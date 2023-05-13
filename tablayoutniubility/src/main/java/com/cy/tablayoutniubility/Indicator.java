package com.cy.tablayoutniubility;

import android.graphics.Paint;
import android.view.View;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/2 23:14
 * @UpdateUser:
 * @UpdateDate: 2020/8/2 23:14
 * @UpdateRemark:
 * @Version: 1.0
 */
public class Indicator {
    private int height_indicator, width_indicator_selected, width_indicator, width_indicator_max;
    private boolean isMax2Width=false;
    private boolean isMax2Height=false;
    private Paint paint_indicator;
    private int progress;
    private View viewIndicator;
    public Indicator(View viewIndicator) {
        this.viewIndicator = viewIndicator;
        paint_indicator=new Paint();
    }

    public boolean isMax2Width() {
        return isMax2Width;
    }

    public Indicator setMax2Width(boolean max2Width) {
        isMax2Width = max2Width;
        return this;
    }

    public boolean isMax2Height() {
        return isMax2Height;
    }

    public Indicator setMax2Height(boolean max2Height) {
        isMax2Height = max2Height;
        return this;
    }

    /**
     * 设置indicator进度
     * @param progress
     * @return
     */
    public Indicator setProgress(int progress) {
        this.progress = progress;
        viewIndicator.invalidate();
        return  this;
    }

    public int getProgress() {
        return progress;
    }

    public int getWidth_indicator_max() {
        return width_indicator_max;
    }

    /**
     * 设置indicator最大长度
     * @param width_indicator_max
     * @return
     */
    public Indicator setWidth_indicator_max(int width_indicator_max) {
        this.width_indicator_max = width_indicator_max;
        return  this;
    }

    /**
     * 设置indicator颜色
     * @param color_indicator
     * @return
     */
    public Indicator setColor_indicator(int color_indicator) {
        paint_indicator.setColor(color_indicator);
        return  this;
    }

    /**
     * 设置indicator高度
     * @param height_indicator
     * @return
     */
    public Indicator setHeight_indicator(int height_indicator) {
        this.height_indicator = height_indicator;
        return  this;
    }

    public int getHeight_indicator() {
        return height_indicator;
    }

    public Paint getPaint_indicator() {
        return paint_indicator;
    }

    /**
     * 设置indicator选中时的长度
     * @param width_indicator_selected
     * @return
     */
    public Indicator setWidth_indicator_selected(int width_indicator_selected) {
        this.width_indicator_selected = width_indicator_selected;
        return  this;
    }

    public int getWidth_indicator_selected() {
        return width_indicator_selected;
    }

    public int getWidth_indicator() {
        return width_indicator;
    }

    /**
     * 设置indicator当前长度
     * @param width_indicator
     * @return
     */
    public Indicator setWidth_indicator(int width_indicator) {
        this.width_indicator = isMax2Width?width_indicator:Math.min(width_indicator_max, width_indicator);
        return  this;
    }
    public Indicator invalidate(){
        viewIndicator.invalidate();
        return this;
    }

}
