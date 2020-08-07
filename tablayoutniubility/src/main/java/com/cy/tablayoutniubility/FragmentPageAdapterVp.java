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
public abstract class FragmentPageAdapterVp<T> extends FragmentStatePagerAdapter implements IFragmentPageAdapter<T,FragmentPageAdapterVp>{
    private List<T> list_bean = new ArrayList<>();

    public FragmentPageAdapterVp(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return createFragment(list_bean.get(position), position);

    }

    @Override
    public int getCount() {
        return list_bean.size();
    }

    @Override
    public FragmentPageAdapterVp getFragmentPageAdapter() {
        return this;
    }

    @Override
    public void onTabClick(TabViewHolder holder, int position, T bean) {
    }
    @Override
    public void onTabScrolled(TabViewHolder holderCurrent, int positionCurrent,
                              boolean fromLeft2RightCurrent, float positionOffsetCurrent,
                              TabViewHolder holder2, int position2,
                              boolean fromLeft2Right2, float positionOffset2) {
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     */
    /**
     * @param list_bean
     */
    public FragmentPageAdapterVp<T> setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return this;
    }

    public List<T> getList_bean() {
        return list_bean;
    }

    /**
     * 删除相应position的数据Item
     */
    public FragmentPageAdapterVp<T> removeNoNotify(int position) {
        list_bean.remove(position);
        return this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public FragmentPageAdapterVp<T> remove(int position) {
        removeNoNotify(position);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加一条数据item
     */
    public FragmentPageAdapterVp<T> addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public FragmentPageAdapterVp<T> add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return this;
    }


    /**
     * 添加一条数据item
     */
    public FragmentPageAdapterVp<T> addNoNotify(T bean) {
        list_bean.add(bean);
        return this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public FragmentPageAdapterVp<T> add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加一条数据item到position 0
     */

    public FragmentPageAdapterVp<T> addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public FragmentPageAdapterVp<T> addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加List
     */
    public FragmentPageAdapterVp<T> addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return this;
    }

    /**
     * 添加List,并且notify
     */
    public FragmentPageAdapterVp<T> add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 先清空后添加List
     */

    public FragmentPageAdapterVp<T> clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return this;
    }


    /**
     * 先清空后添加
     */

    public FragmentPageAdapterVp<T> clearAddNoNotify(T bean) {
        clearAdd(bean);
        return this;
    }

    /**
     * 先清空后添加,并且notify
     */

    public FragmentPageAdapterVp<T> clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 先清空后添加List,并且notify
     */

    public FragmentPageAdapterVp<T> clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加List到position 0
     */

    public FragmentPageAdapterVp<T> addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return this;
    }

    /**
     * 添加List到position 0,并且notify
     */

    public FragmentPageAdapterVp<T> addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //没有刷新的作用
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return this;
    }

    /**
     * 清空list
     */
    public FragmentPageAdapterVp<T> clearNoNotify() {
        list_bean.clear();
        return this;
    }

    /**
     * 清空list
     */
    public FragmentPageAdapterVp<T> clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return this;
    }


    public FragmentPageAdapterVp<T> setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return this;
    }

    public FragmentPageAdapterVp<T> set(int index, T bean) {
        setNoNotify(index, bean);
        notifyDataSetChanged();
        return this;
    }
}
