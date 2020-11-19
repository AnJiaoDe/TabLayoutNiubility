package com.cy.tablayoutniubility;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:27
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseContainerPageAdapterVp<T, V extends IViewHolder> extends SimplePageAdapter<T, V> {
    private ViewPager viewPager;
    private SparseArray<PageContainer> sparseArray_container;
    private SparseArray<Boolean> sparseArray_resume;
    private int position_selected_last = -1;

    public BaseContainerPageAdapterVp(ViewPager viewPager) {
        this.viewPager = viewPager;
        sparseArray_container = new SparseArray<>();
        sparseArray_resume = new SparseArray<>();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                PageContainer pageContainer_last_selected = sparseArray_container.get(position_selected_last);
                if (pageContainer_last_selected != null) pageContainer_last_selected.onStop();

                PageContainer pageContainer_selected = sparseArray_container.get(position);
                if(pageContainer_selected!=null){
                    pageContainer_selected.onResume(sparseArray_resume.get(position) == null || !sparseArray_resume.get(position));
                    sparseArray_resume.put(position, true);
                }
                position_selected_last = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public abstract PageContainer onCreatePageContainer(ViewGroup container, int position, T bean);

    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position, T bean) {
        PageContainer pageContainer = onCreatePageContainer(container, position, bean);
        if(pageContainer.getPageContainerParent()!=null)
            pageContainer.getPageContainerParent().getPageContainerManager().addPageContainer(pageContainer);
        pageContainer.context = container.getContext();
        container.addView(pageContainer.view = pageContainer.onCreateView(LayoutInflater.from(container.getContext()), container),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (position == 0 && position_selected_last == -1) {
            position_selected_last = position;
            pageContainer.onResume(true);
            sparseArray_resume.put(position, true);
        }
        sparseArray_container.put(position, pageContainer);
        return pageContainer;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, T bean, @NonNull Object object) {
        ((PageContainer) object).onDestroyView();
        for(PageContainer pageContainer:((PageContainer) object).getPageContainerManager().getPageContainers()){
            pageContainer.onDestroyView();
        }
        container.removeView(((PageContainer) object).view);
        sparseArray_container.remove(position);
        sparseArray_resume.remove(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((PageContainer) object).view == view;
    }
}
