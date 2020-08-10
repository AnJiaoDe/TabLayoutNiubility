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
public class IndicatorNullView extends View implements IIndicatorView {
    private Indicator indicator;

    public IndicatorNullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        indicator=new Indicator(this);

        indicator.setWidth_indicator_selected(0);
        indicator.setWidth_indicator_max(0);
        indicator.setHeight_indicator(0);
        indicator.setColor_indicator(0x00000000);
        indicator.setWidth_indicator(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public <T extends View> T getView() {
        return (T) this;
    }

    @Override
    public Indicator getIndicator() {
        return indicator;
    }

}
