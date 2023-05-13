package com.cy.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
public abstract class BaseFragPageAdapterVp<T, V extends IViewHolder> extends CyFragStatePageAdapterVp
        implements IBaseTabPageAdapter<T, V> {
    private List<T> list_bean = new ArrayList<>();

    //    private FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks;
//    private ILifecycleCallback lifecycleCallback;
    public BaseFragPageAdapterVp(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
//        fragmentLifecycleCallbacks=new FragmentManager.FragmentLifecycleCallbacks() {
//            @Override
//            public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentViewDestroyed(fm, f);
//                fm.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks);
//                if(lifecycleCallback!=null){
//                    lifecycleCallback.onFragmentViewDestroyed();
//                    lifecycleCallback=null;
//                }
//            }
//        };
    }

//    public void setLifecycleCallback(ILifecycleCallback lifecycleCallback) {
//        this.lifecycleCallback = lifecycleCallback;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return createFragment(list_bean.get(position), position);

    }

    @Override
    public int getCount() {
        return list_bean.size();
    }

    public abstract Fragment createFragment(T bean, int position);


    @Override
    public void onTabClick(V holder, int position, T bean) {

    }

    @Override
    public void onTabScrolled(V holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent,  V holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {
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
