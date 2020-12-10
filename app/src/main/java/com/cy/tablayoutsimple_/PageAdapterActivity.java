package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabViewHolder;

public class PageAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_adapter);

        final TabAdapter<PageBean> tabAdapter = new TabAdapter<PageBean>() {
            @Override
            public void bindDataToView(TabViewHolder holder, int position, PageBean bean, boolean isSelected) {
                holder.setImageResource(R.id.iv, bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(TabViewHolder holder, int position, PageBean bean) {
                Toast.makeText(PageAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };
        ViewPager2 viewPager2 = findViewById(R.id.vp2);
        viewPager2.setAdapter(tabAdapter);

        tabAdapter.add(new PageBean(R.drawable.pic1));
        tabAdapter.add(new PageBean(R.drawable.pic2));
//        tabAdapter.add(new PageBean(R.drawable.pic3));
//        tabAdapter.add(new PageBean(R.drawable.pic4));
//        tabAdapter.add(new PageBean(R.drawable.pic5));

        final  int size=tabAdapter.getItemCount();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                switch (size) {
                    case 1:
                        break;
                    case 2:
                        if (position == 0) {
                            int count =tabAdapter.getItemCount();
                            PageBean pageBean =null;
                            if(count>2){
                                pageBean=tabAdapter.getList_bean().get(count - 2);
                            }else {
                                pageBean=tabAdapter.getList_bean().get(count - 1);
                            }
                            if(count>2)tabAdapter.removeNoNotify(count - 1);
                            tabAdapter.addNoNotify(0, pageBean);
                            if(count>2)tabAdapter.notifyItemRemoved(count - 1);
                            tabAdapter.notifyItemInserted(0);
                        }
                        if (position == tabAdapter.getItemCount() - 1) {
                            int count =tabAdapter.getItemCount();
                            PageBean pageBean =null;
                            if(count>2){
                                pageBean=tabAdapter.getList_bean().get(1);
                            }else {
                                pageBean=tabAdapter.getList_bean().get(0);
                            }
                            if(count>2)tabAdapter.removeNoNotify(0);
                            tabAdapter.addNoNotify(pageBean);
                            if(count>2)tabAdapter.notifyItemRemoved(0);
                            tabAdapter.notifyItemInserted(tabAdapter.getItemCount());
                        }
                        break;
                    default:
                        if (position == 0) {
                            PageBean pageBean = tabAdapter.getList_bean().get(tabAdapter.getItemCount() - 1);
                            tabAdapter.removeNoNotify(tabAdapter.getItemCount() - 1);
                            tabAdapter.addNoNotify(0, pageBean);
                            tabAdapter.notifyItemRemoved(tabAdapter.getItemCount() - 1);
                            tabAdapter.notifyItemInserted(0);
                            break;
                        }
                        if (position == tabAdapter.getItemCount() - 1) {
                            PageBean pageBean = tabAdapter.getList_bean().get(0);
                            tabAdapter.removeNoNotify(0);
                            tabAdapter.addNoNotify(pageBean);
                            tabAdapter.notifyItemRemoved(0);
                            tabAdapter.notifyItemInserted(tabAdapter.getItemCount());
                        }
                        break;
                }

                LogUtils.log("size", tabAdapter.getItemCount());

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}
