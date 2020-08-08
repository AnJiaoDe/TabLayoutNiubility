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
public abstract class FragmentPageAdapterVp<T,V extends IViewHolder> extends FragmentStatePagerAdapter implements IFragmentPageAdapter<T,V,FragmentPageAdapterVp>{
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
    public void onTabClick(V holder, int position, T bean) {
    }
    @Override
    public void onTabScrolled(V holderCurrent, int positionCurrent,
                              boolean fromLeft2RightCurrent, float positionOffsetCurrent,
                              V holder2, int position2,
                              boolean fromLeft2Right2, float positionOffset2) {
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     */
    /**
     * @param list_bean
     */
    public FragmentPageAdapterVp<T,V> setList_bean(List<T> list_bean) {
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
    public FragmentPageAdapterVp<T,V> removeNoNotify(int position) {
        list_bean.remove(position);
        return this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public FragmentPageAdapterVp<T,V> remove(int position) {
        removeNoNotify(position);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加一条数据item
     */
    public FragmentPageAdapterVp<T,V> addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public FragmentPageAdapterVp<T,V> add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return this;
    }


    /**
     * 添加一条数据item
     */
    public FragmentPageAdapterVp<T,V> addNoNotify(T bean) {
        list_bean.add(bean);
        return this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public FragmentPageAdapterVp<T,V> add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加一条数据item到position 0
     */

    public FragmentPageAdapterVp<T,V> addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public FragmentPageAdapterVp<T,V> addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加List
     */
    public FragmentPageAdapterVp<T,V> addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return this;
    }

    /**
     * 添加List,并且notify
     */
    public FragmentPageAdapterVp<T,V> add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 先清空后添加List
     */

    public FragmentPageAdapterVp<T,V> clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return this;
    }


    /**
     * 先清空后添加
     */

    public FragmentPageAdapterVp<T,V> clearAddNoNotify(T bean) {
        clearAdd(bean);
        return this;
    }

    /**
     * 先清空后添加,并且notify
     */

    public FragmentPageAdapterVp<T,V> clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 先清空后添加List,并且notify
     */

    public FragmentPageAdapterVp<T,V> clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加List到position 0
     */

    public FragmentPageAdapterVp<T,V> addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return this;
    }

    /**
     * 添加List到position 0,并且notify
     */

    public FragmentPageAdapterVp<T,V> addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //没有刷新的作用
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return this;
    }

    /**
     * 清空list
     */
    public FragmentPageAdapterVp<T,V> clearNoNotify() {
        list_bean.clear();
        return this;
    }

    /**
     * 清空list
     */
    public FragmentPageAdapterVp<T,V> clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return this;
    }


    public FragmentPageAdapterVp<T,V> setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return this;
    }

    public FragmentPageAdapterVp<T,V> set(int index, T bean) {
        setNoNotify(index, bean);
        notifyDataSetChanged();
        return this;
    }
}
