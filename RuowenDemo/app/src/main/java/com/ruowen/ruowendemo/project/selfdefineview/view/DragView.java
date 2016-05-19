package com.ruowen.ruowendemo.project.selfdefineview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Date : 2016/4/27
 * Author : ruowen
 * Description : 可拖动的View
 */
public class DragView extends View {

    private int lastX;
    private int lastY;
    private Scroller scroller;

    int offsetX1;
    int offsetY1;

    public DragView(Context context){
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        scroller = new Scroller(context);
    }

//    /**
//     * 使用相对位置来实现移动
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int)event.getX();
//        int y = (int)event.getY();
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:{
//                lastX = x;
//                lastY = y;
//            }
//            break;
//            case MotionEvent.ACTION_MOVE:{
//                int offsetX = x - lastX;
//                int offsetY = y - lastY;
//
//                /*//1.使用layout()方法，重新设置自身位置
//                layout(getLeft()+offsetX,
//                        getTop()+offsetY,
//                        getRight()+offsetX,
//                        getBottom()+offsetY);*/
//
//                /*//2.使用offset函数
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);*/
//
//                //3.相对位置，使用scrollBy()来移动，scrollBy()移动控件内容的位置，对于容器，内容即子控件
//                ((View)getParent()).scrollBy(-offsetX, -offsetY);
//            }
//            break;
//            case MotionEvent.ACTION_UP:{
//                //使用Scroller实现平滑移动
//                View viewGroup = (View)getParent();
//                scroller.startScroll(viewGroup.getScrollX(),//getScrollX()获取的是相对坐标
//                        viewGroup.getScrollY(),//getScrollY()获取的是相对坐标
//                        -viewGroup.getScrollX(),
//                        -viewGroup.getScrollY());
//
//                invalidate();
//            }
//            break;
//        }
//
//        return true;
//    }

    /**
     * 使用绝对位置来实现移动
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int)event.getRawX();
        int rawY = (int)event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                lastX = rawX;
                lastY = rawY;

                offsetX1 = 0;
                offsetY1 = 0;
            }
            break;
            case MotionEvent.ACTION_MOVE:{
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;

                /*//1.使用layout()方法，重新设置自身位置
                layout(getLeft()+offsetX,
                        getTop()+offsetY,
                        getRight()+offsetX,
                        getBottom()+offsetY);*/

                /*//2.使用offset函数
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);*/

                //3.绝对位置，使用scrollTo()来移动，scrollTo()移动控件内容的位置，
                // 对于容器，内容即子控件,控制很麻烦，不推荐使用
                offsetX1 += offsetX;
                offsetY1 += offsetY;
                ((View)getParent()).scrollTo(-offsetX1, -offsetY1);

                lastX = rawX;
                lastY = rawY;
            }
            break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(
                    scroller.getCurrX(),
                    scroller.getCurrY());

            invalidate();
        }
    }
}
