package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public abstract class SimplePageAdapter<T> extends BaseSimplePageAdapter<T, TabViewHolder> implements ITabPageAdapterVp<T> {
    public SimplePageAdapter(ViewPager viewPager) {
        super(viewPager);
    }

    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}