package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.cy.tablayoutniubility.FragPageAdapterVp2NoScroll;
import com.cy.tablayoutniubility.TabMediatorVp2NoScroll;
import com.cy.tablayoutniubility.TabLayoutNoScroll;
import com.cy.tablayoutniubility.TabAdapterNoScroll;
import com.cy.tablayoutniubility.TabNoScrollViewHolder;
import com.cy.tablayoutniubility.TabSelectBean;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);
        ViewPager2 viewPager2= findViewById(R.id.view_pager);
        TabLayoutNoScroll tabLayoutNoScroll= findViewById(R.id.tablayout);
        FragPageAdapterVp2NoScroll<TabSelectBean> fragmentPageAdapter = new FragPageAdapterVp2NoScroll<TabSelectBean>(this) {

            @Override
            public Fragment createFragment(TabSelectBean bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position).getText());
            }

            @Override
            public void bindDataToTab(TabNoScrollViewHolder holder, int position, TabSelectBean bean, boolean isSelected) {
                TextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTextColor(0xff00ff00);
                    holder.setImageResource(R.id.iv,bean.getResID_selected());
                } else {
                    textView.setTextColor(0xff444444);
                    holder.setImageResource(R.id.iv,bean.getResID_normal());
                }
                textView.setText(bean.getText());
            }

            @Override
            public int getTabLayoutID(int position, TabSelectBean bean) {
                if (position == 2) {
                    return R.layout.item_tab_main_circle;
                }
                return R.layout.item_tab_main;
            }
        };

        TabAdapterNoScroll<TabSelectBean> tabAdapter = new TabMediatorVp2NoScroll<TabSelectBean>(tabLayoutNoScroll, viewPager2).setAdapter(fragmentPageAdapter);

        List<TabSelectBean> list = new ArrayList<>();
        list.add(new TabSelectBean("消息",R.drawable.msg,R.drawable.msg_selected));
        list.add(new TabSelectBean("通讯录",R.drawable.friends,R.drawable.friends_selected));
        list.add(new TabSelectBean("朋友圈",R.drawable.circle,R.drawable.circle_selected));
        list.add(new TabSelectBean("我",R.drawable.my,R.drawable.my_selected));
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
