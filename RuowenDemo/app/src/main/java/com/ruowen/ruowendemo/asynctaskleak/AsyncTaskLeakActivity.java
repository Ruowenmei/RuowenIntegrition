package com.ruowen.ruowendemo.asynctaskleak;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.TestActivity;

import java.lang.ref.WeakReference;

public class AsyncTaskLeakActivity extends AppCompatActivity {

    private Button btn;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_leak);

        initView();

        setListener();

        processLogic();
    }

    private void initView(){
        btn = (Button)findViewById(R.id.btn);
        tvText = (TextView)findViewById(R.id.tv_text);
    }

    private void setListener(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnToActivity();
            }
        });
    }

    private void processLogic(){
        (new MyAsyncTask(AsyncTaskLeakActivity.this)).execute();
    }

    private void turnToActivity(){
        Intent intent = new Intent(AsyncTaskLeakActivity.this, TestActivity.class);
        intent.putExtra("source", "asnctask");
        startActivity(intent);

        finish();
    }

    /**
     * 定义一个类，让其继承AsyncTask这个类
     * Params: String类型，表示传递给异步任务的参数类型是String，通常指定的是URL路径
     * Progress: Integer类型，进度条的单位通常都是Integer类型
     * Result：byte[]类型，表示我们下载好的图片以字节数组返回
     * @author xiaoluo
     *
     */
    static class MyAsyncTask extends AsyncTask<String, Integer, byte[]> {

        private WeakReference<AsyncTaskLeakActivity> weakReference;

        public MyAsyncTask(AsyncTaskLeakActivity activity){
            weakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected byte[] doInBackground(String... params) {
            try {
                Thread.sleep(20000);
            }catch (Exception e){

            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(byte[] result) {
            final AsyncTaskLeakActivity activity = weakReference.get();
            if (null != activity) {//使用弱引用访问组件时，必须判断activity是否已经被回收
                activity.tvText.setText("call back");
                Log.d("ruowen", "ruowen>>>>>>>>>>asynctask weakreference is not work");
            }else{
                Log.d("ruowen", "ruowen>>>>>>>>>>asynctask weakreference ok");
            }
        }
    }

}
