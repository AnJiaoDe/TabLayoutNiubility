package com.cy.tablayoutsimple_;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.cy.tablayoutniubility.FragPageAdapterVp;
import com.cy.tablayoutniubility.IndicatorLineView;
import com.cy.tablayoutniubility.ScreenUtils;
import com.cy.tablayoutniubility.SimplePageAdapter;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabLayoutScroll;
import com.cy.tablayoutniubility.TabMediatorVp;
import com.cy.tablayoutniubility.TabViewHolder;
import com.cy.tablayoutniubility.ViewPagerHolder;

import java.util.ArrayList;
import java.util.List;

public class TabVpViewViewActivity extends AppCompatActivity {
    private SimplePageAdapter<String> simplePageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_vp_view_view);

        viewPager = findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine = findViewById(R.id.tablayout);
//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        simplePageAdapter = new SimplePageAdapter<String>() {
            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.item_view_2;
            }

            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, String bean) {
                ViewPager viewPager = holder.getView(R.id.view_pager);
                TabLayoutScroll tabLayoutLine = holder.getView(R.id.tablayout);
                SimplePageAdapter<String> simplePageAdapter = new SimplePageAdapter<String>() {
                    @Override
                    public int getItemLayoutID(int position, String bean) {
                        return R.layout.item_tv;
                    }


                    @Override
                    public void bindDataToView(ViewPagerHolder holder, int position, String bean) {
                        holder.setText(R.id.tv, bean);
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
                TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(simplePageAdapter);

                List<String> list = new ArrayList<>();
                list.add(bean + 0);
                list.add(bean + 323);
                list.add(bean + 33333);
                list.add(bean + 3);
                list.add(bean + 4545);
                list.add(bean + 4);
                list.add(bean + 4);
                list.add(bean + 5767);
                list.add(bean + 6765887);
                list.add(bean + 4);
                list.add(bean + 677);
                list.add(bean + 45);
                list.add(bean + 8);
                list.add(bean + 2);
                list.add(bean + 5);
                list.add(bean + 57);
                list.add(bean + 30);
                list.add(bean + 3454);
                simplePageAdapter.add(list);
                tabAdapter.add(list);
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

        TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPager).setAdapter(simplePageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("视频");
        list.add("抗疫");
        list.add("深圳");
        list.add("艺术");
        simplePageAdapter.add(list);
        tabAdapter.add(list);

    }
}