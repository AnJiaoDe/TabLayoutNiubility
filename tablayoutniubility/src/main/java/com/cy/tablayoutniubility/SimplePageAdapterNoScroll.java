package com.cy.tablayoutniubility;

import androidx.viewpager.widget.PagerAdapter;

public abstract class SimplePageAdapterNoScroll<T> extends BaseSimplePageAdapter<T, TabNoScrollViewHolder> implements ITabPageAdapterVpNoScroll<T> {
    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}