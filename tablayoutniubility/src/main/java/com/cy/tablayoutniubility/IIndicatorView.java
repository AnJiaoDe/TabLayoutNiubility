package com.cy.tablayoutniubility;

import android.view.View;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/2 21:46
 * @UpdateUser:
 * @UpdateDate: 2020/8/2 21:46
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface IIndicatorView {

    public <T extends View> T getView();

    public Indicator getIndicator();
}
