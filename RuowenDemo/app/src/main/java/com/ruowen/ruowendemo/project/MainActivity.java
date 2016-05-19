package com.ruowen.ruowendemo.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.project.asynctaskleak.AsyncTaskLeakActivity;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.multitypelistview.MultiTypeListViewActivity;
import com.ruowen.ruowendemo.project.retrofit.RetrofitActivity;
import com.ruowen.ruowendemo.project.retrofitandrxjava.RetrofitAndRxJavaActivity;
import com.ruowen.ruowendemo.project.rxjava.RxJavaActivity;
import com.ruowen.ruowendemo.project.selfdefineview.CanvasUseActivity;
import com.ruowen.ruowendemo.project.selfdefineview.DragViewActivity;
import com.ruowen.ruowendemo.project.selfdefineview.SelfViewActivity;
import com.ruowen.ruowendemo.project.selfdefineview.SlipLayoutActivity;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description : 入口
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button btnListViewAndHandler;
    private Button btnAsyncTask;
    private Button btnSelfView;
    private Button btnRxJava;
    private Button btnRetrofit;
    private Button btnRetrofitAndRxJava;
    private Button btnDragView;
    private Button btnSlipLayout;
    private Button btnCanvasUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {}

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViewById() {
        btnListViewAndHandler = (Button)findViewById(R.id.btnListViewAndHandler);
        btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        btnSelfView = (Button)findViewById(R.id.btnSelfView);
        btnRxJava = (Button)findViewById(R.id.btnRxJava);
        btnRetrofit = (Button)findViewById(R.id.btnRetrofit);
        btnRetrofitAndRxJava = (Button)findViewById(R.id.btnRetrofitAndRxJava);
        btnDragView = (Button)findViewById(R.id.btnDragView);
        btnSlipLayout = (Button)findViewById(R.id.btnSlipLayout);
        btnCanvasUse = (Button)findViewById(R.id.btnCanvasUse);
    }

    @Override
    protected void setListener() {
        btnListViewAndHandler.setOnClickListener(this);
        btnAsyncTask.setOnClickListener(this);
        btnSelfView.setOnClickListener(this);
        btnRxJava.setOnClickListener(this);
        btnRetrofit.setOnClickListener(this);
        btnRetrofitAndRxJava.setOnClickListener(this);
        btnDragView.setOnClickListener(this);
        btnSlipLayout.setOnClickListener(this);
        btnCanvasUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnListViewAndHandler:{
                turnToActivity("listview");
            }
            break;
            case R.id.btnAsyncTask:{
                turnToActivity("asynctask");
            }
            break;
            case R.id.btnSelfView:{
                turnToActivity("selfview");
            }
            break;
            case R.id.btnRxJava:{
                turnToActivity("rxjava");
            }
            break;
            case R.id.btnRetrofit:{
                turnToActivity("retrofit");
            }
            break;
            case R.id.btnRetrofitAndRxJava:{
                turnToActivity("retrofitandrxjava");
            }
            break;
            case R.id.btnDragView:{
                turnToActivity("dragview");
            }
            break;
            case R.id.btnSlipLayout:{
                turnToActivity("sliplayout");
            }
            break;
            case R.id.btnCanvasUse:{
                turnToActivity("canvasuse");
            }
            break;
        }
    }

    @Override
    protected void processLogic() {}

    private void turnToActivity(String tag){
        switch (tag) {
            case "listview": {
                Intent intent = new Intent(MainActivity.this, MultiTypeListViewActivity.class);
                startActivity(intent);
            }
            break;
            case "asynctask":{
                Intent intent = new Intent(MainActivity.this, AsyncTaskLeakActivity.class);
                startActivity(intent);
            }
            break;
            case "selfview":{
                Intent intent = new Intent(MainActivity.this, SelfViewActivity.class);
                startActivity(intent);
            }
            break;
            case "rxjava":{
                Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent);
            }
            break;
            case "retrofit":{
                Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }
            break;
            case "retrofitandrxjava":{
                Intent intent = new Intent(MainActivity.this, RetrofitAndRxJavaActivity.class);
                startActivity(intent);
            }
            break;
            case "dragview":{
                Intent intent = new Intent(MainActivity.this, DragViewActivity.class);
                startActivity(intent);
            }
            break;
            case "sliplayout":{
                Intent intent = new Intent(MainActivity.this, SlipLayoutActivity.class);
                startActivity(intent);
            }
            break;
            case "canvasuse":{
                Intent intent = new Intent(MainActivity.this, CanvasUseActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
