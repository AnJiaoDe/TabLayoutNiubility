package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/6/13 16:20
 * @UpdateUser:
 * @UpdateDate: 2020/6/13 16:20
 * @UpdateRemark:
 * @Version:
 */
public abstract class FragPageAdapterVpNoScroll<T> extends BaseFragPageAdapterVp<T, TabNoScrollViewHolder>
        implements ITabPageAdapterVpNoScroll<T> {

    public FragPageAdapterVpNoScroll(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}