package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/7/14 18:10
 * @UpdateUser:
 * @UpdateDate: 2020/7/14 18:10
 * @UpdateRemark:
 * @Version:
 */
public class IndicatorTriangleView extends View implements IIndicatorView {
    private Path path;
    private Indicator indicator;
    private int height;
    public IndicatorTriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        indicator=new Indicator(this);
        //实例化路径
        path = new Path();

        indicator=new Indicator(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorTriangleView);

        indicator.setWidth_indicator_selected(typedArray.getDimensionPixelSize(R.styleable.IndicatorTriangleView_width_indicator_selected, ScreenUtils.dpAdapt(context,12)));
        indicator.setWidth_indicator_max(typedArray.getDimensionPixelSize(R.styleable.IndicatorTriangleView_width_indicator_max, ScreenUtils.dpAdapt(context,48)));
        indicator.setHeight_indicator(typedArray.getDimensionPixelSize(R.styleable.IndicatorTriangleView_height_indicator, ScreenUtils.dpAdapt(context,6)));
        indicator.setColor_indicator(typedArray.getColor(R.styleable.IndicatorTriangleView_color_indicator, 0xffe45540));

        indicator.setWidth_indicator(0);
        typedArray.recycle();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height=getHeight();
    }

    @Override
    public <T extends View> T getView() {
        return (T) this;
    }

    @Override
    public Indicator getIndicator() {
        return indicator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.moveTo(indicator.getProgress(), height);// 此点为多边形的起点
        path.lineTo(indicator.getProgress() + indicator.getWidth_indicator() * 1f / 2, height - indicator.getHeight_indicator());
        path.lineTo(indicator.getProgress() + indicator.getWidth_indicator(), height);
        path.close(); // 使这些点构成封闭的多边形
        indicator.getPaint_indicator().setStyle(Paint.Style.FILL);
        canvas.drawPath(path, indicator.getPaint_indicator());
    }
}
