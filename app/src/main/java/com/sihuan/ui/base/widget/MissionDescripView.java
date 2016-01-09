package com.sihuan.ui.base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/1/9.
 */
public class MissionDescripView extends View {


    final int bkColor = Color.argb(255,232,144,0);
    float mMinSize;
    final int radius = 20;

    final int splitLength =60;//分割文本的长度
    //巨型的长 宽
    int mSzie;
    public MissionDescripView(Context context){
        super(context,null);
    }


    public MissionDescripView(Context context,AttributeSet attrs){
        super(context,attrs,0);
    }


    public MissionDescripView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int currentHeight= 50;
        Paint mPaint = new Paint();
        // 获取布局控件宽高
        int width = getWidth();
        int height = getHeight();



        mPaint.setColor(Color.argb(255,232,144,0));//黄色

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);

        drawRoundRect(0, 0, width, height, radius, mPaint, canvas);



        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40.0f);
        mPaint.setTypeface(Typeface.SANS_SERIF);


        String mDesc ="Follow the link to the page of certain hashtag (pakistan) and write a comment to any public";
        int length  = mDesc.toCharArray().length;
        String[] text = new String[2];
        text[0] = mDesc.substring(0,splitLength);
        if(length > splitLength){
            text[1] = mDesc.substring(splitLength,length);
        }
        canvas.drawText(text[0],50,currentHeight,mPaint);
        currentHeight += 30;
        if(text[1] != null){
            canvas.drawText(text[1],50,currentHeight,mPaint);
            currentHeight += 30;
        }

        mPaint.setColor(Color.WHITE);
        drawRoundRect(width - 300, currentHeight, width - 100, currentHeight + 60, 5, mPaint, canvas);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(50.f);
        canvas.drawText("批准的",width-280,currentHeight+50,mPaint);
        currentHeight+=60;



        //绘制 任务状态  。。审核中 字体是黄色   批准的 字体是绿色
        //未完待续


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //期望面积
        int desiredWidth = Integer.MAX_VALUE;
        int width;
        int height;
        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }
        mMinSize = width/570.f;
        int desiredHeight = (int) (mMinSize*650);

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width,height);
    }


    //任务框  四角平滑
    public void drawRoundRect(float left ,float top,float right,float down,float radius,Paint paint,Canvas canvas){
        Path p = new Path();
        p.moveTo(left,top);
        p.lineTo(right-radius,top);
        p.quadTo(right, top, right,top+radius);
        p.lineTo(right,down-radius);
        p.quadTo(right, down, right-radius,down);


        p.lineTo(left+radius,down);
        p.quadTo(left, down, left,down-radius);


        p.lineTo(left,top+radius);
        p.quadTo(left, top, left+radius,top);
        canvas.drawPath(p,paint);
    }
}
