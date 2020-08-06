package com.cy.tablayoutniubility;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/7/14 12:27
 * @UpdateUser:
 * @UpdateDate: 2020/7/14 12:27
 * @UpdateRemark:
 * @Version:
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int space_vertical, space_horizontal;
    private RecyclerView.Adapter adapter;

    public LinearItemDecoration(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public LinearItemDecoration setSpace_vertical(int space_vertical) {
        this.space_vertical = space_vertical;
        return this;
    }

    public LinearItemDecoration setSpace_horizontal(int space_horizontal) {
        this.space_horizontal = space_horizontal;
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(view);
        int position = viewHolder.getAdapterPosition();
        outRect.left = space_horizontal;
        outRect.top = space_vertical;
        outRect.right=position==adapter.getItemCount()-1?space_horizontal:0;
        outRect.bottom = space_vertical;
    }

    public int getSpace_vertical() {
        return space_vertical;
    }

    public int getSpace_horizontal() {
        return space_horizontal;
    }
}
