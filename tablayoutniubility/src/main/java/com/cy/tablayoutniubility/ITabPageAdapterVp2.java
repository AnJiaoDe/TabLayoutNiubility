package com.cy.tablayoutniubility;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/7 11:37
 * @UpdateUser:
 * @UpdateDate: 2020/8/7 11:37
 * @UpdateRemark:
 * @Version:
 */
public interface ITabPageAdapterVp2<T > extends IBaseTabPageAdapter<T,TabViewHolder> {
   public <W extends RecyclerView.Adapter> W getPageAdapter();
}
