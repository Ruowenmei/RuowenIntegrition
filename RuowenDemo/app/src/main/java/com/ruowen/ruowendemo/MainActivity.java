package com.ruowen.ruowendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruowen.ruowendemo.asynctaskleak.AsyncTaskLeakActivity;
import com.ruowen.ruowendemo.multitypelistview.MultiTypeListViewActivity;
import com.ruowen.ruowendemo.selfdefineview.SelfViewActivity;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description : 入口
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnListViewAndHandler;
    private Button btnAsyncTask;
    private Button btnSelfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setListener();
    }

    private void initView(){
        btnListViewAndHandler = (Button)findViewById(R.id.btnListViewAndHandler);
        btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        btnSelfView = (Button)findViewById(R.id.btnSelfView);
    }

    private void setListener(){
        btnListViewAndHandler.setOnClickListener(this);
        btnAsyncTask.setOnClickListener(this);
        btnSelfView.setOnClickListener(this);
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
        }
    }

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
        }
    }
}
