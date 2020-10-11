package com.cy.tablayoutniubility;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:27
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class ContainerPageAdapterVp2NoScroll<T> extends BaseContainerPageAdapterVp2<T, TabNoScrollViewHolder>
        implements ITabPageAdapterVp2NoScroll<T> {
    public ContainerPageAdapterVp2NoScroll(ViewPager2 viewPager2) {
        super(viewPager2);
    }

    @Override
    public <W extends RecyclerView.Adapter> W getPageAdapter() {
        return (W) this;
    }
}
