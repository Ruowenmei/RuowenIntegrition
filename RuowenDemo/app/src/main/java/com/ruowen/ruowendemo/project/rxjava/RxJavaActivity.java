package com.ruowen.ruowendemo.project.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.MyApplication;
import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.project.TestActivity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description : RxJava主要用于异步处理，作用类似于Asynctask和Handler,但是其在实现上表现的更加简洁
 *              （逻辑上的简洁，而非代码数量），与Retrofit配合使用，味道更好哟
 */

public class RxJavaActivity extends BaseActivity implements View.OnClickListener{

    private Button btnSubscribe;
    private Button btnSubscribeThread;
    private Button btnSubscribeMap;
    private Button btnSubscribeFlatMap;
    private Button btn;
    private TextView tvDisplay;

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
        btnSubscribe = (Button)findViewById(R.id.btn_subscribe);
        btnSubscribeThread = (Button)findViewById(R.id.btn_subscribe_thread);
        btnSubscribeMap = (Button)findViewById(R.id.btn_subscribe_map);
        btnSubscribeFlatMap = (Button)findViewById(R.id.btn_subscribe_flatMap);
        tvDisplay = (TextView)findViewById(R.id.tv_display);
    }

    @Override
    protected void setListener() {
        btn.setOnClickListener(this);
        btnSubscribe.setOnClickListener(this);
        btnSubscribeThread.setOnClickListener(this);
        btnSubscribeMap.setOnClickListener(this);
        btnSubscribeFlatMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:{
                Intent intent = new Intent(RxJavaActivity.this, TestActivity.class);
                intent.putExtra("source", "rxjava");
                startActivity(intent);

                finish();
            }
            break;
            case R.id.btn_subscribe:{
                observable.subscribe(subscriber);//执行subscibe时，触发观察者行为
            }
            break;
            case R.id.btn_subscribe_thread:{
                Observable.just("dog", "pig", "monkey", "banana")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            tvDisplay.setText(s);
                            Log.d(MyApplication.TAG, "ruowen>>>>>>>>>>>>>"+s);
                        }
                    });
            }
            break;
            case R.id.btn_subscribe_map:{
                Observable.just(1, 2, 3, 4)
                    .map(new Func1<Integer, String>() {
                        @Override
                        public String call(Integer integer) {
                            String str = "";
                            switch (integer){
                                case 1:{
                                    str = "天津";
                                }
                                break;
                                case 2:{
                                    str = "安徽";
                                }
                                break;
                                case 3:{
                                    str = "湖南";
                                }
                                break;
                                case 4:{
                                    str = "湖北";
                                }
                                break;
                            }

                            try {
                                Thread.sleep(2000);
                            }catch (Exception e){
                                Log.d(MyApplication.TAG, "ruowen>>>>>Thread Exception");
                            }

                            return str;
                        }
                    })
                    .subscribeOn(Schedulers.io())//线程调度，指定subscribe发生的线程
                    .observeOn(AndroidSchedulers.mainThread())//线程调度，指定subscriber运行的线程
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            Log.d(MyApplication.TAG, "ruowen>>>>>>>>>>>"+s);
                        }
                    });
            }
            break;
            case R.id.btn_subscribe_flatMap:{
                //配合Retrofit使用
            }
            break;
        }
    }

    @Override
    protected void processLogic() {}


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
