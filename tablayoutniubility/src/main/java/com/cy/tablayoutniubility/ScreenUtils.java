package com.cy.tablayoutniubility;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/2 23:00
 * @UpdateUser:
 * @UpdateDate: 2020/8/2 23:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ScreenUtils {
    public static int dpAdapt(Context context,float dp) {
        return dpAdapt(context,dp, 360);
    }

    public static int dpAdapt(Context context,float dp, float widthDpBase) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;//高的像素
        int widthPixels = dm.widthPixels;//宽的像素
        float density = dm.density;//density=dpi/160,密度比
        float heightDP = heightPixels / density;//高度的dp
        float widthDP = widthPixels / density;//宽度的dp
        float w = widthDP > heightDP ? heightDP : widthDP;
        return (int) (dp * w / widthDpBase * density + 0.5f);
    }
    public static int getScreenWidth(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();

        WindowManager wm = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT <= 17) {
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        } else {
            wm.getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        WindowManager wm = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT <= 17) {
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        } else {
            wm.getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }
}
