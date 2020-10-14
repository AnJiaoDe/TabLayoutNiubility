package com.cy.tablayoutniubility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;


/**
 * Created by cy on 2017/7/2.
 */

public class HorizontalRecyclerView extends RecyclerView {
    private int downX; // 按下时 X轴坐标值
    private int downY; // 按下时 Y 轴坐标值
    private LinearItemDecoration linearItemDecoration;
    //永远<=0
    private int offsetX = 0;
    public HorizontalRecyclerView(Context context) {
        this(context, null);
    }

    public HorizontalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                offsetX -= dx;
            }

        });
        SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) getItemAnimator();
        if (simpleItemAnimator != null) simpleItemAnimator.setSupportsChangeAnimations(false);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                int dx = moveX - downX;
                int dy = moveY - downY;
                downX = moveX;
                downY = moveY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    requestDisallowInterceptTouch(true);
                    if ((dx > 0 && canScrollHorizontally(-1)) || (dx < 0 && canScrollHorizontally(1)))
                        return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void requestDisallowInterceptTouch(boolean disallowInterceptTouchEvent) {
        final ViewParent parent = getParent();
        if (parent != null) parent.requestDisallowInterceptTouchEvent(disallowInterceptTouchEvent);
    }
    /**
     * x为正，表示手指往左滑,x为负，表示手指往右滑
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    /**
     * x<=0
     * 比如 x=0,表示滑动到RecyclerView最左边，完全显示第一个item,
     * 比如 x=-100,表示RecyclerView左边100像素的界面被隐藏
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        scrollBy(offsetX - x, y);
    }

    public int getOffsetX() {
        return offsetX;
    }


    @Override
    public void setAdapter(Adapter adapter) {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        super.setAdapter(adapter);
    }

    @Override
    public void addItemDecoration(@NonNull ItemDecoration decor, int index) {
        try {
            this.linearItemDecoration = (LinearItemDecoration) decor;
        } catch (Exception e) {
            throw new IllegalAccessError("You can only use LinearItemDecoration in  HorizontalRecyclerView");
        }
        super.addItemDecoration(decor, index);
    }

    @Override
    public void addItemDecoration(@NonNull ItemDecoration decor) {
        try {
            this.linearItemDecoration = (LinearItemDecoration) decor;
        } catch (Exception e) {
            throw new IllegalAccessError("You can only use LinearItemDecoration in  HorizontalRecyclerView");
        }
        super.addItemDecoration(decor);
    }

    @NonNull
    @Override
    public ItemDecoration getItemDecorationAt(int index) {
        return linearItemDecoration;
    }

    public LinearItemDecoration getItemDecoration() {
        return linearItemDecoration;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
