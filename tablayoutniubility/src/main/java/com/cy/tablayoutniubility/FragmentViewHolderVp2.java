package com.cy.tablayoutniubility;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/7 22:01
 * @UpdateUser:
 * @UpdateDate: 2020/10/7 22:01
 * @UpdateRemark:
 * @Version: 1.0
 */
class FragmentViewHolderVp2 extends RecyclerView.ViewHolder {
    private FragmentViewHolderVp2(@NonNull FrameLayout container) {
        super(container);
    }

    @NonNull
    static FragmentViewHolderVp2 create(@NonNull ViewGroup parent) {
        FrameLayout container = new FrameLayout(parent.getContext());
        container.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        container.setId(ViewCompat.generateViewId());
        container.setSaveEnabled(false);
        return new FragmentViewHolderVp2(container);
    }

    @NonNull
    FrameLayout getContainer() {
        return (FrameLayout) itemView;
    }
}
