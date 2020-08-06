package com.cy.tablayoutniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/6 12:09
 * @UpdateUser:
 * @UpdateDate: 2020/8/6 12:09
 * @UpdateRemark:
 * @Version:
 */
public class TabFixedOuter extends FrameLayout {


    private int itemCount = 0;

    public TabFixedOuter(@NonNull Context context) {
        this(context,null);
    }

    public TabFixedOuter(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup parent = (ViewGroup) getParent();
        TabLayoutNiubility tabLayoutNiubility = (TabLayoutNiubility) parent.getParent();
        LogUtils.log("width", (tabLayoutNiubility.getWidth()
                - tabLayoutNiubility.getPaddingLeft()
                - tabLayoutNiubility.getPaddingRight()
                - (itemCount + 1) * tabLayoutNiubility.getSpace_horizontal()));
        setMeasuredDimension(MeasureSpec.makeMeasureSpec
                        ((int) ((tabLayoutNiubility.getWidth()
                                - tabLayoutNiubility.getPaddingLeft()
                                - tabLayoutNiubility.getPaddingRight()
                                - (itemCount + 1) * tabLayoutNiubility.getSpace_horizontal()) * 1f / itemCount), MeasureSpec.EXACTLY),
                MeasureSpec.getSize(heightMeasureSpec));
    }
}
