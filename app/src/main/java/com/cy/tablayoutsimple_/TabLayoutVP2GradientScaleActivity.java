package com.cy.tablayoutsimple_;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.cy.tablayoutniubility.FragPageAdapterVp2;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabGradientTextView;
import com.cy.tablayoutniubility.TabLayoutScroll;
import com.cy.tablayoutniubility.TabMediatorVp2;
import com.cy.tablayoutniubility.TabViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutVP2GradientScaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_r_v_gradient_scale);
        ViewPager2 viewPager2= findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine= findViewById(R.id.tablayout);

//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragPageAdapterVp2<String> fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    textView.setTextColor(0xffe45540);
                    textView.setTextSize(spAdapt(TabLayoutVP2GradientScaleActivity.this,20));
                } else {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    textView.setTextColor(0xff444444);
                    textView.setTextSize(spAdapt(TabLayoutVP2GradientScaleActivity.this,16));
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab_scale;
            }

            @Override
            public void onTabScrolled(TabViewHolder holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent, TabViewHolder holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {
                super.onTabScrolled(holderCurrent, positionCurrent, fromLeft2RightCurrent, positionOffsetCurrent, holder2, position2, fromLeft2Right2, positionOffset2);
                TextView textViewCurrent = holderCurrent.getView(R.id.tv);
                TextView textView2= holder2.getView(R.id.tv);
                LogUtils.log("onTabScrolled");

                textViewCurrent.setTextSize(Math.max(spAdapt(TabLayoutVP2GradientScaleActivity.this,20)*positionOffsetCurrent,
                        spAdapt(TabLayoutVP2GradientScaleActivity.this,16)));
                textView2.setTextSize(Math.max(spAdapt(TabLayoutVP2GradientScaleActivity.this,20)*positionOffset2,
                        spAdapt(TabLayoutVP2GradientScaleActivity.this,16)));
            }
        };

        TabAdapter<String> tabAdapter = new TabMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

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
    public  int spAdapt(Context context, float sp) {

        return spAdapt(context,sp,360);
    }
    public  int spAdapt(Context context, float sp, float widthDpBase) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;//高的像素
        int widthPixels = dm.widthPixels;//宽的像素
//        int densityDpi = dm.densityDpi;//dpi
//        float xdpi = dm.xdpi;//xdpi
//        float ydpi = dm.ydpi;//ydpi
        float density = dm.density;//density=dpi/160,密度比
//        float scaledDensity = dm.scaledDensity;//scaledDensity=dpi/160 字体缩放密度比
        float heightDP = heightPixels / density;//高度的dp
        float widthDP = widthPixels / density;//宽度的dp
        float w = widthDP > heightDP ? heightDP : widthDP;
//        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (sp * w / widthDpBase + 0.5f);
    }
}
