package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.LongUnaryOperator;


/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/7/14 18:10
 * @UpdateUser:
 * @UpdateDate: 2020/7/14 18:10
 * @UpdateRemark:
 * @Version:
 */
public class TabLayoutScroll extends FrameLayout implements ITabLayout {
    protected HorizontalRecyclerView horizontalRecyclerView;
    protected int space_vertical, space_horizontal;
    protected IIndicatorView indicatorView;

    public TabLayoutScroll(@NonNull Context context) {
        this(context, null);
    }

    public TabLayoutScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        horizontalRecyclerView = new HorizontalRecyclerView(context);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabLayoutScroll);
            setSpace_horizontal(typedArray.getDimensionPixelSize(R.styleable.TabLayoutScroll_space_horizontal,
                    ScreenUtils.dpAdapt(context, 20)));
            setSpace_vertical(typedArray.getDimensionPixelSize(R.styleable.TabLayoutScroll_space_vertical,
                    ScreenUtils.dpAdapt(context, 8)));
            typedArray.recycle();
        } else {
            setSpace_horizontal(ScreenUtils.dpAdapt(context, 20));
            setSpace_vertical(ScreenUtils.dpAdapt(context, 8));
            addTab();
        }
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
        addTab();
    }

    private void addTab(){
        addView(horizontalRecyclerView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
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
        addView(indicatorView.getView());
        return (T) this;
    }

    public TabLayoutScroll setSpace_vertical(int space_vertical) {
        this.space_vertical = space_vertical;
        return this;
    }

    public TabLayoutScroll setSpace_horizontal(int space_horizontal) {
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

    public TabLayoutScroll  setAdapter(TabAdapter tabAdapter) {
        //tab间距
        horizontalRecyclerView.addItemDecoration(new LinearItemDecoration(tabAdapter)
                .setSpace_horizontal(space_horizontal).setSpace_vertical(space_vertical));
        horizontalRecyclerView.setAdapter(tabAdapter);
        return  this;
    }

}
