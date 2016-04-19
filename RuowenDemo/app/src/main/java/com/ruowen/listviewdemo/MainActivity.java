package com.ruowen.listviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ruowen.listviewdemo.adapter.ListViewAdapter;
import com.ruowen.listviewdemo.bean.DividerBean;
import com.ruowen.listviewdemo.bean.HotTitleBean;
import com.ruowen.listviewdemo.bean.base.BaseBean;
import com.ruowen.listviewdemo.bean.JobGuessLikeBean;
import com.ruowen.listviewdemo.bean.TopTitleBean;
import com.ruowen.listviewdemo.bean.base.TypeEnum;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description :  测试多类型ListView和Handler内存泄露问题
 *      handler -- 通过remove回调函数和消息方式，防止泄露
 *      myHandler -- 通过弱引用方式防止泄露(使用LeakCanary测试，目前不过)
 */

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tvText;
    private ListView lvTest;
    private ListViewAdapter adapter;
    private List<BaseBean> hotPageList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setListener();

        processLogic();
    }

    private void initView(){
        btn = (Button)findViewById(R.id.btn);
        tvText = (TextView)findViewById(R.id.tv_text);
        lvTest = (ListView)findViewById(R.id.lv_test);
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
        testMultiTypeListView();

        testHandlerLeak();
    }

    @Override
    protected void onDestroy() {
        //handler.removeCallbacksAndMessages(null);//参数为null，移除handler的所有回调和发送的消息
        super.onDestroy();
    }

    private void testMultiTypeListView(){
        hotPageList = initData();
        adapter = new ListViewAdapter(this, hotPageList);
        lvTest.setAdapter(adapter);
    }

    /**
     * 初始化ListView的所有数据
     * @return
     */
    private List<BaseBean> initData(){
        List<BaseBean> list = new ArrayList<>();

        //初始化热门标题
        list.add(new HotTitleBean(TypeEnum.HOT_TITLE, "今日热门"));

        //初始化热门项
        for (int i=0; i<20; i++){
            List<String> recReason = new ArrayList<>();
            recReason.add("福利保障");
            recReason.add("3公里以内");
            List<String> welfare = new ArrayList<>();
            welfare.add("五险一金");
            welfare.add("有吃有喝");
            welfare.add("还好玩，有妹子~");
            JobGuessLikeBean jobGuessLikeBean = new JobGuessLikeBean(TypeEnum.HOT_ITEM,
                    "这是一个标题"+i,
                    "这儿的地址是58",
                    "福利几何",
                    recReason,
                    welfare);
            list.add(jobGuessLikeBean);
        }

        //添加分隔符
        list.add(new DividerBean(TypeEnum.DIVIDER));

        //初始化排行榜标题
        list.add(new TopTitleBean(TypeEnum.TOP_TITLE, "本地热门岗位排行榜"));

        return list;
    }

    private void testHandlerLeak(){
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(1);
            }
        }, 30000);
        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 30000);*/
    }

    private void turnToActivity(){
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);

        finish();
    }

    /*//有内存泄露风险的Handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (1 == msg.what){
                tvText.setText("Hi, handler!");
            }
        }
    };*/

    static class MyHandler extends Handler{
        private final WeakReference<MainActivity> activityWeakReference;//弱引用方式持有activity

        public MyHandler(MainActivity activity){
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = activityWeakReference.get();
            if (null != activity) {
                if (1 == msg.what) {
                    activity.tvText.setText("Hi, handler!");
                }
            }
        }
    }

    private final MyHandler myHandler = new MyHandler(this);
}
