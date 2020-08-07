package com.cy.tablayoutniubility;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/6/13 16:20
 * @UpdateUser:
 * @UpdateDate: 2020/6/13 16:20
 * @UpdateRemark:
 * @Version:
 */
public abstract class FragmentPageAdapterVpNoScroll<T> extends FragmentPageAdapterVp<T> {

    public FragmentPageAdapterVpNoScroll(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public final void bindDataToTab(TabViewHolder holder, int position, T bean, boolean isSelected) {
        TabFixedOuter tabFixedOuter= (TabFixedOuter) holder.itemView;
        tabFixedOuter.setItemCount(getCount());
        tabFixedOuter.addView(LayoutInflater.from(tabFixedOuter.getContext())
                .inflate(getTabInnerLayoutID(position,bean),tabFixedOuter,false));
        bindDataToTabInner(holder,position,bean,isSelected);
    }
    @Override
    public final int getTabLayoutID(int position, T bean) {
        return R.layout.tabniubility_item_tab_no_scroll;
    }

    public abstract void bindDataToTabInner(TabViewHolder holder, int position, T bean, boolean isSelected) ;

    public abstract int getTabInnerLayoutID(int position, T bean);


}
