package com.ruowen.ruowendemo.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.common.util.LogUtils;
import com.ruowen.ruowendemo.project.RecyclerView.RecycleViewActivity;
import com.ruowen.ruowendemo.project.asynctaskleak.AsyncTaskLeakActivity;
import com.ruowen.ruowendemo.project.camera.BaiduCameraActivity;
import com.ruowen.ruowendemo.project.camera.JobShotIdCardActivity;
import com.ruowen.ruowendemo.project.camera.SensorCameraActivity;
import com.ruowen.ruowendemo.project.hook.UnManifestActivity;
import com.ruowen.ruowendemo.project.imageloader.ImageLoaderActivity;
import com.ruowen.ruowendemo.project.lottie.LottieActivity;
import com.ruowen.ruowendemo.project.multitypelistview.MultiTypeListViewActivity;
import com.ruowen.ruowendemo.project.myslidingdrawer.SlidingDrawerActivity;
import com.ruowen.ruowendemo.project.retrofit.RetrofitActivity;
import com.ruowen.ruowendemo.project.retrofitandrxjava.RetrofitAndRxJavaActivity;
import com.ruowen.ruowendemo.project.rxjava.RxJavaActivity;
import com.ruowen.ruowendemo.project.selfdefineview.CanvasUseActivity;
import com.ruowen.ruowendemo.project.selfdefineview.ColorMatrixActivity;
import com.ruowen.ruowendemo.project.selfdefineview.DragViewActivity;
import com.ruowen.ruowendemo.project.selfdefineview.SelfViewActivity;
import com.ruowen.ruowendemo.project.selfdefineview.SlipLayoutActivity;
import com.ruowen.ruowendemo.project.selfdefineview.ViewGroupActivity;
import com.ruowen.ruowendemo.project.selfdefineview.WavingFlagActivity;
import com.ruowen.ruowendemo.project.thread.AsyncTaskActivity;
import com.ruowen.ruowendemo.project.viewtoimage.ViewToImageActivity;

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
    private Button btnColorMatrix;
    private Button btnWavingFlag;
    private Button btnCamera;
    private Button btnBaiCamera;
    private Button btnSensorCamera;
    private Button btnSlidingDrawer;
    private Button btnViewImage;
    private Button btnAnimTest;
    private Button btnLottieTest;
    private Button btnRecycleView;
    private Button btnAsync;
    private Button btnUnMani;
    private Button btnViewGroup;
    private Button btnImageLoad;

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
        btnColorMatrix = (Button)findViewById(R.id.btnColorMatrix);
        btnWavingFlag = (Button)findViewById(R.id.btnWavingFlag);
        btnCamera = (Button)findViewById(R.id.btnCamera);
        btnBaiCamera = (Button)findViewById(R.id.btnBaiCamera);
        btnSensorCamera = (Button)findViewById(R.id.btnSensorCamera);
        btnSlidingDrawer = (Button)findViewById(R.id.btnSlidingDrawer);
        btnViewImage = (Button)findViewById(R.id.btnViewImage);
        btnAnimTest = (Button)findViewById(R.id.btnAnimTest);
        btnLottieTest = (Button)findViewById(R.id.btnLottie);
        btnRecycleView = (Button)findViewById(R.id.btnRecycle);
        btnAsync = (Button)findViewById(R.id.btnAsync);
        btnUnMani = (Button)findViewById(R.id.btnUnMani);
        btnViewGroup = (Button)findViewById(R.id.btnViewGroup);
        btnImageLoad = (Button)findViewById(R.id.btnImageLoad);
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
        btnColorMatrix.setOnClickListener(this);
        btnWavingFlag.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnBaiCamera.setOnClickListener(this);
        btnSensorCamera.setOnClickListener(this);
        btnSlidingDrawer.setOnClickListener(this);
        btnViewImage.setOnClickListener(this);
        btnAnimTest.setOnClickListener(this);
        btnLottieTest.setOnClickListener(this);
        btnRecycleView.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnUnMani.setOnClickListener(this);
        btnViewGroup.setOnClickListener(this);
        btnImageLoad.setOnClickListener(this);
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
            case R.id.btnColorMatrix:{
                turnToActivity("colormatrix");
            }
            break;
            case R.id.btnWavingFlag:{
                turnToActivity("wavingflag");
            }
            break;
            case R.id.btnCamera:{
                turnToActivity("camera");
            }
            break;
            case R.id.btnBaiCamera:{
                turnToActivity("baicamera");
            }
            break;
            case R.id.btnSensorCamera:{
                turnToActivity("sensorcamera");
            }
            break;
            case R.id.btnSlidingDrawer:{
                turnToActivity("slidingdrawer");
            }
            break;
            case R.id.btnViewImage:{
                turnToActivity("viewimage");
            }
            break;
            case R.id.btnAnimTest:{
                turnToActivity("animtest");
            }
            break;
            case R.id.btnLottie:{
                turnToActivity("lottietest");
            }
            break;
            case R.id.btnRecycle:{
                turnToActivity("recycleview");
            }
            break;
            case R.id.btnAsync:{
                turnToActivity("async");
            }
            break;
            case R.id.btnUnMani:{
                turnToActivity("unmani");
            }
            break;
            case R.id.btnViewGroup:{
                turnToActivity("viewgroup");
            }
            break;
            case R.id.btnImageLoad:{
                turnToActivity("imageload");
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
            case "colormatrix":{
                Intent intent = new Intent(MainActivity.this, ColorMatrixActivity.class);
                startActivity(intent);
            }
            break;
            case "wavingflag":{
                Intent intent = new Intent(MainActivity.this, WavingFlagActivity.class);
                startActivity(intent);
            }
            break;
            case "camera":{
                Intent intent = new Intent(MainActivity.this, JobShotIdCardActivity.class);
                intent.putExtra(JobShotIdCardActivity.SOURCE, JobShotIdCardActivity.SOURCE_ID_CARD_INSIGNIA);
                startActivity(intent);
            }
            break;
            case "baicamera":{
                Intent intent = new Intent(MainActivity.this, BaiduCameraActivity.class);
                intent.putExtra(BaiduCameraActivity.SOURCE, BaiduCameraActivity.SOURCE_ID_CARD_INSIGNIA);
                startActivity(intent);
            }
            break;
            case "sensorcamera":{
                Intent intent = new Intent(MainActivity.this, SensorCameraActivity.class);
                intent.putExtra(SensorCameraActivity.SOURCE, SensorCameraActivity.SOURCE_HALF_BODY);
                startActivity(intent);
            }
            break;
            case "slidingdrawer":{
                Intent intent = new Intent(MainActivity.this, SlidingDrawerActivity.class);
                startActivity(intent);
            }
            break;
            case "viewimage":{
                Intent intent = new Intent(MainActivity.this, ViewToImageActivity.class);
                startActivity(intent);
            }
            break;
            case "animtest":{
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
            break;
            case "lottietest":{
                Intent intent = new Intent(MainActivity.this, LottieActivity.class);
                startActivity(intent);
            }
            break;
            case "recycleview":{
                Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
                startActivity(intent);
            }
            break;
            case "async":{
                Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
            }
            break;
            case "unmani":{
                Intent intent = new Intent(MainActivity.this, UnManifestActivity.class);
                intent.putExtra("proxy", true);
                startActivity(intent);
            }
            break;
            case "viewgroup":{
                Intent intent = new Intent(MainActivity.this, ViewGroupActivity.class);
                startActivity(intent);
            }
            break;
            case "imageload":{
                Intent intent = new Intent(MainActivity.this, ImageLoaderActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onPause();
    }
}
