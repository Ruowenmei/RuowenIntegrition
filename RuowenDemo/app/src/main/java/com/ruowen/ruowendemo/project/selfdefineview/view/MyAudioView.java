package com.ruowen.ruowendemo.project.selfdefineview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date : 2016/4/22
 * Author : ruowen
 * Description : 绘制音频条形图
 */
public class MyAudioView extends View{

    private static final int RECT_COUNT = 30;
    private static final int OFFSET = 2;
    private float[] arrayHeights = new float[RECT_COUNT];

    private Paint paint;
    private LinearGradient linearGradient;

    public MyAudioView(Context context){
        this(context, null);
    }

    public MyAudioView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public MyAudioView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        initHeights();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();

        linearGradient = new LinearGradient(0, 0, width, getMeasuredHeight()+200,
                Color.YELLOW, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        paint.setStrokeCap(Paint.Cap.ROUND);

        float rectWidth = width / RECT_COUNT;
        for (int i=0; i<RECT_COUNT; i++){
            float rectHeight = getRectHeight();
            if (-1 == arrayHeights[i]) {
                arrayHeights[i] = rectHeight;
            }else{
                if (RECT_COUNT-1 == i){
                    arrayHeights[i] = getRectHeight();
                }else{
                    arrayHeights[i] = arrayHeights[i+1];
                }
            }
            canvas.drawRect(rectWidth*i + OFFSET,
                    arrayHeights[i],
                    rectWidth*(i+1),
                    (float)getMeasuredHeight(),
                    paint);
        }

        postInvalidateDelayed(450);
    }

    private void initHeights(){
        for (int i=0; i<RECT_COUNT; i++){
            arrayHeights[i] = -1;
        }
    }

    private float getRectHeight(){
        return (float) (getMeasuredHeight() * Math.random());
    }
}
