package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
public abstract class FragPageAdapterVp2<T> extends BaseFragPageAdapterVp2<T,TabViewHolder> implements ITabPageAdapterVp2<T>{
    public FragPageAdapterVp2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragPageAdapterVp2(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragPageAdapterVp2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public <W extends RecyclerView.Adapter> W getPageAdapter() {
        return (W) this;
    }
}
