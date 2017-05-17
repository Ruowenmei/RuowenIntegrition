package com.ruowen.ruowendemo.project.transanim;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.util.GlobalData;

/**
 * Created by ruowen on 2017/5/16.
 */

public class VotePubSuccessFragment extends Fragment {

    private LinearLayout llContainer;
    private ImageView ivPic;

    private ShareAnimData shareAnimData = null;
    private Rect targetViewRect = new Rect();

    /**
     * 转场动画
     */
    private boolean isMeasured = false;
    private static final int DURATION = 300;
    private static final AccelerateDecelerateInterpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final String SCALE_WIDTH = "SCALE_WIDTH";
    private static final String SCALE_HEIGHT = "SCALE_HEIGHT";
    private static final String TRANSITION_X = "TRANSITION_X";
    private static final String TRANSITION_Y = "TRANSITION_Y";
    private Palette imagePalette;

    /**
     * 存储图片缩放比例和位移距离
     */
    private Bundle mScaleBundle = new Bundle();
    private Bundle mTransitionBundle = new Bundle();

    /**
     * 上一个界面图片的宽度和高度
     */
    private int originWidth;
    private int originHeight;

    public static VotePubSuccessFragment newInstance() {
        VotePubSuccessFragment fragment = new VotePubSuccessFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pub_success, container, false);

        llContainer = (LinearLayout)view.findViewById(R.id.ll_container);
        ivPic = (ImageView)view.findViewById(R.id.iv_pic);
        ViewTreeObserver observer = ivPic.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!isMeasured) {
                    ivPic.getGlobalVisibleRect(targetViewRect);
                    if (null == targetViewRect){
                        Log.d("zhengbin01", "rect null");
                    }
                    initAnimInfo();

                    runEnterAnim();
                    isMeasured = true;
                }
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void setShareAnimData(ShareAnimData shareAnimData) {
        this.shareAnimData = shareAnimData;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化场景
     */
    private void initAnimInfo() {
        Rect rect = shareAnimData.getRect();
        originWidth = rect.right - rect.left;
        originHeight = rect.bottom - rect.top;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(originWidth, originHeight);
        params.setMargins(rect.left, rect.top - getStatusBarHeight(), rect.right, rect.bottom);
        ivPic.setLayoutParams(params);

        // 设置 ImageView 的图片和缩放类型
        ivPic.setImageBitmap(shareAnimData.getBitmap());
        ivPic.setScaleType(ImageView.ScaleType.CENTER_CROP);
        getBundleInfo(shareAnimData.getBitmap());

        /*// 创建一个 Palette 对象
        imagePalette = Palette.from(shareAnimData.getBitmap()).generate();
        // 使用 Palette 设置背景颜色
        llContainer.setBackgroundColor(imagePalette.getVibrantColor(getResources().getColor(android.R.color.black)));*/
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return -1;
    }

    /**
     * 计算图片缩放比例，以及位移距离
     *
     * @param bitmap
     */
    private void getBundleInfo(Bitmap bitmap) {
        mScaleBundle.putFloat(SCALE_WIDTH, (float) (targetViewRect.right - targetViewRect.left) / originWidth);
        mScaleBundle.putFloat(SCALE_HEIGHT, (float) (targetViewRect.bottom - targetViewRect.top) / originHeight);
        // 计算位移距离，并将数据存储到 bundle 中

        Rect rect = shareAnimData.getRect();
        mTransitionBundle.putFloat(TRANSITION_X,  targetViewRect.left + (float) (targetViewRect.right - targetViewRect.left) / 2 - (rect.left + (rect.right - rect.left) / 2));
        mTransitionBundle.putFloat(TRANSITION_Y,  targetViewRect.top + (float) (targetViewRect.bottom - targetViewRect.top) / 2 - (rect.top + (rect.bottom - rect.top) / 2));
    }

    /**
     * 模拟入场动画
     */
    private void runEnterAnim() {
        ivPic.animate()
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setDuration(DURATION)
                .scaleX(mScaleBundle.getFloat(SCALE_WIDTH))
                .scaleY(mScaleBundle.getFloat(SCALE_HEIGHT))
                .translationX(mTransitionBundle.getFloat(TRANSITION_X))
                .translationY(mTransitionBundle.getFloat(TRANSITION_Y))
                .start();
        ivPic.setVisibility(View.VISIBLE);
    }
}
