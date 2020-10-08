package com.cy.tablayoutniubility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:27
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseContainerPageAdapterVp<T, V extends IViewHolder> extends SimplePagerAdapter<T, V> {

    public abstract PageContainer onCreatePageContainer(ViewGroup container, int position, T bean);

    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position, T bean) {
        PageContainer pageContainer = onCreatePageContainer(container, position, bean);
        container.addView(pageContainer.view=pageContainer.onCreateView(LayoutInflater.from(container.getContext()), container),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return pageContainer;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, T bean, @NonNull Object object) {
        ((PageContainer) object).onDestroyView();
        container.removeView(((PageContainer) object).view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((PageContainer) object).view == view;
    }
}
