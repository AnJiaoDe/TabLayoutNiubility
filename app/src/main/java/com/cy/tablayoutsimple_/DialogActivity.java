package com.cy.tablayoutsimple_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cy.tablayoutniubility.SimplePageAdapter;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabLayoutScroll;
import com.cy.tablayoutniubility.TabMediatorVp;
import com.cy.tablayoutniubility.TabViewHolder;
import com.cy.tablayoutniubility.ViewPagerHolder;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends AppCompatActivity {
    private VPDialog vpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ViewPager viewPager = findViewById(R.id.ViewPager);
        TabLayoutScroll tabLayoutScroll = findViewById(R.id.TabLayoutScroll);
        SimplePageAdapter<String> simplePageAdapter = new SimplePageAdapter<String>(viewPager) {
            @Override
            public void onPageSelected(ViewPagerHolder holder, int position, @NonNull String bean) {
                super.onPageSelected(holder, position, bean);
                LogUtils.log("onPageSelected", bean);
            }

            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.item_vp_dialog;
            }

            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, String bean) {
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

            @Override
            public void onTabClick(TabViewHolder holder, int position, String bean) {
                super.onTabClick(holder, position, bean);
                LogUtils.log("onPageSelected onTabClick", bean);
            }
        };
        TabAdapter<String> tabAdapter =
                new TabMediatorVp<String>(tabLayoutScroll, viewPager).setAdapter(simplePageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("视频");
        list.add("抗疫");
        list.add("深圳");
        list.add("热榜");
        list.add("小视频");
        list.add("软件");
        list.add("探索");
        list.add("在家上课");
        list.add("手机");
        list.add("动漫");
        list.add("通信");
        list.add("影视");
        list.add("互联网");
        list.add("设计");
        list.add("家电");
        list.add("平板");
        list.add("网球");
        list.add("军事");
        list.add("羽毛球");
        list.add("奢侈品");
        list.add("美食");
        list.add("瘦身");
        list.add("幸福里");
        list.add("棋牌");
        list.add("奇闻");
        list.add("艺术");
        list.add("减肥");
        list.add("电玩");
        list.add("台球");
        list.add("八卦");
        list.add("酷玩");
        list.add("彩票");
        list.add("漫画");
        simplePageAdapter.add(list);
        tabAdapter.add(list);

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vpDialog == null) vpDialog = new VPDialog(DialogActivity.this);
                vpDialog.show();
            }
        });
    }
}