package com.cy.tablayoutniubility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:27
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class ContainerPageAdapterVpNoScroll<T> extends BaseContainerPageAdapterVp<T, TabNoScrollViewHolder>
        implements ITabPageAdapterVpNoScroll<T> {
    public ContainerPageAdapterVpNoScroll(ViewPager viewPager) {
        super(viewPager);
    }

    @Override
    public <W extends PagerAdapter> W getPageAdapter() {
        return (W) this;
    }
}
