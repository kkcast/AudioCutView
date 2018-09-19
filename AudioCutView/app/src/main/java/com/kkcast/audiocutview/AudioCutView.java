package com.kkcast.audiocutview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;


/**
 * @author kcast
 * @date 2018/09/19
 */

public class AudioCutView extends View {

    private  Paint paint;

    private float maxProgress=100.0f;

    /**
     * 当前进度，100进制的
     */
    private float currentProgress=0.0f;

    private float viewCurrentProgress=0;

    private int measuredHeight;
    private int measuredWidth;

    private float videoProgressWidth=100;

    private  Paint progressPaint;

    private float videoWidthRate;

    private Paint wavePaint;


    private float[] smoothedGains;



    public AudioCutView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        paint = new Paint();
        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);

        wavePaint = new Paint();
        wavePaint.setColor(Color.parseColor("#FF7701"));
        wavePaint.setStrokeWidth(dpToPx(3));
        wavePaint.setAntiAlias(false);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();

        computeSmoothedGains();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * 绘制一条条线
         */
            float ctr = measuredHeight / 2f;
            for (int i = 0; i < measuredWidth; i+=15) {
                canvas.drawLine(i, ctr-smoothedGains[i]/2, i, ctr+smoothedGains[i]/2, wavePaint);
        }


        /**
         * 绘制阴影
         */
        videoProgressWidth=measuredWidth*videoWidthRate;
        paint.setStrokeWidth(getHeight());
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        paint.setColor(Color.parseColor("#87654321"));

        viewCurrentProgress=(measuredWidth-videoProgressWidth)*currentProgress/maxProgress;
        canvas.drawLine(viewCurrentProgress,measuredHeight/2,viewCurrentProgress+videoProgressWidth,measuredHeight/2,paint);

    }

    /**
     * 设置视频占音频的比例
     * @param videoWidthRate
     */
    public void setVideoProgressWidth(float videoWidthRate){
        this.videoWidthRate=videoWidthRate;
    }


    /**
     * 设置最大进度
     * @param maxProgress
     */
    public void setMaxProgress(float maxProgress){
        this.maxProgress=maxProgress;
    }


    /**
     * 设置当前进度
     * @param currentNum
     */
    public void setCurrentProgress(float currentNum){
        currentProgress=currentNum;
        postInvalidate();
    }


    /**
     * 生成一些随机数据
     */
    public void computeSmoothedGains() {

        smoothedGains = new float[measuredWidth];
        Random rand = new Random();
        int MAX=180;
        int MIN=80;
        for (int i=0;i<measuredWidth;i++){
            int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;
            smoothedGains[i]=randNumber;
        }

    }

    public float dpToPx(float dp) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


}
