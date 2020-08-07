package com.cy.tablayoutniubility;

import android.app.ActionBar;
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
public class TabLayoutNiubility extends FrameLayout {
    protected HorizontalRecyclerView horizontalRecyclerView;
    protected int space_vertical, space_horizontal;
    protected IIndicatorView indicatorView;
    protected TabAdapter tabAdapter;

    public TabLayoutNiubility(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        horizontalRecyclerView = new HorizontalRecyclerView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabLayoutNiubility);
        setSpace_horizontal(typedArray.getDimensionPixelSize(R.styleable.TabLayoutNiubility_space_horizontal,
                ScreenUtils.dpAdapt(context, 20)));
        setSpace_vertical(typedArray.getDimensionPixelSize(R.styleable.TabLayoutNiubility_space_vertical,
                ScreenUtils.dpAdapt(context, 8)));
        typedArray.recycle();
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
        addView(horizontalRecyclerView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public IIndicatorView getIndicatorView() {
        return indicatorView;
    }

    public TabLayoutNiubility setSpace_vertical(int space_vertical) {
        this.space_vertical = space_vertical;
        return this;
    }

    public TabLayoutNiubility setSpace_horizontal(int space_horizontal) {
        this.space_horizontal = space_horizontal;
        return this;
    }

    public int getSpace_vertical() {
        return space_vertical;
    }

    public int getSpace_horizontal() {
        return space_horizontal;
    }

    public HorizontalRecyclerView getHorizontalRecyclerView() {
        return horizontalRecyclerView;
    }

    public TabLayoutNiubility setAdapter(TabAdapter tabAdapter) {
        this.tabAdapter = tabAdapter;
        //tab间距
        horizontalRecyclerView.addItemDecoration(new LinearItemDecoration(tabAdapter)
                .setSpace_horizontal(space_horizontal).setSpace_vertical(space_vertical));
        horizontalRecyclerView.setAdapter(tabAdapter);
        return this;
    }

}
