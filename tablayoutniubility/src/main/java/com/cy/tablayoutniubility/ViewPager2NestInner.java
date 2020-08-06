package com.cy.tablayoutniubility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/3 16:22
 * @UpdateUser:
 * @UpdateDate: 2020/8/3 16:22
 * @UpdateRemark:
 * @Version:
 */
public class ViewPager2NestInner extends LinearLayout {
    private ViewPager2 viewPager2;
    private float downX, downY;
    private float moveX;
    private float moveY;
//    private TabLayoutNiubility tabLayoutNiubility;
//    private ViewPager2NestOuter viewPager2NestOuter;
    private boolean canceled=false;
    public ViewPager2NestInner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

//    public void setViewPager2NestOuter(ViewPager2NestOuter viewPager2NestOuter) {
//        this.viewPager2NestOuter = viewPager2NestOuter;
//    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count > 1)
            throw new RuntimeException("Exception:You must add only one TabLayoutNiubility and only one ViewPager2 in " + getClass().getName());
        try {
//            tabLayoutNiubility = (TabLayoutNiubility) getChildAt(0);

            viewPager2 = (ViewPager2) getChildAt(0);
        } catch (Exception e) {
            throw new RuntimeException("Exception:You must add only one TabLayoutNiubility and only one ViewPager2 in " + getClass().getName());
        }
        disallowInterceptTouchEvent(true);

    }

    public ViewPager2 getViewPager2() {
        return viewPager2;
    }

//    public TabLayoutNiubility getTabLayoutNiubility() {
//        return tabLayoutNiubility;
//    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        LogUtils.log("dispatchTouchEventInner");
//        return true;
//    }

    /**
     * canScrollVertically(-1)表示可以下滑，canScrollVertically(1)表示可以上滑
     * canScrollHorizontally(-1)表示可以左滑，canScrollHorizontally(1)表示可以右滑
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.log("onInterceptTouchEventinner");
        canceled=false;
//        if (viewPager2NestOuter == null)
//            throw new RuntimeException("Exception:You must call setViewPager2Out for " + getClass().getName());

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                LogUtils.log("ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                moveX = ev.getX();
                moveY = ev.getY();
                LogUtils.log("ACTION_MOVE");


                float disX = moveX - downX;
                float disY = moveY - downY;


//                LogUtils.log("viewPager2Inner.canScrollHorizontally(-1)",viewPager2.canScrollHorizontally(-1));
//                //true表示手指往左拖动，还能右滑
                LogUtils.log("viewPager2Inner.canScrollHorizontally(1)", viewPager2.canScrollHorizontally(1));
//
//                LogUtils.log("viewPager2.canScrollHorizontally((int) (disX > 0 ? disX : -disX));", viewPager2.canScrollHorizontally((int) (disX > 0 ? disX : -disX)));
//                if (Math.abs(disX) > Math.abs(disY)) {
//                    LogUtils.log("disX > disY");
//                    return viewPager2.canScrollHorizontally((int) (disX > 0 ? disX : -disX));
//                } else {
//                    LogUtils.log("disX00000disY");
//                    return false;
//                }


//                downX = moveX;
//                downY = moveY;

                //手指右滑
                if (disX > 0 && viewPager2.canScrollHorizontally(-1)) {
                    LogUtils.log("00000000000000");
                    disallowInterceptTouchEvent(true);
                }
                //手指左滑
                if (disX < 0 && viewPager2.canScrollHorizontally(1)) {
                    LogUtils.log("111111111111");
                    disallowInterceptTouchEvent(true);

                }

                break;
            case MotionEvent.ACTION_UP:
                LogUtils.log("ACTION_UP");

                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.log("ACTION_CANCEL");

                canceled=true;
                break;
        }
        return false;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        LogUtils.log("onTouchEventinner");
//        return true;
//    }

    private void disallowInterceptTouchEvent(boolean disallow) {
        final ViewParent parent = viewPager2.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallow);
        }
//        else {
//            throw new RuntimeException("Exception:You must call setViewPager2Out for " + getClass().getName());
//        }
    }
}
