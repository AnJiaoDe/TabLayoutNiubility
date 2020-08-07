package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.cy.tablayoutniubility.TabViewHolder;
import com.cy.tablayoutniubility.FragmentPageAdapterVp2;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabLayoutMediatorVp2;
import com.cy.tablayoutniubility.TabLayoutNiubility;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutVp2NestActivity extends AppCompatActivity {
    public ViewPager2 viewPager2;
    private TabLayoutNiubility tabLayoutLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_vp2_nest);
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.requestDisallowInterceptTouchEvent(true);
        tabLayoutLine = findViewById(R.id.tablayout);



//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragmentPageAdapterVp2<String> fragmentPageAdapter = new FragmentPageAdapterVp2<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab1Vp2.newInstance(FragmentTab1Vp2.TAB_NAME1, getList_bean().get(position));
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
                if (position == 0) {
                    return R.layout.item_tab_msg;
                }
                return R.layout.item_tab;
            }
        };

        TabAdapter<String> tabAdapter = new TabLayoutMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

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
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
    }

    /**
     * --------------------------------------------------------------------------------
     */
    public int dpAdapt(float dp) {
        return dpAdapt(dp, 360);
    }

    public int dpAdapt(float dp, float widthDpBase) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;//高的像素
        int widthPixels = dm.widthPixels;//宽的像素
        float density = dm.density;//density=dpi/160,密度比
        float heightDP = heightPixels / density;//高度的dp
        float widthDP = widthPixels / density;//宽度的dp
        float w = widthDP > heightDP ? heightDP : widthDP;
        return (int) (dp * w / widthDpBase * density + 0.5f);
    }
}
