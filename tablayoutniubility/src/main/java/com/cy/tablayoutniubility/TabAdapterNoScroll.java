package com.cy.tablayoutniubility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */

public abstract class TabAdapterNoScroll<T> {

    private List<T> list_bean = new ArrayList<>();//数据源
    private int positionSelectedLast = 0;
    private int positionSelected = 0;
    private TabNoScrollViewCallback tabNoScrollViewCallback;

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

    void setTabNoScrollViewCallback(TabNoScrollViewCallback tabNoScrollViewCallback) {
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

    /**
     * @param list_bean
     */
    public TabAdapterNoScroll<T> setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return this;
    }

    public List<T> getList_bean() {
        return list_bean;
    }

    /**
     * 添加List,并且notify
     */
    public TabAdapterNoScroll<T> add(List<T> beans) {
        list_bean.addAll(beans);
        notifyDataSetChanged();
        return this;
    }
}
