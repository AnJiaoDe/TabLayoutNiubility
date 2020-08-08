package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.cy.tablayoutniubility.FragmentPageAdapterVp2;
import com.cy.tablayoutniubility.FragmentPageAdapterVp2NoScroll;
import com.cy.tablayoutniubility.TabMediatorVp2NoScroll;
import com.cy.tablayoutniubility.TabLayoutNoScroll;
import com.cy.tablayoutniubility.TabNoScrollAdapter;
import com.cy.tablayoutniubility.TabNoScrollViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutMainActivity extends AppCompatActivity {
    public ViewPager2 viewPager2;
    private TabLayoutNoScroll tabLayoutNoScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);
        viewPager2 = findViewById(R.id.view_pager);
        tabLayoutNoScroll = findViewById(R.id.tablayout);
        FragmentPageAdapterVp2NoScroll<TabBean> fragmentPageAdapter = new FragmentPageAdapterVp2NoScroll<TabBean>(this) {

            @Override
            public Fragment createFragment(TabBean bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position).getText());
            }

            @Override
            public void bindDataToTab(TabNoScrollViewHolder holder, int position, TabBean bean, boolean isSelected) {
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
            public int getTabLayoutID(int position, TabBean bean) {
                if (position == 2) {
                    return R.layout.item_tab_main_circle;
                }
                return R.layout.item_tab_main;
            }
        };

        TabNoScrollAdapter<TabBean> tabAdapter = new TabMediatorVp2NoScroll<TabBean>(tabLayoutNoScroll, viewPager2).setAdapter(fragmentPageAdapter);

        List<TabBean> list = new ArrayList<>();
        list.add(new TabBean("消息",R.drawable.msg,R.drawable.msg_selected));
        list.add(new TabBean("通讯录",R.drawable.friends,R.drawable.friends_selected));
        list.add(new TabBean("朋友圈",R.drawable.circle,R.drawable.circle_selected));
        list.add(new TabBean("我",R.drawable.my,R.drawable.my_selected));
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
