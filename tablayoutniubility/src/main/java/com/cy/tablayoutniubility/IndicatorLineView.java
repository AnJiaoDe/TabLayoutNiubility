package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
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
    private boolean isIndicatorCenter = false;

    public IndicatorLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        indicator = new Indicator(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorLineView);

        indicator.setWidth_indicator_selected(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_cy_width_indicator_selected, ScreenUtils.dpAdapt(context, 20)));
        indicator.setWidth_indicator_max(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_cy_width_indicator_max, ScreenUtils.dpAdapt(context, 60)));
        indicator.setHeight_indicator(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_cy_height_indicator, ScreenUtils.dpAdapt(context, 3)));
        indicator.setColor_indicator(typedArray.getColor(R.styleable.IndicatorLineView_cy_color_indicator, 0xffe45540));

        int style = typedArray.getInt(R.styleable.IndicatorLineView_cy_paint_style, 0);
        switch (style) {
            case 0:
                indicator.setPaintStyle(Paint.Style.FILL);
                break;
            case 1:
                indicator.setPaintStyle(Paint.Style.STROKE);
                break;
            case 2:
                indicator.setPaintStyle(Paint.Style.FILL_AND_STROKE);
                break;
        }
        indicator.setPaintStrokeWidth(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_cy_paint_stroke_width, ScreenUtils.dpAdapt(context, 1)));

        indicator.setMax2Width(typedArray.getBoolean(R.styleable.IndicatorLineView_cy_isMax2Width, false));
        indicator.setMax2Height(typedArray.getBoolean(R.styleable.IndicatorLineView_cy_isMax2Height, false));
        isIndicatorCenter = typedArray.getBoolean(R.styleable.IndicatorLineView_cy_isIndicatorCenter, isIndicatorCenter);
        setRadius_indicator(typedArray.getDimensionPixelSize(R.styleable.IndicatorLineView_cy_radius_indicator, ScreenUtils.dpAdapt(context, 2)));

        indicator.setWidth_indicator(0);

        typedArray.recycle();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int top = isIndicatorCenter ? (int) ((height - indicator.getHeight_indicator()) * 0.5f) : height - indicator.getHeight_indicator();
        canvas.drawRoundRect(indicator.getProgress(), top,
                indicator.getProgress() + indicator.getWidth_indicator(), top + indicator.getHeight_indicator(),
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
