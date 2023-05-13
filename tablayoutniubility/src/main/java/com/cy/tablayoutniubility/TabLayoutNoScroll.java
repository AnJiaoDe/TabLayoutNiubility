package com.cy.tablayoutniubility;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
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
public class TabLayoutNoScroll extends FrameLayout implements ITabLayout {
    protected TabNoScrollView tabNoScrollView;
    protected IIndicatorView indicatorView;

    public TabLayoutNoScroll(@NonNull Context context) {
        this(context, null);
    }

    public TabLayoutNoScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tabNoScrollView = new TabNoScrollView(context);
        if (attrs == null) addTab();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count > 2)
            throw new RuntimeException("Exception:You must add only one IndicatorView type of IIndicatorView in " + getClass().getName());
        for (int i = 0; i < count; i++) {
            try {
                indicatorView = (IIndicatorView) getChildAt(i);
            } catch (Exception e) {
                continue;
            }
            break;
        }
        if (indicatorView == null)
            throw new RuntimeException("Exception:You must add only one IndicatorView type of IIndicatorView in " + getClass().getName());
        addTab();
    }

    private void addTab() {
        addView(tabNoScrollView, getChildCount(), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    public <T extends View> T getView() {
        return (T) this;
    }

    public IIndicatorView getIndicatorView() {
        return indicatorView;
    }

    @Override
    public <T extends ITabLayout> T setIndicatorView(IIndicatorView indicatorView) {
        if (this.indicatorView != null) removeView(this.indicatorView.getView());
        removeView(indicatorView.getView());
        this.indicatorView = indicatorView;
        addView(indicatorView.getView(),0);
        return (T) this;
    }

    public TabNoScrollView getTabNoScrollView() {
        return tabNoScrollView;
    }

    public TabLayoutNoScroll setAdapter(TabAdapterNoScroll tabAdapter) {
        tabNoScrollView.setAdapter(tabAdapter);
        return this;
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
