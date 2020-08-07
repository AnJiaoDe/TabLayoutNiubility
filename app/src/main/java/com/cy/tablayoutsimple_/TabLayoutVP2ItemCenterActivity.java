package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.cy.tablayoutniubility.FragmentPageAdapterVp2NoScroll;
import com.cy.tablayoutniubility.TabLayoutMediatorVp2;
import com.cy.tablayoutniubility.TabLayoutNiubility;
import com.cy.tablayoutniubility.TabViewHolder;
import com.cy.tablayoutniubility.FragmentPageAdapterVp2;
import com.cy.tablayoutniubility.TabAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutVP2ItemCenterActivity extends AppCompatActivity {
    public ViewPager2 viewPager2;
    private TabLayoutNiubility tabLayoutLine;
    private TabAdapter<String> tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_v_p2_item_center);
        viewPager2 = findViewById(R.id.view_pager);
        tabLayoutLine = findViewById(R.id.tablayout);



//        tabLayoutLine.setSpace_horizontal(0).setSpace_vertical(0);
        FragmentPageAdapterVp2NoScroll<String> fragmentPageAdapter = new FragmentPageAdapterVp2NoScroll<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTabInner(TabViewHolder holder, int position, String bean, boolean isSelected) {
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
            public int getTabInnerLayoutID(int position, String bean) {
                return R.layout.item_tab_center;
            }
        };

        tabAdapter = new TabLayoutMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("上课");
        list.add("抗疫");
//        list.add("深圳");
//        list.add("热榜");
//        list.add("小视频");
//        list.add("软件");
//        list.add("探索");
//        list.add("在家上课");
//        list.add("手机");
//        list.add("动漫");
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
