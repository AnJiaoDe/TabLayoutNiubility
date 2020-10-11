package com.cy.tablayoutniubility;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */

public abstract class SimplePageAdapterVp2<T, V extends IViewHolder> extends RecyclerView.Adapter<BaseViewHolder> implements IBaseTabPageAdapter<T, V> {

    private List<T> list_bean;//数据源
    private int positionSelectedLast = 0;
    private int positionSelected = 0;
    private int count_threshold = 5000;
    private int count_delete = 10;

    public SimplePageAdapterVp2() {
        list_bean = new ArrayList<>();//数据源
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        handleClick(holder);
        bindDataToView(holder, position, list_bean.get(position), position == positionSelected);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutID(position, list_bean.get(position));
    }


    @Override
    public int getItemCount() {
        return list_bean.size();
    }

    public List<T> getList_bean() {
        return list_bean;
    }

    protected void handleClick(final BaseViewHolder holder) {
        /**
         *
         */
        //添加Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                //设置选中的item
                if (positionSelectedLast != position) {
                    positionSelected = position; //选择的position赋值给参数，
                    notifyItemChanged(positionSelected);
                    notifyItemChanged(positionSelectedLast);
                    positionSelectedLast = position;
                }
                onItemClick(holder, position, list_bean.get(position));

            }
        });
        //添加Item的长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                onItemLongClick(holder, position, list_bean.get(position));
                return true;
                //返回true，那么长按监听只执行长按监听中执行的代码，返回false，还会继续响应其他监听中的事件。
            }
        });
    }

    /**
     * ----------------------------------------------------------------------------------
     */
    public abstract void bindDataToView(BaseViewHolder holder, int position, T bean, boolean isSelected);

    public abstract int getItemLayoutID(int position, T bean);

    public abstract void onItemClick(BaseViewHolder holder, int position, T bean);


    public void onItemLongClick(BaseViewHolder holder, int position, T bean) {

    }

    public void startDefaultAttachedAnim(BaseViewHolder holder) {

        final ObjectAnimator objectAnimator_scaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0.5f, 1);

        final ObjectAnimator objectAnimator_scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 0.5f, 1);

        final ObjectAnimator objectAnimator_alpha = ObjectAnimator.ofFloat(holder.itemView, "alpha", 0.5f, 1);

//        final ObjectAnimator objectAnimator_transX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -1000,0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(objectAnimator_scaleX, objectAnimator_scaleY, objectAnimator_alpha);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }


    /**
     * ------------------------------------------------------------------------------
     */

    /**
     * ---------------------------------------------------------------------------------
     */
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
    public <W extends IBaseTabPageAdapter<T, V>> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 删除相应position的数据Item
     */
    public <W extends IBaseTabPageAdapter<T, V>> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;

    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public <W extends IBaseTabPageAdapter<T, V>> W remove(int position) {
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
    public <W extends IBaseTabPageAdapter<T, V>> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        reduceFromTop(count_threshold, 1);
        return (W) this;

    }

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IBaseTabPageAdapter<T, V>> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyItemInserted(position);
        return (W) this;

    }


    /**
     * 添加一条数据item
     */
    public <W extends IBaseTabPageAdapter<T, V>> W addNoNotify(T bean) {
        list_bean.add(bean);
        reduceFromTop(count_threshold, 1);
        return (W) this;

    }

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IBaseTabPageAdapter<T, V>> W add(T bean) {
        addNoNotify(bean);
        notifyItemInserted(list_bean.size() - 1);
        return (W) this;

    }

    /**
     * 添加一条数据item到position 0
     */

    public <W extends IBaseTabPageAdapter<T, V>> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        reduceFromBottom(count_threshold, 1);
        return (W) this;

    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public <W extends IBaseTabPageAdapter<T, V>> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyItemInserted(0);
        return (W) this;

    }

    /**
     * 添加List
     */
    public <W extends IBaseTabPageAdapter<T, V>> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        reduceFromTop(count_threshold, beans.size());
        return (W) this;

    }

    /**
     * 添加List,并且notify
     */
    public <W extends IBaseTabPageAdapter<T, V>> W add(List<T> beans) {
        addNoNotify(beans);
        notifyItemRangeInserted(list_bean.size() - beans.size(), beans.size());
        return (W) this;

    }

    /**
     * 先清空后添加List
     */

    public <W extends IBaseTabPageAdapter<T, V>> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        reduceFromTop(count_threshold, beans.size());
        return (W) this;

    }


    /**
     * 先清空后添加
     */

    public <W extends IBaseTabPageAdapter<T, V>> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        reduceFromTop(count_threshold, 1);
        return (W) this;

    }

    /**
     * 先清空后添加,并且notify
     */

    public <W extends IBaseTabPageAdapter<T, V>> W clearAdd(T bean) {
        clearNoNotify();
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 先清空后添加List,并且notify
     */

    public <W extends IBaseTabPageAdapter<T, V>> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 添加List到position 0
     */

    public <W extends IBaseTabPageAdapter<T, V>> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        reduceFromBottom(count_threshold, beans.size());
        return (W) this;

    }

    /**
     * 添加List到position 0,并且notify
     */

    public <W extends IBaseTabPageAdapter<T, V>> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //没有刷新的作用
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * 清空list
     */
    public <W extends IBaseTabPageAdapter<T, V>> W clearNoNotify() {
        list_bean.clear();
        return (W) this;

    }

    /**
     * 清空list
     */
    public <W extends IBaseTabPageAdapter<T, V>> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;

    }

    public <W extends IBaseTabPageAdapter<T, V>> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;

    }

    public <W extends IBaseTabPageAdapter<T, V>> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyItemChanged(index);
        return (W) this;

    }

    /**
     * 假设List装的实体类对象，每个实体类对象占用500字节，
     * 1MB=1024*1024B;1MB/500大致=2024个实体对象，假如list.size达到1W，大致占用5MB内存
     * 所以考虑到内存问题，list.size达到5000就应该开始删除部分数据了。删除离当前item最远的数据，留下最近的数据
     */
    public <W extends IBaseTabPageAdapter<T, V>> W reduceFromTop(int threshold, int count_delete) {
        if (list_bean.size() > threshold) {
            for (int i = 0; i < count_delete && i < list_bean.size(); i++) {
                remove(i);
            }
        }
        return (W) this;

    }

    //0 1 2 3     4-2
    public <W extends IBaseTabPageAdapter<T, V>> W reduceFromBottom(int threshold, int count_delete) {
        if (list_bean.size() > threshold) {
            int size = list_bean.size();
            for (int i = size - 1; i >= size - count_delete && i >= 0; i--) {
                remove(i);
            }
        }
        return (W) this;

    }

    public <W extends IBaseTabPageAdapter<T, V>> W reduceFromTop() {
        reduceFromTop(count_threshold, count_delete);
        return (W) this;

    }

    //0 1 2 3     4-2
    public <W extends IBaseTabPageAdapter<T, V>> W reduceFromBottom() {
        reduceFromBottom(count_threshold, count_delete);
        return (W) this;

    }

    public int getCount_threshold() {
        return count_threshold;
    }

    public void setCount_threshold(int count_threshold) {
        this.count_threshold = count_threshold;
    }

    public int getCount_delete() {
        return count_delete;
    }

    public void setCount_delete(int count_delete) {
        this.count_delete = count_delete;
    }
}
