package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

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
public class TabLayoutNoScroll extends FrameLayout {
    protected TabNoScrollView tabNoScrollView;
    protected IIndicatorView indicatorView;

    public TabLayoutNoScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tabNoScrollView = new TabNoScrollView(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count > 1)
            throw new RuntimeException("Exception:You must add only one IndicatorView type of IIndicatorView in " + getClass().getName());
        try {
            indicatorView = (IIndicatorView) getChildAt(0);
        } catch (Exception e) {
            throw new RuntimeException("Exception:You must add only one IndicatorView type of IIndicatorView in " + getClass().getName());
        }
        addView(tabNoScrollView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public IIndicatorView getIndicatorView() {
        return indicatorView;
    }

    public TabNoScrollView getTabNoScrollView() {
        return tabNoScrollView;
    }

    public TabLayoutNoScroll setAdapter(TabNoScrollAdapter tabAdapter) {
        tabNoScrollView.setAdapter(tabAdapter);
        return this;
    }

}
