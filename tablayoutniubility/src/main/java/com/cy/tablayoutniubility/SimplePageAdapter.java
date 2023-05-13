package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public abstract class SimplePageAdapter<T> extends BaseSimplePageAdapter<T, TabViewHolder> implements ITabPageAdapterVp<T> {
    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}