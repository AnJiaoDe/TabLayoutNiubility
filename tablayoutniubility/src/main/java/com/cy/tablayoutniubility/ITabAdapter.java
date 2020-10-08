package com.cy.tablayoutniubility;

import android.view.View;

import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/9 12:00
 * @UpdateUser:
 * @UpdateDate: 2020/8/9 12:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ITabAdapter<T,V extends IViewHolder> {

    public <W extends ITabAdapter> W getAdapter() ;

    public List<T> getList_bean() ;

    public  void bindDataToView(V holder, int position, T bean, boolean isSelected);

    public  int getItemLayoutID(int position, T bean);

    public  void onItemClick(V holder, int position, T bean);

    /**
     * @param list_bean
     */
    public <W extends ITabAdapter> W setList_bean(List<T> list_bean);

    /**
     * 删除相应position的数据Item
     */
    public <W extends ITabAdapter> W removeNoNotify(int position) ;

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public <W extends ITabAdapter> W remove(int position) ;

    /**
     * 添加一条数据item
     */
    public <W extends ITabAdapter> W addNoNotify(int position, T bean);

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends ITabAdapter> W add(int position, T bean);


    /**
     * 添加一条数据item
     */
    public <W extends ITabAdapter> W addNoNotify(T bean) ;

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends ITabAdapter> W add(T bean);

    /**
     * 添加一条数据item到position 0
     */

    public <W extends ITabAdapter> W addToTopNoNotify(T bean);

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public <W extends ITabAdapter> W addToTop(T bean) ;

    /**
     * 添加List
     */
    public <W extends ITabAdapter> W addNoNotify(List<T> beans);

    /**
     * 添加List,并且notify
     */
    public <W extends ITabAdapter> W add(List<T> beans);


    /**
     * 先清空后添加List
     */

    public <W extends ITabAdapter> W clearAddNoNotify(List<T> beans);


    /**
     * 先清空后添加
     */

    public <W extends ITabAdapter> W clearAddNoNotify(T bean);

    /**
     * 先清空后添加,并且notify
     */

    public <W extends ITabAdapter> W clearAdd(T bean);

    /**
     * 先清空后添加List,并且notify
     */

    public <W extends ITabAdapter> W clearAdd(List<T> beans) ;

    /**
     * 添加List到position 0
     */

    public <W extends ITabAdapter> W addToTopNoNotify(List<T> beans) ;

    /**
     * 添加List到position 0,并且notify
     */

    public <W extends ITabAdapter> W addToTop(List<T> beans) ;

    /**
     * 清空list
     */
    public <W extends ITabAdapter> W clearNoNotify();

    /**
     * 清空list
     */
    public <W extends ITabAdapter> W clear() ;


    public <W extends ITabAdapter> W setNoNotify(int index, T bean);

    public <W extends ITabAdapter> W set(int index, T bean) ;
}
