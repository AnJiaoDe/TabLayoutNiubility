package com.cy.tablayoutsimple_;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.tablayoutniubility.PageContainer;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:06
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:06
 * @UpdateRemark:
 * @Version: 1.0
 */
public class PageContainer1 extends PageContainer {
    private View view;
    private String bean;
    public PageContainer1(String bean) {
        this.bean=bean;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container) {
        view=layoutInflater.inflate(R.layout.fragment_tab2, container, false);
        LogUtils.log("onCreateView",bean);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText(bean);
        return view;
    }

    @Override
    public void onDestroyView() {

    }
}
