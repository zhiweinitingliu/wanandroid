package com.dukang.wanandroid.refresh;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/6/27 18:54
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/27 18:54
 * @LastCheckBy :wdk
 */
public class ProgressBarDrawable extends Drawable implements Animatable {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();


    public ProgressBarDrawable() {
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    public void draw(@NonNull Canvas canvas) {

//        Rect rect = getBounds();
//        int width=rect.width();
//        int height=rect.height();
//        Log.e("width",width+"");
//        Log.e("height",height+"");
//        canvas.save();

        int width=50;
        int height=50;

        int centerX=width/2;
        int centerY=height/2;

        int Radius=Math.max(width,height)/2;

        Path path=new Path();

        path.moveTo(0,0);

        // float leftctrY = - (Radius*4)/5.0f;
        float leftctrY = - (Radius*7)/10.0f;
        float leftctrX = -(float) (Math.abs(leftctrY) * Math.tan(Math.toRadians(30)));
        // path.lineTo(leftctrX,leftctrY);

        int lastX = 0;
        int lastY = -Radius;

        float rightctrY = - (Radius*7)/10.0f;
        float rightctrX = (float) (Math.abs(rightctrY) * Math.tan(Math.toRadians(30)));

        path.quadTo(leftctrX,leftctrY,lastX,lastY);
        path.quadTo(rightctrX,rightctrY,0,0);

        path.close();
        path.setFillType(Path.FillType.WINDING);



        int restoreId = canvas.save();
        Paint.Style style = mPaint.getStyle();
        // mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(centerX,centerY);
        canvas.drawPath(path,mPaint);

        mPaint.setStyle(style);
        canvas.restoreToCount(restoreId);




    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        invalidateSelf();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
