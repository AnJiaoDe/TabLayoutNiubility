package com.cy.tablayoutniubility;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/9 13:25
 * @UpdateUser:
 * @UpdateDate: 2020/8/9 13:25
 * @UpdateRemark:
 * @Version: 1.0
 */
public class TabMediatorMulti<T> {
    private TabLayoutMulti tabLayoutMulti;

    public TabMediatorMulti(TabLayoutMulti tabLayoutMulti) {
        this.tabLayoutMulti = tabLayoutMulti;
    }

    public ITabAdapter setAdapter(ViewPager2 viewPager2, BaseFragPageAdapterVp2 baseFragPageAdapterVp2) {
        if (tabLayoutMulti.isScrollable()) {
            TabMediatorVp2 tabMediatorVp2 = new TabMediatorVp2<T>((TabLayoutScroll) tabLayoutMulti.getTabLayout(), viewPager2);
            return tabMediatorVp2.setAdapter((FragPageAdapterVp2) baseFragPageAdapterVp2);
        } else {
            TabMediatorVp2NoScroll tabMediatorVp2NoScroll = new TabMediatorVp2NoScroll<T>((TabLayoutNoScroll) tabLayoutMulti.getTabLayout(), viewPager2);
            return tabMediatorVp2NoScroll.setAdapter((FragPageAdapterVp2NoScroll) baseFragPageAdapterVp2);
        }
    }

    public ITabAdapter setAdapter(ViewPager viewPager, BaseFragPageAdapterVp baseFragPageAdapterVp) {
        if (tabLayoutMulti.isScrollable()) {
            TabMediatorVp tabMediatorVp = new TabMediatorVp<T>((TabLayoutScroll) tabLayoutMulti.getTabLayout(), viewPager);
            return tabMediatorVp.setAdapter((FragPageAdapterVp) baseFragPageAdapterVp);
        } else {
            TabMediatorVpNoScroll tabMediatorVpNoScroll = new TabMediatorVpNoScroll<T>((TabLayoutNoScroll) tabLayoutMulti.getTabLayout(), viewPager);
            return tabMediatorVpNoScroll.setAdapter((FragPageAdapterVpNoScroll) baseFragPageAdapterVp);
        }
    }
}
