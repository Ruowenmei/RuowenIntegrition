package com.ruowen.ruowendemo.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        doBeforeOnCreate();

        super.onCreate(savedInstanceState);

        loadViewLayout();

        initViewById();

        setListener();

        processLogic();
    }

    protected abstract void doBeforeOnCreate();

    protected abstract void loadViewLayout();

    protected abstract void initViewById();

    protected abstract void setListener();

    protected abstract void processLogic();
}
