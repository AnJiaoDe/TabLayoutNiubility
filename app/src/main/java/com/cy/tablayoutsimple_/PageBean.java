package com.cy.tablayoutsimple_;

import androidx.annotation.DrawableRes;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/8 10:23
 * @UpdateUser:
 * @UpdateDate: 2020/8/8 10:23
 * @UpdateRemark:
 * @Version:
 */
public class PageBean {
    private @DrawableRes int resID;

    public PageBean(@DrawableRes int resID) {
        this.resID = resID;
    }

    public @DrawableRes int getResID() {
        return resID;
    }

    public void setResID(@DrawableRes int resID) {
        this.resID = resID;
    }
}
