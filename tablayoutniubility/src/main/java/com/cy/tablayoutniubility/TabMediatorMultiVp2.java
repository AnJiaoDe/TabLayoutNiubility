package com.cy.tablayoutniubility;

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
public class TabMediatorMultiVp2 {
    private TabLayoutMulti tabLayoutMulti;

    public TabMediatorMultiVp2(TabLayoutMulti tabLayoutMulti) {
        this.tabLayoutMulti = tabLayoutMulti;
    }

    public ITabAdapter setAdapter(ViewPager2 viewPager2, IBaseTabPageAdapter tabPageAdapter) {
        if (tabLayoutMulti.isScrollable()) {
            TabMediatorVp2 tabMediatorVp2 = new TabMediatorVp2((TabLayoutScroll) tabLayoutMulti.getTabLayout(), viewPager2);
            return tabMediatorVp2.setAdapter((ITabPageAdapterVp2) tabPageAdapter);
        } else {
            TabMediatorVp2NoScroll tabMediatorVp2NoScroll = new TabMediatorVp2NoScroll((TabLayoutNoScroll) tabLayoutMulti.getTabLayout(), viewPager2);
            return tabMediatorVp2NoScroll.setAdapter((ITabPageAdapterVp2NoScroll) tabPageAdapter);
        }
    }

}
