package com.cy.tablayoutniubility;

import androidx.fragment.app.Fragment;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/7 11:37
 * @UpdateUser:
 * @UpdateDate: 2020/8/7 11:37
 * @UpdateRemark:
 * @Version:
 */
public interface IFragmentPageAdapter<T,V> {

    public Fragment createFragment(T bean, int position);

    public void bindDataToTab(TabViewHolder holder, int position, T bean, boolean isSelected);

    public int getTabLayoutID(int position, T bean);

    public void onTabClick(TabViewHolder holder, int position, T bean);

    public void onTabScrolled(TabViewHolder holderCurrent, int positionCurrent,
                              boolean fromLeft2RightCurrent, float positionOffsetCurrent,
                              TabViewHolder holder2, int position2,
                              boolean fromLeft2Right2, float positionOffset2);

    public V getFragmentPageAdapter();

}
