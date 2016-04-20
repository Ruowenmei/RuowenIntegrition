package com.ruowen.ruowendemo.selfdefineview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ruowen.ruowendemo.R;


/**
 * Created by ruowen on 2016/4/10.
 */
public class MyTextView extends View{
    private String text;
    private int textColor;
    private int textSize;
    private String flag = "";

    private Paint paint;
    private Rect rect;
    private LinearGradient linearGradient;
    private Matrix matrix;
    private int translate;

    public MyTextView(Context context){
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    /**
     * 获得自定义的样式属性
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        //获取自定义样式属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyViewStyle, defStyle, 0);
        int iLen = array.getIndexCount();
        for (int i=0; i<iLen; i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.MyViewStyle_newViewText:{
                    text = array.getString(attr);
                    break;
                }
                case R.styleable.MyViewStyle_newViewTextColor:{
                    textColor = array.getColor(attr, Color.BLUE);//默认颜色为蓝色
                    break;
                }
                case R.styleable.MyViewStyle_newViewTextSize:{
                    textSize = array.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                }
                case R.styleable.MyViewStyle_newViewGravity:{
                    flag = array.getString(attr);
                    break;
                }
            }
        }
        array.recycle();//资源回收，以便复用

        //初始化画笔和背景区域
        paint = new Paint();
        rect = new Rect();
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;

        //获取测量模式以及推荐的宽高
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        paint.setTextSize(textSize);
        paint.getTextBounds(text, 0, text.length(), rect);

        //设置需要的宽度
        if (MeasureSpec.EXACTLY == widthMode){//EXACTLY模式，对应match_parent和固定宽高属性
            width = widthSize;
        }else{
            float textWidth = rect.width();
            width = (int)(getPaddingLeft() + textWidth + getPaddingRight());
        }

        //设置需要的高度
        if (MeasureSpec.EXACTLY == heightMode){//EXACTLY模式，对应match_parent和固定宽高属性
            height = heightSize;
        }else{
            float textHeight = rect.height();
            height = (int)(getPaddingTop() + textHeight + getPaddingBottom());
        }

        //设置需要绘制的宽高
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0,
                new int[]{0x23ff0000, 0xff2d9fff, Color.WHITE}, null, Shader.TileMode.CLAMP);
//        paint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        //绘制内容
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        if ("center".equals(flag)) {
            canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);//gravity属性效果
        }else{
            canvas.drawText(text, 0, rect.height(), paint);
        }

//        paint.setShader(linearGradient);
//        if (null != matrix){
//            int width = getMeasuredWidth();
//            translate += width/5;
//            if (translate > 2*width){
//                translate = -width;
//            }
//            matrix.setTranslate(translate, 0);
//            linearGradient.setLocalMatrix(matrix);
//            postInvalidateDelayed(100);
//        }
    }

    /**
     * 设置内容
     * @param content
     */
    public void setText(String content){
        this.text = content;
        invalidate();//刷新内容，会调用onDraw()方法
    }
}
