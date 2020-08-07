//package com.cy.tablayoutniubility;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.ViewParent;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.viewpager2.widget.ViewPager2;
//
///**
// * @Description:
// * @Author: cy
// * @CreateDate: 2020/8/3 16:22
// * @UpdateUser:
// * @UpdateDate: 2020/8/3 16:22
// * @UpdateRemark:
// * @Version:
// */
//public class ViewPager2NestInner extends LinearLayout {
//    private ViewPager2 viewPager2;
//    private float downX, downY;
//    private float moveX;
//    private float moveY;
//
//    public ViewPager2NestInner(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        setOrientation(VERTICAL);
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        int count = getChildCount();
//        if (count > 1)
//            throw new RuntimeException("Exception:You must add only  one ViewPager2 in " + getClass().getName());
//        try {
//            viewPager2 = (ViewPager2) getChildAt(0);
//        } catch (Exception e) {
//            throw new RuntimeException("Exception:You must add only  one ViewPager2 in " + getClass().getName());
//        }
//        disallowInterceptTouchEvent(true);
//
//    }
//
//    public ViewPager2 getViewPager2() {
//        return viewPager2;
//    }
//
//    /**
//     * canScrollVertically(-1)表示可以下滑，canScrollVertically(1)表示可以上滑
//     * canScrollHorizontally(-1)表示可以左滑，canScrollHorizontally(1)表示可以右滑
//     *
//     * @param ev
//     * @return
//     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        LogUtils.log("onInterceptTouchEventinner");
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = ev.getX();
//                downY = ev.getY();
//                LogUtils.log("ACTION_DOWN");
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                moveX = ev.getX();
//                moveY = ev.getY();
//                LogUtils.log("ACTION_MOVE");
//
//
//                float disX = moveX - downX;
//                float disY = moveY - downY;
//
//                downX = moveX;
//                downY = moveY;
//                //手指右滑
//                if (disX > 0 && Math.abs(disX) > Math.abs(disY) && viewPager2.canScrollHorizontally(-1)) {
//                    LogUtils.log("00000000000000");
//                    disallowInterceptTouchEvent(true);
//                }
//                //手指左滑
//                if (disX < 0 && Math.abs(disX) > Math.abs(disY) && viewPager2.canScrollHorizontally(1)) {
//                    LogUtils.log("111111111111");
//                    disallowInterceptTouchEvent(true);
//
//                }
//
//                break;
//        }
//        return false;
//    }
//
//
//    private void disallowInterceptTouchEvent(boolean disallow) {
//        final ViewParent parent = viewPager2.getParent();
//        if (parent != null) {
//            parent.requestDisallowInterceptTouchEvent(disallow);
//        }
//    }
//}
