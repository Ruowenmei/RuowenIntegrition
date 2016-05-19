package com.ruowen.ruowendemo.project.selfdefineview.viewgroup;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Date : 2016/5/5
 * Author : ruowen
 * Description :
 */
public class SlipLayout extends FrameLayout {

    private ViewDragHelper viewDragHelper;
    private View vMenuView;
    private View vMainView;
    private int width;

    private boolean bIsOnRight = false;
    private float lastX;

    public SlipLayout(Context context){
        this(context, null);
    }

    public SlipLayout(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public SlipLayout(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        vMenuView = getChildAt(0);
        vMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = vMenuView.getMeasuredWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:{
                lastX = ev.getRawX();
                return super.onInterceptTouchEvent(ev);
                //return viewDragHelper.shouldInterceptTouchEvent(ev);
            }
            case MotionEvent.ACTION_MOVE:{
                if (ev.getRawX() > lastX){
                    return viewDragHelper.shouldInterceptTouchEvent(ev);
                }else{
                    return super.onInterceptTouchEvent(ev);
                }
            }
        }

        return super.onInterceptTouchEvent(ev);*/

        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                lastX = event.getRawX();
                viewDragHelper.processTouchEvent(event);//触摸事件传递给viewDragHelper
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                if (bIsOnRight){
                    if (event.getRawX() > lastX) {
                        return super.onTouchEvent(event);
                    } else {
                        viewDragHelper.processTouchEvent(event);//触摸事件传递给viewDragHelper
                        return true;
                    }
                }else {
                    if (event.getRawX() > lastX) {
                        viewDragHelper.processTouchEvent(event);//触摸事件传递给viewDragHelper
                        return true;
                    } else {
                        return super.onTouchEvent(event);
                    }
                }
            }
        }

        viewDragHelper.processTouchEvent(event);//触摸事件传递给viewDragHelper
        return true;
    }

    //ViewDragHealper同样是利用Scroller实现的滑动
    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback(){
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == vMainView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left < 0){
                return 0;
            } else if (left > width){
                return width;
            } else{
                return left;
            }
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        //手指离开后的处理
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if (vMainView.getLeft() < width * 0.75){//关闭菜单
                viewDragHelper.smoothSlideViewTo(vMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(SlipLayout.this);
                bIsOnRight = false;
            } else{
                viewDragHelper.smoothSlideViewTo(vMainView, width, 0);
                ViewCompat.postInvalidateOnAnimation(SlipLayout.this);
                bIsOnRight = true;
            }
        }
    };
}
