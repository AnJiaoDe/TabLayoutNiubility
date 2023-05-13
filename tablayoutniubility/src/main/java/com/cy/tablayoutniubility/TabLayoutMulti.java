package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
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
public class TabLayoutMulti extends FrameLayout {
    protected ITabLayout tabLayout;
    protected boolean scrollable = false;
    private IIndicatorView indicatorView;

    public TabLayoutMulti(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabLayoutMulti);
        setScrollable(typedArray.getBoolean(R.styleable.TabLayoutMulti_cy_scrollable, scrollable));
        setSpace_horizontal(typedArray.getDimensionPixelSize(R.styleable.TabLayoutMulti_cy_space_horizontal,
                ScreenUtils.dpAdapt(context, 20)));
        setSpace_vertical(typedArray.getDimensionPixelSize(R.styleable.TabLayoutMulti_cy_space_vertical,
                ScreenUtils.dpAdapt(context, 8)));
        typedArray.recycle();
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

    public boolean isScrollable() {
        return scrollable;
    }

    public TabLayoutMulti setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
        if (tabLayout != null) removeView(tabLayout.getView());
        tabLayout = scrollable ? new TabLayoutScroll(getContext()) : new TabLayoutNoScroll(getContext());
        addTab();
        return this;
    }

    private void addTab() {
        if (indicatorView != null) {
            removeView(indicatorView.getView());
            ViewGroup parent = (ViewGroup) indicatorView.getView().getParent();
            if (parent != null) parent.removeView(indicatorView.getView());
            tabLayout.setIndicatorView(indicatorView);
        }
        removeView(tabLayout.getView());
        addView(tabLayout.getView(), getChildCount(), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }


    public TabLayoutMulti setSpace_vertical(int space_vertical) {
        if (scrollable)
            ((TabLayoutScroll) tabLayout).setSpace_horizontal(space_vertical);
        return this;
    }

    public TabLayoutMulti setSpace_horizontal(int space_horizontal) {
        if (scrollable)
            ((TabLayoutScroll) tabLayout).setSpace_horizontal(space_horizontal);
        return this;
    }

    public int getSpace_vertical() {
        if (scrollable)
            return ((TabLayoutScroll) tabLayout).getSpace_vertical();
        return 0;
    }

    public int getSpace_horizontal() {
        if (scrollable)
            return ((TabLayoutScroll) tabLayout).getSpace_horizontal();
        return 0;
    }

    <T extends ITabLayout> T getTabLayout() {
        if (scrollable) {
            TabLayoutScroll tabLayoutScroll = (TabLayoutScroll) tabLayout;
            return (T) tabLayoutScroll;
        } else {
            TabLayoutNoScroll tabLayoutNoScroll = (TabLayoutNoScroll) tabLayout;
            return (T) tabLayoutNoScroll;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

}
