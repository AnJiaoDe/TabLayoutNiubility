package com.cy.tablayoutniubility;

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
public interface ITabAdapter<T> {

    public <W extends ITabAdapter> W getAdapter();

    public <W extends ITabAdapter> W add(List<T> beans);
}
