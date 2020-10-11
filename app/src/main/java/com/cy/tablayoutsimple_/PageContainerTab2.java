package com.cy.tablayoutsimple_;

import android.content.Context;
import android.nfc.cardemulation.HostApduService;
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
public class PageContainerTab2 extends PageContainer {
    private View view;
    private String bean;

    public PageContainerTab2(String bean) {
        this.bean = bean;
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
    public void onResume(boolean isFirstResume) {
        super.onResume(isFirstResume);
        LogUtils.log("onResume",bean+isFirstResume);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.log("onStop",bean);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.log("onDestroyView",bean);

    }
}
