package com.cy.tablayoutniubility;

import androidx.viewpager2.widget.ViewPager2;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/7/29 18:42
 * @UpdateUser:
 * @UpdateDate: 2020/7/29 18:42
 * @UpdateRemark:
 * @Version:
 */
public class TabMediatorVp2NoScroll<T> implements ITabMediator {
    private TabLayoutNoScroll tabLayout;
    private ViewPager2 viewPager2;
    private TabAdapterNoScroll<T> tabAdapter;
    private int position_scroll_last = 0;
    private int diff = 0;
    private int diff_click = 0;
    private int toScroll = 0;
    private boolean scrolledByClick = false;
    private boolean op_click_last = false;
    private int click_position_last = -1;

    public TabMediatorVp2NoScroll(TabLayoutNoScroll tabLayout, final ViewPager2 viewPager2) {
        this.tabLayout = tabLayout;
        this.viewPager2 = viewPager2;
    }

    public TabAdapterNoScroll<T> setAdapter(final FragPageAdapterVp2NoScroll<T> fragmentPageAdapter) {
        tabAdapter = new TabAdapterNoScroll<T>() {
            @Override
            public void bindDataToView(TabNoScrollViewHolder holder, int position, T bean, boolean isSelected) {
                fragmentPageAdapter.bindDataToTab(holder, position, bean, isSelected);
            }

            @Override
            public int getItemLayoutID(int position, T bean) {
                return fragmentPageAdapter.getTabLayoutID(position, bean);
            }

            @Override
            public void onItemClick(TabNoScrollViewHolder holder, int position, T bean) {
                //点击tabLayout的item,会先回调onPageSelected,然后回调onPageScrolled
                //标志：tablayout的滑动是由点击item触发的
                scrolledByClick = true;
//                position_selected_last = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(position);
                //让indicator立马指向currentItem
                TabNoScrollViewHolder viewHolder = tabLayout.getTabNoScrollView().getViewHolder(viewPager2.getCurrentItem());
                tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                        .setProgress((int) (viewHolder.itemView.getLeft()
                                + viewHolder.itemView.getWidth() * 1f / 2
                                - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2));
                fragmentPageAdapter.onTabClick(holder, position, bean);
            }
        };

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //通知tabAdapter更新选中项
                tabAdapter.setPositionSelected(viewPager2.getCurrentItem());
            }

            /**注意：滑动很快的时候，即使到了另外的page,positionOffsetPixels不一定会出现0
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                int centerX = (int) (tabLayout.getWidth() * 1f / 2);
                //点击item后，onPageSelected先回调，然后还会继续回调onPageScrolled，直到onPageScrolled=position_selected，从page index 滑动到 page index+1，
                //position == viewPager2.getCurrentItem() - 1说明点击的item在当前position之后
                //position == viewPager2.getCurrentItem()说明点击的item在当前position之前
                //viewpager滑动中，才处理，
                if (scrolledByClick) {
                    if ((position == viewPager2.getCurrentItem() - 1 || position == viewPager2.getCurrentItem())) {
                        TabNoScrollViewHolder viewHolder = tabLayout.getTabNoScrollView().getViewHolder(viewPager2.getCurrentItem());
                        //indicator想要指向正中间，计算TabLayout需要滑动的距离
                        if (diff_click == 0)
                            diff_click = (int) (viewHolder.itemView.getLeft() + viewHolder.itemView.getWidth() * 1f / 2 - centerX);
                    }
                    position_scroll_last = position;
                    return;
                }
                /**
                 * 手指左右滑动Viewpager，触发下面所有代码
                 */
                TabNoScrollViewHolder viewHolder = tabLayout.getTabNoScrollView().getViewHolder(position);
                int width_half = (int) (viewHolder.itemView.getWidth() * 1f / 2);
                int left = viewHolder.itemView.getLeft();
                TabNoScrollViewHolder viewHolder_behind = tabLayout.getTabNoScrollView().getViewHolder(position + 1);
                if (position == 0) {
                    //TabLayout刚显示，indicator会指向第0个item
                    diff = 0;
                    //计算indicator指向下一个item需要滑动的距离
                   if(viewHolder_behind!=null) toScroll = (int) (width_half
                            + viewHolder_behind.itemView.getWidth() * 1f / 2);
                } else if (position_scroll_last < position) {
                    //说明从page index 滑动到了page index+1,
                    //indicator想要指向正中间，计算TabLayout需要滑动的距离
                    if(viewHolder_behind!=null){
                        diff = (int) (viewHolder_behind.itemView.getLeft() + viewHolder_behind.itemView.getWidth() * 1f / 2 - centerX);
                        //下一个item都在正中间的前面，无需滑动，而且可以避免出现负数导致recyclerView抖动
                        if (diff < 0) diff = 0;
                        //计算indicator指向下一个item需要滑动的距离
                        toScroll = (int) (width_half
                                + viewHolder_behind.itemView.getWidth() * 1f / 2);
                    }

                } else if (position_scroll_last > position) {
                    //说明从page index 滑动到了page index-1
                    //indicator想要指向正中间，计算TabLayout需要滑动的距离
                    diff = (int) (left + width_half - centerX);
                    //position的item在正中间的后面，无需滑动，而且可以避免出现正数导致recyclerView抖动
                    if (diff > 0) diff = 0;
                    //获取上次tablayout的偏移量，永远<=0
                    if (viewHolder_behind != null)
                        //计算indicator指向position的item需要滑动的距离
                        toScroll = (int) (width_half
                                + viewHolder_behind.itemView.getWidth() * 1f / 2);
                } else if (op_click_last) {
                    //如果position_scroll_last==position,并且上次操作是点击item,
                    if (position == click_position_last&& viewHolder_behind!=null) {
                        //说明现在是正要从page index 滑动到page index+1
                        //indicator想要指向正中间，计算TabLayout需要滑动的距离
                        diff = (int) (viewHolder_behind.itemView.getLeft() + viewHolder_behind.itemView.getWidth() * 1f / 2 - centerX);
                        //计算indicator指向position的item需要滑动的距离
                        toScroll = (int) (width_half
                                + viewHolder_behind.itemView.getWidth() * 1f / 2);
                    }
                    op_click_last = false;
                }
                //计算Width_indicator,Width_indicator由小变大再变小，2个item中间时最大
                tabLayout.getIndicatorView().getIndicator().setWidth_indicator(Math.max(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected(),
                        (int) (tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected() +
                                (positionOffset == 0 ? 0 : tabLayout.getIndicatorView().getIndicator().getWidth_indicator_max() * (0.5 - Math.abs(0.5 - positionOffset))))))
                        .setProgress((int) (left
                                + width_half
                                - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2
                                + (toScroll * positionOffset)));

                if (toScroll != 0)
                    //手指往左滑动，positionOffset由小变大
                    //手指往右滑动，positionOffset由大变小
                    if (viewHolder_behind != null)
                        fragmentPageAdapter.onTabScrolled(viewHolder, position, false, 1 - positionOffset,
                                viewHolder_behind, position + 1, true, positionOffset);
                position_scroll_last = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case SCROLL_STATE_IDLE:
                        //记录上次操作的是点击item
                        if (scrolledByClick) {
                            click_position_last = viewPager2.getCurrentItem();
                            op_click_last = true;
                        }
                        //标志复位
                        scrolledByClick = false;
                        diff_click = 0;
                        break;
                }
            }
        });

        tabLayout.setAdapter(tabAdapter);

        viewPager2.setAdapter(fragmentPageAdapter);

        return tabAdapter;
    }

}
