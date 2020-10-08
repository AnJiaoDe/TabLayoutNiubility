package com.cy.tablayoutniubility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */

public  abstract class TabAdapterNoScroll<T> implements ITabAdapter<T,TabNoScrollViewHolder> {

    private List<T> list_bean = new ArrayList<>();//数据源
    private int positionSelectedLast = 0;
    private int positionSelected = 0;
    private TabNoScrollCallback tabNoScrollViewCallback;

    @Override
    public <W extends ITabAdapter> W getAdapter() {
        return (W) this;
    }

    TabNoScrollViewHolder onCreateViewHolder(int position, T bean, ViewGroup parent) {
        return new TabNoScrollViewHolder(LayoutInflater.from(parent.getContext()).inflate(getItemLayoutID(position, bean), parent, false));
    }

    void onBindViewHolder(final TabNoScrollViewHolder holder, int position) {
        //添加Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = holder.getAdapterPosition();
                //设置选中的item
                if (positionSelectedLast != p) {
                    positionSelected = p; //选择的position赋值给参数，
                    notifyItemChanged(positionSelected);
                    notifyItemChanged(positionSelectedLast);
                    positionSelectedLast = p;
                }
                onItemClick(holder, p, list_bean.get(p));
            }
        });
        bindDataToView(holder, position, list_bean.get(position), position == positionSelected);
    }

    void setTabNoScrollViewCallback(TabNoScrollCallback tabNoScrollViewCallback) {
        this.tabNoScrollViewCallback = tabNoScrollViewCallback;
    }

    public int getItemCount() {
        return list_bean.size();
    }


    public abstract void bindDataToView(TabNoScrollViewHolder holder, int position, T bean, boolean isSelected);

    public abstract int getItemLayoutID(int position, T bean);

    public abstract void onItemClick(TabNoScrollViewHolder holder, int position, T bean);


    /**
     * ---------------------------------------------------------------------------------
     */

    public void notifyDataSetChanged() {
        tabNoScrollViewCallback.notifyDataSetChanged();
    }

    public void notifyItemChanged(int position) {
        tabNoScrollViewCallback.notifyItemChanged(position);
    }

    public int getPositionSelectedLast() {
        return positionSelectedLast;
    }

    public void setPositionSelectedLast(int positionSelectedLast) {
        this.positionSelectedLast = positionSelectedLast;
    }

    public int getPositionSelected() {
        return positionSelected;
    }

    public void setPositionSelected(int positionSelected) {

        if (positionSelectedLast != positionSelected) {
            this.positionSelected = positionSelected;
            notifyItemChanged(positionSelected);
            notifyItemChanged(positionSelectedLast);
            positionSelectedLast = positionSelected;
        }
    }

    public void setPositionSelectedNoNotify(int positionSelected) {
        this.positionSelected = positionSelected;
    }

    public void setPositionSelectedNotifyAll(int positionSelected) {
        if (positionSelectedLast != positionSelected) {
            this.positionSelected = positionSelected;
            notifyDataSetChanged();
            positionSelectedLast = positionSelected;
        }
    }

    @Override
    public List<T> getList_bean() {
        return list_bean;
    }
    /**
     * @param list_bean
     */
    @Override
    public <W extends ITabAdapter> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 删除相应position的数据Item
     */
    @Override
    public <W extends ITabAdapter> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    @Override
    public <W extends ITabAdapter> W remove(int position) {
        removeNoNotify(position);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加一条数据item
     */
    @Override
    public <W extends ITabAdapter> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends ITabAdapter> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return (W) this;
    }


    /**
     * 添加一条数据item
     */
    @Override
    public <W extends ITabAdapter> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends ITabAdapter> W add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0
     */
    @Override
    public <W extends ITabAdapter> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    @Override
    public <W extends ITabAdapter> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List
     */
    @Override
    public <W extends ITabAdapter> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * 添加List,并且notify
     */
    @Override
    public <W extends ITabAdapter> W add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }


    /**
     * 先清空后添加List
     */
    @Override
    public <W extends ITabAdapter> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;
    }


    /**
     * 先清空后添加
     */
    @Override
    public <W extends ITabAdapter> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;
    }

    /**
     * 先清空后添加,并且notify
     */
    @Override
    public <W extends ITabAdapter> W clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List,并且notify
     */
    @Override
    public <W extends ITabAdapter> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List到position 0
     */
    @Override
    public <W extends ITabAdapter> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;
    }

    /**
     * 添加List到position 0,并且notify
     */
    @Override
    public <W extends ITabAdapter> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends ITabAdapter> W clearNoNotify() {
        list_bean.clear();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends ITabAdapter> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;
    }

    @Override
    public <W extends ITabAdapter> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;
    }

    @Override
    public <W extends ITabAdapter> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyItemChanged(index);
        return (W) this;
    }

}
