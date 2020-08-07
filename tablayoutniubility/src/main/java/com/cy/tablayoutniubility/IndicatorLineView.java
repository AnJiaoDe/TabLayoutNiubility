package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/2 21:43
 * @UpdateUser:
 * @UpdateDate: 2020/8/2 21:43
 * @UpdateRemark:
 * @Version: 1.0
 */
public class IndicatorLineView extends View implements IIndicatorView {
    protected int height;
    protected int radius_indicator;
    private Indicator indicator;

    public IndicatorLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        indicator=new Indicator(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorLineView);

        indicator.setWidth_indicator_selected(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_width_indicator_selected, ScreenUtils.dpAdapt(context,20)));
        indicator.setWidth_indicator_max(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_width_indicator_max, ScreenUtils.dpAdapt(context,60)));
        indicator.setHeight_indicator(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_height_indicator, ScreenUtils.dpAdapt(context,3)));
        indicator.setColor_indicator(typedArray.getColor(R.styleable.IndicatorLineView_color_indicator, 0xffe45540));
        setRadius_indicator(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_radius_indicator, ScreenUtils.dpAdapt(context,2)));

        indicator.setWidth_indicator(ScreenUtils.dpAdapt(context,30));

        typedArray.recycle();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height=getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(indicator.getProgress(), height - indicator.getHeight_indicator(), indicator.getProgress() + indicator.getWidth_indicator(), height,
                radius_indicator, radius_indicator, indicator.getPaint_indicator());
    }


    @Override
    public <T extends View> T getView() {
        return (T) this;
    }

    @Override
    public Indicator getIndicator() {
        return indicator;
    }

    public IIndicatorView setRadius_indicator(int radius_indicator) {
        this.radius_indicator = radius_indicator;
        return this;
    }

    public int getRadius_indicator() {
        return radius_indicator;
    }
}
