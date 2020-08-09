package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
public abstract class FragPageAdapterVpNoScroll<T> extends BaseFragPageAdapterVp<T,TabNoScrollViewHolder>{

    public FragPageAdapterVpNoScroll(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
}