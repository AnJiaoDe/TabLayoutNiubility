package com.cy.tablayoutsimple_;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cy.tablayoutniubility.ContainerPageAdapterVp;
import com.cy.tablayoutniubility.FragPageAdapterVp;
import com.cy.tablayoutniubility.PageContainer;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabLayoutScroll;
import com.cy.tablayoutniubility.TabMediatorVp;
import com.cy.tablayoutniubility.TabViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/8 0:06
 * @UpdateUser:
 * @UpdateDate: 2020/10/8 0:06
 * @UpdateRemark:
 * @Version: 1.0
 */
public class PageContainerTab1 extends PageContainer {
    private String bean;

    public PageContainerTab1(String bean) {
        this.bean = bean;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container) {
        view = (ViewGroup) layoutInflater.inflate(R.layout.fragment_tab1, container, false);
        LogUtils.log("onCreateView");
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine = view.findViewById(R.id.tablayout);
        ContainerPageAdapterVp<String> containerPageAdapterVp = new ContainerPageAdapterVp<String>(viewPager) {
            @Override
            public PageContainer onCreatePageContainer(ViewGroup container, int position, String bean) {
                LogUtils.log("onCreatePageContainer", position);
                return new PageContainerTab2(bean);
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xffe45540);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    textView.setTextColor(0xff444444);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab;
            }
        };
        TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(containerPageAdapterVp);
        List<String> list = new ArrayList<>();
        list.add(bean + "0");
        list.add(bean + "1");
        list.add(bean + "2");
        list.add(bean + "3");
        list.add(bean + "4");
        list.add(bean + "5");
        list.add(bean + "6");
        list.add(bean + "7");
        list.add(bean + "8");
        list.add(bean + "9");
        list.add(bean + "10");
        list.add(bean + "11");
        list.add(bean + "12");
        list.add(bean + "13");
        containerPageAdapterVp.add(list);
        tabAdapter.add(list);
        return view;
    }

    @Override
    public void onResume(boolean isFirstResume) {
        super.onResume(isFirstResume);
        LogUtils.log("onResume", bean + isFirstResume);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.log("onStop", bean);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.log("onDestroyView", bean);

    }
}
