package com.cy.tablayoutsimple_;

import androidx.annotation.DrawableRes;
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
public class TabBean {
    private String text;
    private @DrawableRes int resID_normal;
    private @DrawableRes int resID_selected;

    public TabBean(String text, @DrawableRes int resID_normal, @DrawableRes int resID_selected) {
        this.text = text;
        this.resID_normal = resID_normal;
        this.resID_selected = resID_selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getResID_normal() {
        return resID_normal;
    }

    public void setResID_normal(@DrawableRes int resID_normal) {
        this.resID_normal = resID_normal;
    }

    public int getResID_selected() {
        return resID_selected;
    }

    public void setResID_selected(@DrawableRes int resID_selected) {
        this.resID_selected = resID_selected;
    }
}
