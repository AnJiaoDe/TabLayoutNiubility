package com.cy.tablayoutniubility;

import androidx.viewpager.widget.ViewPager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/9 13:25
 * @UpdateUser:
 * @UpdateDate: 2020/8/9 13:25
 * @UpdateRemark:
 * @Version: 1.0
 */
public class TabMediatorMultiVp<T> {
    private TabLayoutMulti tabLayoutMulti;

    public TabMediatorMultiVp(TabLayoutMulti tabLayoutMulti) {
        this.tabLayoutMulti = tabLayoutMulti;
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
