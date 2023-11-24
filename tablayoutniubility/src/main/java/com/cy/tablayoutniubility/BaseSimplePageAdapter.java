package com.cy.tablayoutniubility;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseSimplePageAdapter<T, V extends IViewHolder> extends PagerAdapter implements IBaseTabPageAdapter<T, V> {
    protected List<T> list_bean;
    protected SparseArray<ViewPagerHolder> sparseArrayViewPagerHolder;
    protected ViewPager viewPager;
    //    protected ViewPager viewPagerParent;
    protected int positionParent;
    protected int positionSelected=-1;
    protected SparseArray<BaseSimplePageAdapter> sparseArrayChildAdapter;
    protected BaseSimplePageAdapter adapterParent;

    public BaseSimplePageAdapter(ViewPager viewPager) {
        this(viewPager, null, 0);
    }

    public BaseSimplePageAdapter(final ViewPager viewPager, final BaseSimplePageAdapter adapterParent, final int positionParent) {
        this.viewPager = viewPager;
        this.adapterParent = adapterParent;
        this.positionParent = positionParent;
        list_bean = new ArrayList<>();
        sparseArrayViewPagerHolder = new SparseArray<>();
        sparseArrayChildAdapter = new SparseArray<>();
        if (adapterParent != null) adapterParent.putChildAdapter(positionParent, this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                BaseSimplePageAdapter.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(position);
                if (viewPagerHolder != null && position >= 0 && position < list_bean.size()) {
//                    LogUtils.log("onPageSelected自己",position);
                    positionSelected=position;
                    BaseSimplePageAdapter.this.onPageSelected(viewPagerHolder, position, list_bean.get(position));
                    BaseSimplePageAdapter adapterChild = sparseArrayChildAdapter.get(position);
                    if (adapterChild != null) {
//                        LogUtils.log("onPageSelected父通知子",position);
                        adapterChild.notifyPageSelected(adapterChild.getCurrentItem());
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                BaseSimplePageAdapter.this.onPageScrollStateChanged(state);
            }
        });
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(getItemLayoutID(position, list_bean.get(position)), container, false);
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final ViewPagerHolder viewPagerHolder = new ViewPagerHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(viewPagerHolder, position, list_bean.get(position));
            }
        });
        sparseArrayViewPagerHolder.put(position, viewPagerHolder);
        bindDataToView(viewPagerHolder, position, list_bean.get(position));
        if (positionSelected==-1 && (adapterParent == null || adapterParent.getPositionSelected() == positionParent)) {
//            LogUtils.log("onPageSelected自己instanti",position+":"+positionParent);
            positionSelected = position;
            onPageSelected(viewPagerHolder, position, list_bean.get(position));
        }
        return view;
    }

    /**
     * 有且只有3个item同时存在
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        sparseArrayViewPagerHolder.remove(position);
        if (position < 0 || position >= list_bean.size()) return;
        sparseArrayChildAdapter.remove(position);
        onViewRecycled(position, list_bean.get(position));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 是否有缓存
        // view 显示的View
        // object : instantiateItem 返回的标记
        return view == object;
    }


    public abstract int getItemLayoutID(int position, T bean);

    public ViewPagerHolder getViewPagerHolderFromPosition(int position) {
        return sparseArrayViewPagerHolder.get(position);
    }

    public void onViewRecycled(int position, @NonNull T bean) {
    }

    public abstract void bindDataToView(ViewPagerHolder holder, int position, T bean);

    public void onItemClick(ViewPagerHolder holder, int position, T bean) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageScrollStateChanged(int state) {

    }

    public void onPageSelected(ViewPagerHolder holder, int position, @NonNull T bean) {
    }

    public void putChildAdapter(int position, BaseSimplePageAdapter adapter) {
        sparseArrayChildAdapter.put(position, adapter);
    }

    public int getPositionSelected() {
        return positionSelected;
    }
    public int getCurrentItem() {
        return viewPager.getCurrentItem();
    }
    public void notifyPageSelected(int position) {
        ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(position);
        if (viewPagerHolder != null && position >= 0 && position < list_bean.size()){
            positionSelected=position;
            onPageSelected(viewPagerHolder, position, list_bean.get(position));
            //这里必须通知儿子，多因为层嵌套，有可能不止2层，可能会是3层
            BaseSimplePageAdapter adapterChild = sparseArrayChildAdapter.get(position);
            if (adapterChild != null) {
//                        LogUtils.log("onPageSelected父通知子",position);
                adapterChild.notifyPageSelected(adapterChild.getCurrentItem());
            }
        }
    }

    @Override
    public int getCount() {
        return list_bean.size();
    }

    @Override
    public void onTabClick(V holder, int position, T bean) {

    }

    @Override
    public void onTabScrolled(V holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent, V holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    @Override
    public List<T> getList_bean() {
        return list_bean;
    }

    /**
     * 删除相应position的数据Item
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;

    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W remove(int position) {
        removeNoNotify(position);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;

    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return (W) this;

    }


    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;

    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 添加一条数据item到position 0
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;

    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 添加List
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;

    }

    /**
     * 添加List,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 先清空后添加List
     */

    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;

    }


    /**
     * 先清空后添加
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;

    }

    /**
     * 先清空后添加,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 先清空后添加List,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 添加List到position 0
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;

    }

    /**
     * 添加List到position 0,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 清空list
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clearNoNotify() {
        list_bean.clear();
        return (W) this;

    }

    /**
     * 清空list
     */
    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;

    }

    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;

    }

    @Override
    public <W extends IBaseTabPageAdapter<T, V>> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyDataSetChanged();
        return (W) this;
    }
}