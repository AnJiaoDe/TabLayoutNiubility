package com.cy.tablayoutniubility;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:27
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseContainerPageAdapterVp2<T, V extends IViewHolder> extends SimplePageAdapterVp2<T, V> {
    private ViewPager2 viewPager2;
    private SparseArray<PageContainer> sparseArray_container;
    private SparseArray<Boolean> sparseArray_resume;
    private int position_selected_last = -1;

    public BaseContainerPageAdapterVp2(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
        sparseArray_container = new SparseArray<>();
        sparseArray_resume = new SparseArray<>();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @NonNull
    @Override
    public final BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FrameLayout container = new FrameLayout(parent.getContext());
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return new BaseViewHolder(container);
    }

    @Override
    public final int getItemViewType(int position) {
        return position;
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        FrameLayout frameLayout= (FrameLayout) holder.itemView;
        PageContainer pageContainer = onCreatePageContainer(frameLayout, position, getList_bean().get(position));
        if(pageContainer.getPageContainerParent()!=null)
            pageContainer.getPageContainerParent().getPageContainerChildManager().addPageContainer(pageContainer);
        pageContainer.context = frameLayout.getContext();
        frameLayout.addView(pageContainer.view = pageContainer.onCreateView(LayoutInflater.from(frameLayout.getContext()), frameLayout),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        sparseArray_container.put(position, pageContainer);
    }

    @Override
    public final void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        sparseArray_container.get(holder.getAdapterPosition()).onDestroyView();
        for(PageContainer pageContainer:sparseArray_container.get(holder.getAdapterPosition()).getPageContainerChildManager().getPageContainers()){
            pageContainer.onDestroyView();
        }
        sparseArray_container.get(holder.getAdapterPosition()).getPageContainerChildManager().clear();
        ((FrameLayout) holder.itemView).removeAllViews();
        sparseArray_container.remove(holder.getAdapterPosition());
        sparseArray_resume.remove(holder.getAdapterPosition());
    }

    @Override
    public final void bindDataToView(BaseViewHolder holder, int position, T bean, boolean isSelected) {

    }

    @Override
    public final int getItemLayoutID(int position, T bean) {
        return 0;
    }

    @Override
    public final void onItemClick(BaseViewHolder holder, int position, T bean) {

    }

    @Override
    public void onTabClick(V holder, int position, T bean) {

    }

    @Override
    public void onTabScrolled(V holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent, V holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {

    }
}
