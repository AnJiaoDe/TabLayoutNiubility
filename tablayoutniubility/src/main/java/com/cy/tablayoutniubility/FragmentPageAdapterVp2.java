package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
public abstract class FragmentPageAdapterVp2<T,V extends IViewHolder> extends FragmentStateAdapter implements IFragmentPageAdapter<T,V,FragmentPageAdapterVp2> {
    private List<T> list_bean = new ArrayList<>();

    public FragmentPageAdapterVp2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragmentPageAdapterVp2(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragmentPageAdapterVp2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public final Fragment createFragment(int position) {
        return createFragment(list_bean.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list_bean.size();
    }

    @Override
    public FragmentPageAdapterVp2 getFragmentPageAdapter() {
        return this;
    }

    @Override
    public  void onTabClick(V holder, int position, T bean){}
    @Override
    public  void onTabScrolled(V holderCurrent,int positionCurrent,
                                       boolean fromLeft2RightCurrent,float positionOffsetCurrent,
                               V holder2,int position2,
                                       boolean fromLeft2Right2,float positionOffset2){}
    /**
     * ----------------------------------------------------------------------------------------------------
     */
    /**
     * @param list_bean
     */
    public FragmentPageAdapterVp2<T,V> setList_bean(List<T> list_bean) {
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
    public FragmentPageAdapterVp2<T,V> removeNoNotify(int position) {
        list_bean.remove(position);
        return this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public FragmentPageAdapterVp2<T,V> remove(int position) {
        removeNoNotify(position);
        /**
         onBindViewHolder回调的position永远是最后一个可见的item的position,
         比如一次最多只能看到5个item,只要执行了notifyItemRemoved(position)，
         onBindViewHolder回调的position永远是4
         */
        notifyItemRemoved(position);
        return this;
    }

    /**
     * 添加一条数据item
     */
    public FragmentPageAdapterVp2<T,V> addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public FragmentPageAdapterVp2<T,V> add(int position, T bean) {
        addNoNotify(position, bean);
        notifyItemInserted(position);
        return this;
    }


    /**
     * 添加一条数据item
     */
    public FragmentPageAdapterVp2<T,V> addNoNotify(T bean) {
        list_bean.add(bean);
        return this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public FragmentPageAdapterVp2<T,V> add(T bean) {
        addNoNotify(bean);
        notifyItemInserted(list_bean.size() - 1);
        return this;
    }

    /**
     * 添加一条数据item到position 0
     */

    public FragmentPageAdapterVp2<T,V> addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public FragmentPageAdapterVp2<T,V> addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyItemInserted(0);
        return this;
    }

    /**
     * 添加List
     */
    public FragmentPageAdapterVp2<T,V> addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return this;
    }

    /**
     * 添加List,并且notify
     */
    public FragmentPageAdapterVp2<T,V> add(List<T> beans) {
        addNoNotify(beans);
        notifyItemRangeInserted(list_bean.size() - beans.size(), beans.size());
        return this;
    }

    /**
     * 先清空后添加List
     */

    public FragmentPageAdapterVp2<T,V> clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return this;
    }


    /**
     * 先清空后添加
     */

    public FragmentPageAdapterVp2<T,V> clearAddNoNotify(T bean) {
        clearAdd(bean);
        return this;
    }

    /**
     * 先清空后添加,并且notify
     */

    public FragmentPageAdapterVp2<T,V> clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 先清空后添加List,并且notify
     */

    public FragmentPageAdapterVp2<T,V> clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 添加List到position 0
     */

    public FragmentPageAdapterVp2<T,V> addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return this;
    }

    /**
     * 添加List到position 0,并且notify
     */

    public FragmentPageAdapterVp2<T,V> addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //没有刷新的作用
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return this;
    }

    /**
     * 清空list
     */
    public FragmentPageAdapterVp2<T,V> clearNoNotify() {
        list_bean.clear();
        return this;
    }

    /**
     * 清空list
     */
    public FragmentPageAdapterVp2<T,V> clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return this;
    }


    public FragmentPageAdapterVp2<T,V> setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return this;
    }

    public FragmentPageAdapterVp2<T,V> set(int index, T bean) {
        setNoNotify(index, bean);
        notifyItemChanged(index);
        return this;
    }
}
