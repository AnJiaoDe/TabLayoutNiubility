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

    public BaseContainerPageAdapterVp(final ViewPager viewPager) {
        this.viewPager = viewPager;
        sparseArray_container = new SparseArray<>();
        sparseArray_resume = new SparseArray<>();

        viewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                clear();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                PageContainer pageContainer_last_selected = sparseArray_container.get(position_selected_last);
                if (pageContainer_last_selected != null) pageContainer_last_selected.onStop();

                PageContainer pageContainer_selected = sparseArray_container.get(position);
                if (pageContainer_selected != null) {
                    boolean isFirstResume = sparseArray_resume.get(position) == null || !sparseArray_resume.get(position);
                    pageContainer_selected.onResume(isFirstResume);

                    if (pageContainer_selected.getPageContainerChildManager().getPageContainerChildResumedLast() != null)
                        pageContainer_selected.getPageContainerChildManager().getPageContainerChildResumedLast().onResume(false);

                    if (pageContainer_selected.getPageContainerParent() != null)
                        pageContainer_selected.getPageContainerParent().getPageContainerChildManager().setPageContainerChildResumedLast(pageContainer_selected);
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
        if (pageContainer.getPageContainerParent() != null)
            pageContainer.getPageContainerParent().getPageContainerChildManager().addPageContainer(pageContainer);
        pageContainer.context = container.getContext();
        container.addView(pageContainer.view = pageContainer.onCreateView(LayoutInflater.from(container.getContext()), container),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (position == 0 && position_selected_last == -1) {
            position_selected_last = position;
            pageContainer.onResume(true);
            if (pageContainer.getPageContainerParent() != null)
                pageContainer.getPageContainerParent().getPageContainerChildManager().setPageContainerChildResumedLast(pageContainer);
//            for (int i = 0; i < pageContainer.getPageContainerChildManager().getPageContainers().size(); i++) {
//                if (i == 0)
//                    pageContainer.getPageContainerChildManager().getPageContainers().get(i).onResume(true);
//            }
            sparseArray_resume.put(position, true);
        }
        sparseArray_container.put(position, pageContainer);
        return pageContainer;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, T bean, @NonNull Object object) {
        ((PageContainer) object).onDestroyView();
        for (PageContainer pageContainer : ((PageContainer) object).getPageContainerChildManager().getPageContainers()) {
            pageContainer.onDestroyView();
        }
        ((PageContainer) object).getPageContainerChildManager().clear();
        container.removeView(((PageContainer) object).view);
        sparseArray_container.remove(position);
        sparseArray_resume.remove(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((PageContainer) object).view == view;
    }

    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clear() {
        for (int i = 0; i < sparseArray_container.size(); i++) {
            PageContainer pageContainer = sparseArray_container.get(sparseArray_container.keyAt(i));
            pageContainer.onDestroyView();
            pageContainer.getPageContainerChildManager().clear();
            sparseArray_container.remove(sparseArray_container.keyAt(i));
            sparseArray_resume.remove(sparseArray_container.keyAt(i));
        }
        return super.clear();
    }
}
