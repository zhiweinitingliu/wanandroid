package com.dukang.wanandroid.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/6/27 16:25
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/27 16:25
 * @LastCheckBy :wdk
 */
public class CloverHeader extends View {

    private Paint mPaint = new Paint();

    public CloverHeader(Context context) {
        super(context);
        this.initView(context, null);
    }

    public CloverHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public CloverHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        DensityUtil density = new DensityUtil();

//        LinearLayout linearLayout = new LinearLayout(context);
//        linearLayout.setId(android.R.id.widget_frame);
//        linearLayout.setGravity(Gravity.CENTER);
//        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//        TextView tvDesc = new TextView(context);
//        tvDesc.setTextColor(0xff666666);
//
//        LayoutParams lpHeaderLayout = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//        lpHeaderLayout.addRule(CENTER_IN_PARENT);
//        addView(linearLayout, lpHeaderLayout);

//        ImageView mProgressView = new ImageView(context);
//        LayoutParams lpProgress = new LayoutParams(density.dip2px(20), density.dip2px(20));
//        lpProgress.addRule(CENTER_IN_PARENT);
//        mProgressView.setLayoutParams(lpProgress);
//        ProgressBarDrawable progressBarDrawable=new ProgressBarDrawable();
//        mProgressView.setImageDrawable(progressBarDrawable);
//        progressBarDrawable.start();

        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width=500;
        int height=500;

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

}
