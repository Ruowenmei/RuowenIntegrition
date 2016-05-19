package com.ruowen.ruowendemo.project.selfdefineview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;

public class SlipLayoutActivity extends BaseActivity implements View.OnClickListener{

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {

    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_slip_layout);
    }

    @Override
    protected void initViewById() {
        btnTest = (Button)findViewById(R.id.btn_test);
    }

    @Override
    protected void setListener() {
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test:{
                Toast.makeText(this, "hahah", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    protected void processLogic() {}
}
