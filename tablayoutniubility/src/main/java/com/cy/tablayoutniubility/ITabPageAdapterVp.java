package com.cy.tablayoutniubility;

import androidx.viewpager.widget.PagerAdapter;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/7 11:37
 * @UpdateUser:
 * @UpdateDate: 2020/8/7 11:37
 * @UpdateRemark:
 * @Version:
 */
public interface ITabPageAdapterVp<T> extends IBaseTabPageAdapter<T,TabViewHolder> {
   public <W extends PagerAdapter> W getPageAdapter();
}
