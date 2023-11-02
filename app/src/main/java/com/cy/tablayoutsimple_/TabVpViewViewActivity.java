package com.cy.tablayoutsimple_;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cy.tablayoutniubility.BaseSimplePageAdapter;
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
        simplePageAdapter = new com.cy.tablayoutniubility.SimplePageAdapter<String>(viewPager) {
            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.item_view_2;
            }

            @Override
            public void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull String bean) {
                LogUtils.log("onPageSelected父", position);
            }

            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, final String bean) {
               final ViewPager viewPagerChild = holder.getView(R.id.view_pager);
                TabLayoutScroll tabLayoutLine = holder.getView(R.id.tablayout);

//                holder.setOnClickListener(R.id.btn_child0, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPagerChild.setCurrentItem(0);
//                    }
//                });
//                holder.setOnClickListener(R.id.btn_child1, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPagerChild.setCurrentItem(1);
//                    }
//                });
//                holder.setOnClickListener(R.id.btn_child2, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPagerChild.setCurrentItem(2);
//                    }
//                });
//                holder.setOnClickListener(R.id.btn_child3, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPagerChild.setCurrentItem(3);
//                    }
//                });
//                holder.setOnClickListener(R.id.btn_child4, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPagerChild.setCurrentItem(4);
//                    }
//                });
                SimplePageAdapterChild simplePageAdapterChild=new SimplePageAdapterChild(viewPagerChild,this,position);
                TabAdapter<String> tabAdapter = new TabMediatorVp<String>(tabLayoutLine, viewPagerChild).setAdapter(simplePageAdapterChild);

                List<String> list = new ArrayList<>();
                list.add(bean + "0");
                list.add(bean + "1");
                list.add(bean + "2");
                list.add(bean + "3");
                list.add(bean + "4");
                simplePageAdapterChild.add(list);
                tabAdapter.add(list);

//                viewPagerChild.setCurrentItem(2);
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

//        findViewById(R.id.btn_parent0).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(0);
//            }
//        });
//        findViewById(R.id.btn_parent1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(1);
//            }
//        });
//        findViewById(R.id.btn_parent2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(2);
//            }
//        });
//        findViewById(R.id.btn_parent3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(3);
//            }
//        });
//        findViewById(R.id.btn_parent4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(4);
//            }
//        });
//        findViewById(R.id.btn_parent5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(5);
//            }
//        });
//        viewPager.setCurrentItem(3);
    }
    private static class SimplePageAdapterChild extends SimplePageAdapter<String>{

        public SimplePageAdapterChild(ViewPager viewPager, BaseSimplePageAdapter adapterParent, int positionParent) {
            super(viewPager, adapterParent, positionParent);
        }
        @Override
        public int getItemLayoutID(int position, String bean) {
            return R.layout.item_view_2;
        }

        @Override
        public void onPageSelected(ViewPagerHolder holder, int position, @NonNull String bean) {
            LogUtils.log(bean + "onPageSelected子", position);
        }

        @Override
        public void bindDataToView(ViewPagerHolder holder, int position, String bean) {
            final ViewPager viewPagerChild = holder.getView(R.id.view_pager);
            TabLayoutScroll tabLayoutLine = holder.getView(R.id.tablayout);
            SimplePageAdapterChild2 simplePageAdapterChild2=new SimplePageAdapterChild2(viewPagerChild,this,position);
            TabAdapter<String> tabAdapter =
                    new TabMediatorVp<String>(tabLayoutLine, viewPagerChild).setAdapter(simplePageAdapterChild2);

            List<String> list = new ArrayList<>();
            list.add(bean + "孙0");
            list.add(bean + "孙1");
            list.add(bean + "孙2");
            list.add(bean + "孙3");
            list.add(bean + "孙4");
            simplePageAdapterChild2.add(list);
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
    }
    private static class SimplePageAdapterChild2 extends SimplePageAdapter<String>{

        public SimplePageAdapterChild2(ViewPager viewPager, BaseSimplePageAdapter adapterParent, int positionParent) {
            super(viewPager, adapterParent, positionParent);
        }
        @Override
        public int getItemLayoutID(int position, String bean) {
            return R.layout.item_view_2;
        }

        @Override
        public void onPageSelected(ViewPagerHolder holder, int position, @NonNull String bean) {
            LogUtils.log(bean + "onPageSelected孙子", position);
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
    }
}