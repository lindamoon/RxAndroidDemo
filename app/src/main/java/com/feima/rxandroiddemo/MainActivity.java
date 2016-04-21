package com.feima.rxandroiddemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtn;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mTv = (TextView) findViewById(R.id.tv);

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
                SystemClock.sleep(2000);
                subscriber.onNext("hello RxJava");
                subscriber.onCompleted();
            }
        });

        Observable<String> observable1 = Observable.just("Haha!!!");

        Subscriber<String> sub1 = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.e(TAG, "sub1 onStart"+System.currentTimeMillis());

            }

            @Override
            public void onCompleted() {
                mTv.setText("onCompleted");
                Log.e(TAG, "sub1 onCompleted" + System.currentTimeMillis());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                mTv.setText(s);
                Log.e(TAG, "sub1 onNext "+ s + "***" +System.currentTimeMillis());
            }
        };

        Subscriber<String> sub2 = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "sub2" + " onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "sub2 " + "onNext " + s);
            }
        };


//        observable.subscribe(sub1);
//        observable.subscribe(sub2);
//        observable1.subscribe(sub1);


        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "action1" + s);
            }
        };

//        observable.subscribe(action1);

        Observable.just("Hello world").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.print(TAG+" "+ s);
            }
        });





    }
}
