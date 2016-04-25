package com.ruowen.ruowendemo.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.project.asynctaskleak.AsyncTaskLeakActivity;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.multitypelistview.MultiTypeListViewActivity;
import com.ruowen.ruowendemo.project.rxjava.RxJavaActivity;
import com.ruowen.ruowendemo.project.selfdefineview.SelfViewActivity;

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
    }

    @Override
    protected void setListener() {
        btnListViewAndHandler.setOnClickListener(this);
        btnAsyncTask.setOnClickListener(this);
        btnSelfView.setOnClickListener(this);
        btnRxJava.setOnClickListener(this);
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
        }
    }
}
