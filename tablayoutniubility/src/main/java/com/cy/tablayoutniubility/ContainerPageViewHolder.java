//package com.cy.tablayoutniubility;
//
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import androidx.annotation.NonNull;
//import androidx.core.view.ViewCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
///**
// * @Description:
// * @Author: cy
// * @CreateDate: 2020/10/7 22:01
// * @UpdateUser:
// * @UpdateDate: 2020/10/7 22:01
// * @UpdateRemark:
// * @Version: 1.0
// */
//class ContainerPageViewHolder extends RecyclerView.ViewHolder {
//
//    private ContainerPageViewHolder(@NonNull FrameLayout container) {
//        super(container);
//    }
//
//    @NonNull
//    static ContainerPageViewHolder create(@NonNull ViewGroup parent) {
//        FrameLayout container = new FrameLayout(parent.getContext());
//        container.setLayoutParams(
//                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT));
//        container.setId(ViewCompat.generateViewId());
//        container.setSaveEnabled(false);
//        return new ContainerPageViewHolder(container);
//    }
//}
