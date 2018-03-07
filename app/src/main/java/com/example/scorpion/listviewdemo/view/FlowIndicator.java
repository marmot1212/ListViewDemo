package com.example.scorpion.listviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.scorpion.listviewdemo.R;

/**
 * Created by scorpion on 2018/3/7.
 */

public class FlowIndicator extends View {
    private int mCount;
    private int mSpacing;
    private int mRadius;
    private int mFocus;
    private int normalColor;
    private int focusColor;

    private Paint mPaint;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
        requestLayout(); // 重新校对编辑、当控件数量或者布局发生变化时调用
    }

    public int getFocus() {
        return mFocus;
    }

    public void setFocus(int focus) {
        mFocus = focus;
        invalidate(); // 重新绘制 回调 onDraw()
    }

    public FlowIndicator(Context context) {
        super(context);
    }

    public FlowIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowIndicator);
        mCount = typedArray.getInt(R.styleable.FlowIndicator_count,0);
        mFocus = typedArray.getInt(R.styleable.FlowIndicator_focus,0);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.FlowIndicator_radius, 15);
        mSpacing = typedArray.getDimensionPixelSize(R.styleable.FlowIndicator_spacing, 10);
        normalColor = typedArray.getColor(R.styleable.FlowIndicator_normalColor, 0xfff);
        focusColor = typedArray.getColor(R.styleable.FlowIndicator_focusColor, 0xf00);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

    private int measureHeight(int measureSpec) {
        int height = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            int temp = getPaddingBottom()+getPaddingTop()+2*mRadius;
            height = Math.min( height, temp);
        }
        return height;
    }

    private int measureWidth(int measureSpec) {
        int width = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            int temp = getPaddingLeft()+getPaddingRight()+mRadius*2*mCount+(mCount-1)*mSpacing;
            width = Math.min(width, temp);
        }
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i=0; i<mCount; i++) {
            int x = mRadius+ i*(2*mRadius+mSpacing);
            mPaint.setColor(i==mFocus?focusColor:normalColor);
            canvas.drawCircle(x, mRadius, mRadius, mPaint);
        }

    }
}
