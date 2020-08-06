//package com.cy.tablayoutsimple;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
///**
// * @Description:
// * @Author: cy
// * @CreateDate: 2020/8/6 10:23
// * @UpdateUser:
// * @UpdateDate: 2020/8/6 10:23
// * @UpdateRemark:
// * @Version:
// */
//public class ViewPagerRV extends RecyclerView {
//    private float downX, downY;
//    private float moveX;
//    private float moveY;
//    //永远<=0
//    private int offsetX = 0;
//    private int currentItem=0;
//    public ViewPagerRV(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public ViewPagerRV(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        addOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                offsetX-=dx;
//            }
//
//        });
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = ev.getX();
//                downY = ev.getY();
//                LogUtils.log("ACTION_DOWN");
//                break;
////
////            case MotionEvent.ACTION_MOVE:
////                moveX = ev.getX();
////                moveY = ev.getY();
////                LogUtils.log("ACTION_MOVE");
////
////
////                float disX = moveX - downX;
////                float disY = moveY - downY;
////
////
//////                downX = moveX;
//////                downY = moveY;
////
////                //手指右滑
////                if (disX > 0 && Math.abs(disX) > Math.abs(disY) && canScrollHorizontally(-1)) {
////                    LogUtils.log("00000000000000");
////                    scrollBy(-(int) disX, 0);
////                }
////                //手指左滑
////                if (disX < 0 && Math.abs(disX) > Math.abs(disY) && canScrollHorizontally(1)) {
////                    LogUtils.log("111111111111");
////                    scrollBy(-(int) disX, 0);
////                }
////
////                break;
////            case MotionEvent.ACTION_UP:
////                LogUtils.log("ACTION_UP");
////                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = ev.getX();
//                downY = ev.getY();
//                LogUtils.log("ACTION_DOWNonTouchEvent");
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                moveX = ev.getX();
//                moveY = ev.getY();
//                LogUtils.log("ACTION_MOVEonTouchEvent");
//
//
//                float disX = moveX - downX;
//                float disY = moveY - downY;
//
//
//                downX = moveX;
//                downY = moveY;
//
//                //手指右滑
//                if (disX > 0 && Math.abs(disX) > Math.abs(disY) && canScrollHorizontally(-1)) {
//                    LogUtils.log("00000000000000");
//                    scrollBy(-(int) disX, 0);
//                    return false;
//                }
//                //手指左滑
//                if (disX < 0 && Math.abs(disX) > Math.abs(disY) && canScrollHorizontally(1)) {
//                    LogUtils.log("111111111111");
//                    scrollBy(-(int) disX, 0);
//                    return false;
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:
//                LogUtils.log("ACTION_UPonTouchEvent");
//                ViewHolder viewHolder=findViewHolderForAdapterPosition(currentItem);
//                if(viewHolder!=null){
//                    if(-viewHolder.itemView.getLeft()>viewHolder.itemView.getWidth()*1f/2){
//                        scrollTo(-viewHolder.itemView.getWidth(),0);
//                        currentItem++;
//                    }
//                }
//                return  false;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//    public int getCurrentItem() {
//        return currentItem;
//    }
//
//    /**
//     * x为正，表示手指往左滑,x为负，表示手指往右滑
//     *
//     * @param x
//     * @param y
//     */
//    @Override
//    public void scrollBy(int x, int y) {
//        super.scrollBy(x, y);
//    }
//
//    /**
//     * x<=0
//     * 比如 x=0,表示滑动到RecyclerView最左边，完全显示第一个item,
//     * 比如 x=-100,表示RecyclerView左边100像素的界面被隐藏
//     *
//     * @param x
//     * @param y
//     */
//    @Override
//    public void scrollTo(int x, int y) {
//        scrollBy(offsetX - x, y);
//    }
//
//    @Override
//    public void setAdapter(Adapter adapter) {
//        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        super.setAdapter(adapter);
//    }
//}
