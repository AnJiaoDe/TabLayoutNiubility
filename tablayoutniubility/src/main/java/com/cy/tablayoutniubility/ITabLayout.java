package com.cy.tablayoutniubility;

import android.view.View;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/9 11:42
 * @UpdateUser:
 * @UpdateDate: 2020/8/9 11:42
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ITabLayout {

    public <T extends View> T getView();

    public <T extends ITabLayout> T setIndicatorView(IIndicatorView indicatorView);

}
