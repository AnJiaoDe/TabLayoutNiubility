//package com.cy.tablayoutniubility;
//
//import android.content.Context;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @param <T>
// */
//
//public abstract class TabNoScrollAdapter<T> {
//
//    private List<T> list_bean = new ArrayList<>();//数据源
//    private int positionSelectedLast = 0;
//    private int positionSelected = 0;
//    private Context context;
//    private SparseArray<TabNoScrollViewHolder> sparseArrayViewHolder;
//
//    public TabNoScrollAdapter(Context context) {
//        this.context = context;
//        sparseArrayViewHolder = new SparseArray<>();
//    }
//
//    public final TabNoScrollViewHolder createViewHolder(int position) {
//        final View view = LayoutInflater.from(context).inflate(getItemLayoutID(position, list_bean.get(position)), null);
//        TabNoScrollViewHolder tabNoScrollViewHolder = new TabNoScrollViewHolder(view);
//        sparseArrayViewHolder.put(position,tabNoScrollViewHolder);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewGroup viewGroup = (ViewGroup) view.getParent();
//                int position = viewGroup.indexOfChild(view);
//                //设置选中的item
//                if (positionSelectedLast != position) {
//                    positionSelected = position; //选择的position赋值给参数，
////                    notifyItemChanged(positionSelected);
////                    notifyItemChanged(positionSelectedLast);
//                    positionSelectedLast = position;
//                }
//                onItemClick(getViewHolder(position), position, list_bean.get(position));
//            }
//        });
//        bindDataToView(tabNoScrollViewHolder, position, list_bean.get(position), position == positionSelected);
//        return tabNoScrollViewHolder;
//    }
//
//    public TabNoScrollViewHolder getViewHolder(int position) {
//        TabNoScrollViewHolder tabNoScrollViewHolder = sparseArrayViewHolder.get(position);
//        return tabNoScrollViewHolder;
//    }
//
//    public int getItemCount() {
//        return list_bean.size();
//    }
//
//    public List<T> getList_bean() {
//        return list_bean;
//    }
//
//
//    public abstract void bindDataToView(TabNoScrollViewHolder holder, int position, T bean, boolean isSelected);
//
//    public abstract int getItemLayoutID(int position, T bean);
//
//    public abstract void onItemClick(TabNoScrollViewHolder holder, int position, T bean);
//
//
//    /**
//     * ---------------------------------------------------------------------------------
//     */
//    public int getPositionSelectedLast() {
//        return positionSelectedLast;
//    }
//
//    public void setPositionSelectedLast(int positionSelectedLast) {
//        this.positionSelectedLast = positionSelectedLast;
//    }
//
//    public int getPositionSelected() {
//        return positionSelected;
//    }
//
//    public void setPositionSelected(int positionSelected) {
//        if (positionSelectedLast != positionSelected) {
//            this.positionSelected = positionSelected;
////            notifyItemChanged(positionSelected);
////            notifyItemChanged(positionSelectedLast);
//            positionSelectedLast = positionSelected;
//        }
//    }
//
//    public void setPositionSelectedNoNotify(int positionSelected) {
//        this.positionSelected = positionSelected;
//    }
//
//    public void setPositionSelectedNotifyAll(int positionSelected) {
//        if (positionSelectedLast != positionSelected) {
//            this.positionSelected = positionSelected;
////            notifyDataSetChanged();
//            positionSelectedLast = positionSelected;
//        }
//    }
//
//    /**
//     * @param list_bean
//     */
//    public TabNoScrollAdapter<T> setList_bean(List<T> list_bean) {
//        this.list_bean = list_bean;
////        notifyDataSetChanged();
//        return this;
//    }
//
//    /**
//     * 删除相应position的数据Item
//     */
//    public TabNoScrollAdapter<T> removeNoNotify(int position) {
//        list_bean.remove(position);
//        return this;
//    }
//
//    /**
//     * 删除相应position的数据Item ,并且notify,
//     */
//    public TabNoScrollAdapter<T> remove(int position) {
//        removeNoNotify(position);
//        /**
//         onBindViewHolder回调的position永远是最后一个可见的item的position,
//         比如一次最多只能看到5个item,只要执行了notifyItemRemoved(position)，
//         onBindViewHolder回调的position永远是4
//         */
////        notifyItemRemoved(position);
//        return this;
//    }
//
//    /**
//     * 添加一条数据item
//     */
//    public TabNoScrollAdapter<T> addNoNotify(int position, T bean) {
//        list_bean.add(position, bean);
//        return this;
//    }
//
//    /**
//     * 添加一条数据item,并且notify
//     */
//    public TabNoScrollAdapter<T> add(int position, T bean) {
//        addNoNotify(position, bean);
////        notifyItemInserted(position);
//        return this;
//    }
//
//
//    /**
//     * 添加一条数据item
//     */
//    public TabNoScrollAdapter<T> addNoNotify(T bean) {
//        list_bean.add(bean);
//        return this;
//    }
//
//    /**
//     * 添加一条数据item,并且notify
//     */
//    public TabNoScrollAdapter<T> add(T bean) {
//        addNoNotify(bean);
////        notifyItemInserted(list_bean.size() - 1);
//        return this;
//    }
//
//    /**
//     * 添加一条数据item到position 0
//     */
//
//    public TabNoScrollAdapter<T> addToTopNoNotify(T bean) {
//        list_bean.add(0, bean);
//        return this;
//    }
//
//    /**
//     * 添加一条数据item到position 0,并且notify
//     */
//    public TabNoScrollAdapter<T> addToTop(T bean) {
//        addToTopNoNotify(bean);
////        notifyItemInserted(0);
//        return this;
//    }
//
//    /**
//     * 添加List
//     */
//    public TabNoScrollAdapter<T> addNoNotify(List<T> beans) {
//        list_bean.addAll(beans);
//        return this;
//    }
//
//    /**
//     * 添加List,并且notify
//     */
//    public TabNoScrollAdapter<T> add(List<T> beans) {
//        addNoNotify(beans);
////        notifyItemRangeInserted(list_bean.size() - beans.size(), beans.size());
//        return this;
//    }
//
//    /**
//     * 先清空后添加List
//     */
//
//    public TabNoScrollAdapter<T> clearAddNoNotify(List<T> beans) {
//        list_bean.clear();
//        list_bean.addAll(beans);
//        return this;
//    }
//
//
//    /**
//     * 先清空后添加
//     */
//
//    public TabNoScrollAdapter<T> clearAddNoNotify(T bean) {
//        clearAdd(bean);
//        return this;
//    }
//
//    /**
//     * 先清空后添加,并且notify
//     */
//
//    public TabNoScrollAdapter<T> clearAdd(T bean) {
//        clearNoNotify();
//        add(bean);
////        notifyDataSetChanged();
//        return this;
//    }
//
//    /**
//     * 先清空后添加List,并且notify
//     */
//
//    public TabNoScrollAdapter<T> clearAdd(List<T> beans) {
//        clearAddNoNotify(beans);
////        notifyDataSetChanged();
//        return this;
//    }
//
//    /**
//     * 添加List到position 0
//     */
//
//    public TabNoScrollAdapter<T> addToTopNoNotify(List<T> beans) {
//        list_bean.addAll(0, beans);
//        return this;
//    }
//
//    /**
//     * 添加List到position 0,并且notify
//     */
//
//    public TabNoScrollAdapter<T> addToTop(List<T> beans) {
//        addToTopNoNotify(beans);
//        //没有刷新的作用
////        notifyItemRangeInserted(0, beans.size());
////        notifyDataSetChanged();
//        return this;
//    }
//
//    /**
//     * 清空list
//     */
//    public TabNoScrollAdapter<T> clearNoNotify() {
//        list_bean.clear();
//        return this;
//    }
//
//    /**
//     * 清空list
//     */
//    public TabNoScrollAdapter<T> clear() {
//        list_bean.clear();
////        notifyDataSetChanged();
//        return this;
//    }
//
//
//    public TabNoScrollAdapter<T> setNoNotify(int index, T bean) {
//        list_bean.set(index, bean);
//        return this;
//    }
//
//    public TabNoScrollAdapter<T> set(int index, T bean) {
//        setNoNotify(index, bean);
////        notifyItemChanged(index);
//        return this;
//    }
//
//}
