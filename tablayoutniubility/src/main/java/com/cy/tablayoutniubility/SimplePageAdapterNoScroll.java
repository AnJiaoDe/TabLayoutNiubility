package com.cy.tablayoutniubility;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public abstract class SimplePageAdapterNoScroll<T> extends BaseSimplePageAdapter<T, TabNoScrollViewHolder> implements ITabPageAdapterVpNoScroll<T> {
    public SimplePageAdapterNoScroll(ViewPager viewPager) {
        super(viewPager);
    }

    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}