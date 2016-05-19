package com.ruowen.ruowendemo.project.selfdefineview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date : 2016/5/13
 * Author : ruowen
 * Description :
 */
public class ClockView extends View{

    private int width;
    private int height;
    private Paint paint;

    public ClockView(Context context){
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制外圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawCircle(width/2, height/2, width/2-3, paint);

        //绘制刻度
        for (int i=0; i<24; i++){
            if (i==0 || i==6 || i==12 || i==18){//区分整点
                paint.setStrokeWidth(5);
                paint.setTextSize(30);
                canvas.drawLine(width/2, height/2-width/2+3, width/2, height/2-width/2+40, paint);
                String degree = String.valueOf(i);
                canvas.drawText(degree, width/2-paint.measureText(degree)/2, height/2-width/2+70, paint);
            }else{
                paint.setStrokeWidth(3);
                paint.setTextSize(15);
                canvas.drawLine(width/2, height/2-width/2+3, width/2, height/2-width/2+20, paint);
                String degree = String.valueOf(i);
                canvas.drawText(degree, width/2-paint.measureText(degree)/2, height/2-width/2+40, paint);
            }

            canvas.rotate(15, width/2, height/2);//rotate()方法，通过旋转简化坐标运算
        }

        //canvas.save();//保存开始绘制的图像

        //绘制时针和分针
        canvas.translate(width/2, height/2);//translate移动原点到x,y
        paint.setStrokeWidth(20);
        canvas.drawLine(0, 0, 100, 100, paint);
        paint.setStrokeWidth(10);
        canvas.drawLine(0, 0, 100, 200, paint);
        canvas.restore();//合并图层,很重要

        canvas.saveLayerAlpha(0, 0, width, height, 100, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        paint.setColor(Color.BLUE);
        canvas.drawCircle((float)(width*0.75), (float)(height*0.75), (float)(width*0.25), paint);
        canvas.restore();

    }
}
