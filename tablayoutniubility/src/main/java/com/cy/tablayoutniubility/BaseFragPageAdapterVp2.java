package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

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
public abstract class BaseFragPageAdapterVp2<T,V extends IViewHolder> extends CyFragStatePageAdapterVp2
        implements IBaseTabPageAdapter<T,V> {
    private List<T> list_bean = new ArrayList<>();

    public BaseFragPageAdapterVp2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BaseFragPageAdapterVp2(@NonNull Fragment fragment) {
        super(fragment);
    }

    public BaseFragPageAdapterVp2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
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

    public  abstract Fragment createFragment(T bean, int position);


    @Override
    public void onTabScrolled(V holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent, V holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {

    }

    @Override
    public void onTabClick(V holder, int position, T bean) {

    }
/**
     * ----------------------------------------------------------------------------------------------------
     */
    /**
     * @param list_bean
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    public List<T> getList_bean() {
        return list_bean;
    }

    /**
     * 删除相应position的数据Item
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W remove(int position) {
        removeNoNotify(position);
        /**
         onBindViewHolder回调的position永远是最后一个可见的item的position,
         比如一次最多只能看到5个item,只要执行了notifyItemRemoved(position)，
         onBindViewHolder回调的position永远是4
         */
        notifyItemRemoved(position);
        return (W) this;
    }

    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyItemInserted(position);
        return (W) this;
    }


    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W add(T bean) {
        addNoNotify(bean);
        notifyItemInserted(list_bean.size() - 1);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyItemInserted(0);
        return (W) this;
    }

    /**
     * 添加List
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * 添加List,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W add(List<T> beans) {
        addNoNotify(beans);
        notifyItemRangeInserted(list_bean.size() - beans.size(), beans.size());
        return (W) this;
    }

    /**
     * 先清空后添加List
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;
    }


    /**
     * 先清空后添加
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;
    }

    /**
     * 先清空后添加,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List到position 0
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;
    }

    /**
     * 添加List到position 0,并且notify
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //没有刷新的作用
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W clearNoNotify() {
        list_bean.clear();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;
    }

    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;
    }
    @Override
    public <W extends IBaseTabPageAdapter<T,V>> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyItemChanged(index);
        return (W) this;
    }
}
