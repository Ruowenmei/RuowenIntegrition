package com.ruowen.ruowendemo.project;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.common.util.LogUtils;

import static java.lang.Math.PI;

public class TestActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvTest;
    private ImageView iv;
    private ImageView iv2;
    private Button btnTop;

    private String source;
    private ValueAnimator animator;
    private ValueAnimator animator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.d("TestActivity onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);//如果不执行setIntent()函数，那么getIntent取到的值任然是旧的intent值
        source = getIntent().getStringExtra("source");
        LogUtils.d("source=" + source);
    }

    @Override
    protected void doBeforeOnCreate() {
        Intent intent = getIntent();
        source = intent.getStringExtra("source");

        LogUtils.d("source=" + source);
    }

    protected void loadViewLayout() {
        setContentView(R.layout.activity_test);
    }

    protected void initViewById(){
        tvTest = (TextView)findViewById(R.id.tv_test);
        iv = (ImageView)findViewById(R.id.iv);
        iv2 = (ImageView)findViewById(R.id.iv2);
        btnTop = (Button)findViewById(R.id.btnTop);

        tvTest.setText(source);
    }

    protected void setListener() {
        btnTop.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        LogUtils.d("TestActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        LogUtils.d("TestActivity onResume");
    }

    protected void doLogic() {
        /*animator = ObjectAnimator.ofFloat(iv, "translationX", 0f, -4f, 3f, -2f, 1f, 0f);
        animator.setDuration(2000);
        animator.setInterpolator(new MyInterpolator());
        animator.setEvaluator(new MyEvaluator());
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();*/

        animator2 = ObjectAnimator.ofFloat(iv2, "translationX", 0f, -4f, 3f, -2f, 1f, 0f);
        animator2.setDuration(2000);
        animator2.setInterpolator(new MyInterpolator2());
        animator2.setEvaluator(new MyEvaluator());
        animator2.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        animator2.setRepeatMode(ValueAnimator.RESTART);
        animator2.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTop:{
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("source", "onNewIntent");
                startActivity(intent);
            }
            break;
        }
    }

    class MyInterpolator implements TimeInterpolator{
        @Override
        public float getInterpolation(float input) {
            if (input<0.1){
                return (float) (4 * (0.1-input) * 10 * Math.sin(2 * PI * input * 50));//50代表5个sin周期
            }else {
                return 0;
            }
        }
    }

    class MyInterpolator2 implements TimeInterpolator{
        @Override
        public float getInterpolation(float input) {
            if (input<0.1){
                return (float) (4 * (0.1-input) * 10 * Math.sin(2 * PI * input * 70));//50代表5个sin周期
            }else {
                return 0;
            }
        }
    }

    class MyEvaluator implements TypeEvaluator<Number>{
        @Override
        public Number evaluate(float fraction, Number startValue, Number endValue) {
            return fraction;
        }
    }

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    };
    Handler handler = new Handler(callback);
}
