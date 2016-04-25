package com.ruowen.ruowendemo.project.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.MyApplication;
import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.project.TestActivity;

import rx.Observable;
import rx.Subscriber;

public class RxJavaActivity extends BaseActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {}

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_rx_java);
    }


    @Override
    protected void initViewById() {
        btn = (Button)findViewById(R.id.btn);
    }

    @Override
    protected void setListener() {
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:{
                Intent intent = new Intent(RxJavaActivity.this, TestActivity.class);
                startActivity(intent);

                finish();
            }
            break;
        }
    }

    @Override
    protected void processLogic() {
        observable.subscribe(subscriber);
    }


    Observable observable = Observable.create(new Observable.OnSubscribe<String>(){
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onCompleted();
        }
    });

    Subscriber<String> subscriber = new Subscriber<String>(){

        @Override
        public void onStart() {
            super.onStart();
            //可在其中处理预置事件，但最好与线程无关，它总是在subscribe线程中执行，无法主动指定
        }

        @Override
        public void onNext(String s) {
            Log.d(MyApplication.TAG, "ruowen>>>>>>>>>>>>> onNext"+s);
        }

        @Override
        public void onCompleted() {
            Log.d(MyApplication.TAG, "ruowen>>>>>>>>>>>>> onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(MyApplication.TAG, "ruowen>>>>>>>>>>>> onError");
        }
    };
}
