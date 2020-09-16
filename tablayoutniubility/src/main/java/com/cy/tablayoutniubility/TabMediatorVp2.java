package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
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
public class TabMediatorVp2<T> implements ITabMediator {
    private TabLayoutScroll tabLayout;
    private ViewPager2 viewPager2;
    private int position_scroll_last = 0;
    private int diff = 0;
    private float diff_click = -1;
    private int toScroll = 0;
    private int offsetX_last = 0;
    private int offsetX_last_click = -1;
    private int offsetX_touch = 0;
    private boolean rvScrolledByVp = false;
    private boolean rvScrolledByTouch = false;
    private boolean scrolledByClick = false;
    private boolean op_click_last = false;
    private int click_position_last = -1;
    private TabAdapter<T> tabAdapter;
    private FragPageAdapterVp2<T> fragmentPageAdapter;

    public TabMediatorVp2(final TabLayoutScroll tabLayout, final ViewPager2 viewPager2) {
        this.tabLayout = tabLayout;
        this.viewPager2 = viewPager2;
        tabLayout.getHorizontalRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果是手指滑动tabLayout，需要记录滑动的距离
                if (!rvScrolledByVp) {
                    rvScrolledByTouch = true;
                    offsetX_touch -= dx;
                }
                //indicator需要跟着滑动
                RecyclerView.ViewHolder viewHolder = tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(viewPager2.getCurrentItem());
                if (viewHolder != null) {
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                            .setProgress((int) (viewHolder.itemView.getLeft()
                                    + viewHolder.itemView.getWidth() * 1f / 2
                                    - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2));
                } else {
                    //不可见，width_indicator为0
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                }

            }
        });
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
                //说明上次手指滑动了tabLayout，现在手指滑动viewpager,需要将tablayout复位
                if (rvScrolledByTouch && offsetX_touch != 0) {
                    tabLayout.getHorizontalRecyclerView().stopScroll();
                    //标志不是由手指滑动tablayout
                    rvScrolledByVp = true;
                    tabLayout.getHorizontalRecyclerView().scrollBy(offsetX_touch, 0);
                    rvScrolledByVp = false;
                    //立刻复位
                    rvScrolledByTouch = false;
                    offsetX_touch = 0;
                    //这里不能修改position_scroll_last，因为只要上次手指滑动了tablayout,然后手指滑动viewapger，onPageScrolled会被回调多次
                    //在后面去修改position_scroll_last即可
//                    position_scroll_last = position;
                    return;
                }
                //点击item后，onPageSelected先回调，然后还会继续回调onPageScrolled，直到onPageScrolled=position_selected，从page index 滑动到 page index+1，
                //position == viewPager2.getCurrentItem() - 1说明点击的item在当前position之后
                //position == viewPager2.getCurrentItem()说明点击的item在当前position之前
                //viewpager滑动中，才处理，
                if (scrolledByClick) {
                    if ((position == viewPager2.getCurrentItem() - 1 || position == viewPager2.getCurrentItem())) {
                        RecyclerView.ViewHolder viewHolder = tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(viewPager2.getCurrentItem());
                        if (viewHolder != null) {
                            //indicator想要指向正中间，计算TabLayout需要滑动的距离
                            if (diff_click == -1)
                                diff_click = (viewHolder.itemView.getLeft() + viewHolder.itemView.getWidth() * 1f / 2 - centerX);
                            //获取tablayout的偏移量，永远<=0
                            if (offsetX_last_click == -1)
                                offsetX_last_click = tabLayout.getHorizontalRecyclerView().getOffsetX();
                            if (positionOffset != 0) {
                                //scrollBy调用一次，onScrolled回调一次
                                //标志不是由手指滑动tablayout
                                rvScrolledByVp = true;
                                if (diff_click > 0) {
                                    tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last_click - (diff_click * positionOffset)), 0);
                                } else if (diff_click < 0) {
                                    tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last_click - (diff_click * (1 - positionOffset))), 0);
                                }
                                rvScrolledByVp = false;
                            }

                        } else {
                            //不可见，width_indicator为0
                            tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                        }

                    }
                    position_scroll_last = position;
                    return;
                }
                /**
                 * 手指左右滑动Viewpager，触发下面所有代码
                 */
                TabViewHolder viewHolder = (TabViewHolder) tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(position);
                if (viewHolder != null) {
                    int width_half = (int) (viewHolder.itemView.getWidth() * 1f / 2);
                    int left = viewHolder.itemView.getLeft();
                    int space = tabLayout.getHorizontalRecyclerView().getItemDecoration().getSpace_horizontal();
                    TabViewHolder viewHolder_behind = (TabViewHolder) tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(position + 1);
                    if (position == 0) {
                        //TabLayout刚显示，indicator会指向第0个item
                        diff = 0;
                        offsetX_last = 0;
                        if (viewHolder_behind != null)
                            //计算indicator指向下一个item需要滑动的距离
                            toScroll = (int) (width_half
                                    + space
                                    + viewHolder_behind.itemView.getWidth() * 1f / 2);
                    } else if (position_scroll_last < position) {
                        //说明从page index 滑动到了page index+1,
                        if (viewHolder_behind != null) {
                            //indicator想要指向正中间，计算TabLayout需要滑动的距离
                            diff = (int) (viewHolder_behind.itemView.getLeft() + viewHolder_behind.itemView.getWidth() * 1f / 2 - centerX);
                            //下一个item都在正中间的前面，无需滑动，而且可以避免出现负数导致recyclerView抖动
                            if (diff < 0) diff = 0;
                            //获取上次tablayout的偏移量，永远<=0
                            offsetX_last = tabLayout.getHorizontalRecyclerView().getOffsetX();
                            //计算indicator指向下一个item需要滑动的距离
                            toScroll = (int) (width_half
                                    + space
                                    + viewHolder_behind.itemView.getWidth() * 1f / 2);
                        }

                    } else if (position_scroll_last > position) {
                        //说明从page index 滑动到了page index-1
                        //indicator想要指向正中间，计算TabLayout需要滑动的距离
                        diff = left + width_half - centerX;
                        //position的item在正中间的后面，无需滑动，而且可以避免出现正数导致recyclerView抖动
                        if (diff > 0) diff = 0;
                        //获取上次tablayout的偏移量，永远<=0
                        offsetX_last = tabLayout.getHorizontalRecyclerView().getOffsetX();
                        if (viewHolder_behind != null)
                            //计算indicator指向position的item需要滑动的距离
                            toScroll = (int) (width_half
                                    + space
                                    + viewHolder_behind.itemView.getWidth() * 1f / 2);
                    } else if (op_click_last) {
                        //如果position_scroll_last==position,并且上次操作是点击item,
                        if (position == click_position_last) {
                            //说明现在是正要从page index 滑动到page index+1
                            if (viewHolder_behind != null) {
                                //indicator想要指向正中间，计算TabLayout需要滑动的距离
                                diff = (int) (viewHolder_behind.itemView.getLeft() + viewHolder_behind.itemView.getWidth() * 1f / 2 - centerX);
                                //获取上次tablayout的偏移量，永远<=0
                                offsetX_last = tabLayout.getHorizontalRecyclerView().getOffsetX();
                                //计算indicator指向position的item需要滑动的距离
                                toScroll = (int) (width_half
                                        + space
                                        + viewHolder_behind.itemView.getWidth() * 1f / 2);
                            }
                        }
                        op_click_last = false;
                    }
                    //diff==0,无需滑动，positionOffset==0,无需滑动，当前position和上次滑动的position相等，才执行滑动操作
                    if (diff != 0 && positionOffset != 0 && position_scroll_last == position) {
                        //标志，tabLayout滑动，不是因为手指滑动tablayout导致的
                        rvScrolledByVp = true;
                        if (diff > 0) {
                            //scrollBy调用一次，onScrolled回调一次
                            //手指往左滑动，positionOffset由小变大
                            tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last - (diff * positionOffset)), 0);
                        } else {
                            //手指往右滑动，positionOffset由大变小
                            tabLayout.getHorizontalRecyclerView().scrollTo((int) (offsetX_last - (diff * (1 - positionOffset))), 0);
                        }
                        //标志复位
                        rvScrolledByVp = false;
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

                } else {
                    //viewpager嵌套viewpager的时候，内层viewpager向右滑动了以后又向左滑动，会导致tablayout
                    //position对应的item不可见，所以要滑动到对应的position
                    tabLayout.getHorizontalRecyclerView().scrollToPosition(position);
                    viewHolder = (TabViewHolder) tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(position);
                    //scrollToPosition一调用，不会立马滑动完毕，所以还会有存在null的时候，
                    if (viewHolder != null) {
                        int width_half = (int) (viewHolder.itemView.getWidth() * 1f / 2);
                        int left = viewHolder.itemView.getLeft();
                        tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                                .setProgress((int) (left
                                        + width_half
                                        - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() * 1f / 2));
                    } else {
                        tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                    }

                }

                position_scroll_last = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                switch (state) {
                    case SCROLL_STATE_IDLE:
                        //记录上次操作的是点击item
                        if (scrolledByClick) {
                            click_position_last = viewPager2.getCurrentItem();
                            op_click_last = true;
                        }
                        //标志复位
                        scrolledByClick = false;
                        diff_click = -1;
                        offsetX_last_click = -1;
                        break;
                }
            }
        });
        tabAdapter = new TabAdapter<T>() {
            @Override
            public void bindDataToView(TabViewHolder holder, int position, T bean, boolean isSelected) {
                fragmentPageAdapter.bindDataToTab(holder, position, bean, isSelected);
            }

            @Override
            public int getItemLayoutID(int position, T bean) {
                return fragmentPageAdapter.getTabLayoutID(position, bean);
            }

            @Override
            public void onItemClick(TabViewHolder holder, int position, T bean) {
                //点击tabLayout的item,会先回调onPageSelected,然后回调onPageScrolled
                //标志复位
                rvScrolledByTouch = false;
                offsetX_touch = 0;
                //标志：tablayout的滑动是由点击item触发的
                scrolledByClick = true;
                viewPager2.setCurrentItem(position);
                //让indicator立马指向currentItem
                RecyclerView.ViewHolder viewHolder = tabLayout.getHorizontalRecyclerView().findViewHolderForAdapterPosition(viewPager2.getCurrentItem());
                if (viewHolder != null) {

                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(tabLayout.getIndicatorView().getIndicator().getWidth_indicator_selected())
                            .setProgress((int) (viewHolder.itemView.getLeft()
                                    + viewHolder.itemView.getWidth() * 1f / 2
                                    - tabLayout.getIndicatorView().getIndicator().getWidth_indicator() / 2));

                } else {
                    //不可见，width_indicator为0
                    tabLayout.getIndicatorView().getIndicator().setWidth_indicator(0).invalidate();
                }
                fragmentPageAdapter.onTabClick(holder, position, bean);
            }
        };

    }

    public TabAdapter<T> setAdapter(FragPageAdapterVp2<T> fragPageAdapter) {
        fragmentPageAdapter = fragPageAdapter;
        tabLayout.setAdapter(tabAdapter);
        viewPager2.setAdapter(fragmentPageAdapter);
        return tabAdapter;
    }

}
