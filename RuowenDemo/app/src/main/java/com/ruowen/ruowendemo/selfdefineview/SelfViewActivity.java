package com.ruowen.ruowendemo.selfdefineview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.selfdefineview.view.MyTextView;

public class SelfViewActivity extends AppCompatActivity {

    private MyTextView mtvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view);

        initView();
    }

    private void initView(){
        mtvTest = (MyTextView)findViewById(R.id.mtv_test);
        mtvTest.setText("自定义view");
    }
}
