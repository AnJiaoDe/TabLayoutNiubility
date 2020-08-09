package com.cy.tablayoutniubility;

import android.view.View;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/8 14:42
 * @UpdateUser:
 * @UpdateDate: 2020/8/8 14:42
 * @UpdateRemark:
 * @Version:
 */
public interface IViewHolder {

    public <T extends View> T getView(int viewId);
}
