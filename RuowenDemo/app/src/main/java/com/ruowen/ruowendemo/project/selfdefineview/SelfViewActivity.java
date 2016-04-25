package com.ruowen.ruowendemo.project.selfdefineview;

import android.os.Bundle;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.selfdefineview.view.MyTextView;

public class SelfViewActivity extends BaseActivity {

    private MyTextView mtvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {}

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_self_view);
    }

    @Override
    protected void initViewById() {
        mtvTest = (MyTextView)findViewById(R.id.mtv_test);
    }

    @Override
    protected void setListener() {}

    @Override
    protected void processLogic() {
        mtvTest.setText("自定义view");
    }
}
