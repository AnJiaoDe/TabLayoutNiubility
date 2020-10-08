package com.cy.tablayoutniubility;

import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/7 11:37
 * @UpdateUser:
 * @UpdateDate: 2020/8/7 11:37
 * @UpdateRemark:
 * @Version:
 */
public interface IBaseTabPageAdapter<T,V extends IViewHolder> {

    public void bindDataToTab(V holder, int position, T bean, boolean isSelected);

    public int getTabLayoutID(int position, T bean);

    public void onTabClick(V holder, int position, T bean);

    public void onTabScrolled(V holderCurrent, int positionCurrent,
                              boolean fromLeft2RightCurrent, float positionOffsetCurrent,
                              V holder2, int position2,
                              boolean fromLeft2Right2, float positionOffset2);

    public <W extends IBaseTabPageAdapter<T,V>> W setList_bean(List<T> list_bean) ;

    public List<T> getList_bean() ;

    /**
     * 删除相应position的数据Item
     */
    public <W extends IBaseTabPageAdapter<T,V>> W removeNoNotify(int position);

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public <W extends IBaseTabPageAdapter<T,V>> W remove(int position);

    /**
     * 添加一条数据item
     */
    public <W extends IBaseTabPageAdapter<T,V>> W addNoNotify(int position, T bean) ;

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IBaseTabPageAdapter<T,V>> W add(int position, T bean);

    /**
     * 添加一条数据item
     */
    public <W extends IBaseTabPageAdapter<T,V>> W addNoNotify(T bean);

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IBaseTabPageAdapter<T,V>> W add(T bean) ;

    /**
     * 添加一条数据item到position 0
     */

    public <W extends IBaseTabPageAdapter<T,V>> W addToTopNoNotify(T bean);

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public <W extends IBaseTabPageAdapter<T,V>> W addToTop(T bean);

    /**
     * 添加List
     */
    public <W extends IBaseTabPageAdapter<T,V>> W addNoNotify(List<T> beans);

    /**
     * 添加List,并且notify
     */
    public <W extends IBaseTabPageAdapter<T,V>> W add(List<T> beans) ;

    /**
     * 先清空后添加List
     */

    public <W extends IBaseTabPageAdapter<T,V>> W clearAddNoNotify(List<T> beans);


    /**
     * 先清空后添加
     */

    public <W extends IBaseTabPageAdapter<T,V>> W clearAddNoNotify(T bean) ;

    /**
     * 先清空后添加,并且notify
     */

    public <W extends IBaseTabPageAdapter<T,V>> W clearAdd(T bean);

    /**
     * 先清空后添加List,并且notify
     */

    public <W extends IBaseTabPageAdapter<T,V>> W clearAdd(List<T> beans) ;

    /**
     * 添加List到position 0
     */

    public <W extends IBaseTabPageAdapter<T,V>> W addToTopNoNotify(List<T> beans);

    /**
     * 添加List到position 0,并且notify
     */

    public <W extends IBaseTabPageAdapter<T,V>> W addToTop(List<T> beans);

    /**
     * 清空list
     */
    public <W extends IBaseTabPageAdapter<T,V>> W clearNoNotify();

    /**
     * 清空list
     */
    public <W extends IBaseTabPageAdapter<T,V>> W clear();


    public <W extends IBaseTabPageAdapter<T,V>> W setNoNotify(int index, T bean);

    public <W extends IBaseTabPageAdapter<T,V>> W set(int index, T bean);
}
