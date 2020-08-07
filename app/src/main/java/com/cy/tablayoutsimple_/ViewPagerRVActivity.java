package com.cy.tablayoutsimple_;//package com.cy.tablayoutsimple_;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import com.cy.tablayoutsimple.BaseViewHolder;
//import com.cy.tablayoutsimple.TabAdapter;
//import com.cy.tablayoutsimple.ViewPagerRV;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ViewPagerRVActivity extends AppCompatActivity {
//    private ViewPagerRV viewPagerRV;
//    private TabAdapter<String> simpleAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_pager_r_v);
//        viewPagerRV=findViewById(R.id.view_pager);
//
//        simpleAdapter=new TabAdapter<String>() {
//            @Override
//            public void bindDataToView(BaseViewHolder holder, int position, String bean, boolean isSelected) {
//                holder.setText(R.id.tv,bean);
//            }
//
//            @Override
//            public int getItemLayoutID(int position, String bean) {
//                return R.layout.item_viewpager_rv;
//            }
//
//            @Override
//            public void onItemClick(BaseViewHolder holder, int position, String bean) {
//
//            }
//        };
//        viewPagerRV.setAdapter(simpleAdapter);
//
//        List<String> list = new ArrayList<>();
//        list.add("关注");
//        list.add("推荐");
//        list.add("视频");
//        list.add("抗疫");
//        list.add("深圳");
//        list.add("热榜");
//        list.add("小视频");
//        list.add("软件");
//        list.add("探索");
//        list.add("在家上课");
//        list.add("手机");
//        list.add("动漫");
//        list.add("通信");
//        list.add("影视");
//        list.add("互联网");
//        list.add("设计");
//        list.add("家电");
//        list.add("平板");
//        list.add("网球");
//        list.add("军事");
//        list.add("羽毛球");
//        list.add("奢侈品");
//        list.add("美食");
//        list.add("瘦身");
//        list.add("幸福里");
//        list.add("棋牌");
//        list.add("奇闻");
//        list.add("艺术");
//        list.add("减肥");
//        list.add("电玩");
//        list.add("台球");
//        list.add("八卦");
//        list.add("酷玩");
//        list.add("彩票");
//        list.add("漫画");
//        simpleAdapter.add(list);
//
//    }
//}
