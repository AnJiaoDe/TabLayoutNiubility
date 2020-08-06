//package com.cy.tablayoutniubility;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//
//import androidx.annotation.Nullable;
//
///**
// * @Description:
// * @Author: cy
// * @CreateDate: 2020/8/6 15:24
// * @UpdateUser:
// * @UpdateDate: 2020/8/6 15:24
// * @UpdateRemark:
// * @Version:
// */
//public class NoScrollView extends LinearLayout {
//    private TabNoScrollAdapter tabNoScrollAdapter;
//
//    //    private boolean layouted=false;
//    public NoScrollView(Context context) {
//        this(context, null);
//    }
//
//    public NoScrollView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        setOrientation(HORIZONTAL);
//    }
//
//    public void setAdapter(TabNoScrollAdapter tabNoScrollAdapter) {
//        this.tabNoScrollAdapter = tabNoScrollAdapter;
//        LinearLayout.LayoutParams layoutParams =
//                new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        removeAllViews();
//        for (int i = 0; i < tabNoScrollAdapter.getItemCount(); i++) {
//            View view = tabNoScrollAdapter.createViewHolder(i).itemView;
//            addViewInLayout(view, i, layoutParams);
//            LogUtils.log("addView");
//
//        }
//
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        LogUtils.log("onMeasure");
//
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        LogUtils.log("onLayout");
//        if (tabNoScrollAdapter == null) return;
//        int widthUsed = 0;
//        int widthLayouted = 0;
//        for (int i = 0; i < tabNoScrollAdapter.getItemCount(); i++) {
//            View view = tabNoScrollAdapter.getViewHolder(i).itemView;
//            widthUsed += view.getMeasuredWidth();
//        }
//        int space_horizontal = (int) ((getMeasuredWidth() - widthUsed) * 1f / (tabNoScrollAdapter.getItemCount() + 1));
//        for (int i = 0; i < tabNoScrollAdapter.getItemCount(); i++) {
//            View view = tabNoScrollAdapter.getViewHolder(i).itemView;
//            view.layout(space_horizontal + widthLayouted, 0, view.getMeasuredWidth(), getMeasuredHeight());
//            widthLayouted += view.getMeasuredWidth() + view.getMeasuredWidth();
//        }
//
//    }
//}
