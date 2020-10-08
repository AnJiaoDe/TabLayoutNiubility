package com.cy.tablayoutsimple_;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager2.widget.ViewPager2;

import com.cy.tablayoutniubility.FragPageAdapterVp2;
import com.cy.tablayoutniubility.TabLayoutScroll;
import com.cy.tablayoutniubility.TabMediatorVp2;
import com.cy.tablayoutniubility.TabViewHolder;
import com.cy.tablayoutniubility.TabAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTab1Vp2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTab1Vp2 extends Fragment {
    public ViewPager2 viewPager2;
    public static final String TAB_NAME1 = "TAB_NAME1";
    private View view;
    private TabLayoutScroll tabLayoutLine;
    private TabAdapter<String> tabAdapter;

    public FragmentTab1Vp2() {
    }

    public static FragmentTab1Vp2 newInstance(String key, String value) {
        FragmentTab1Vp2 fragment = new FragmentTab1Vp2();
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1_vp2, container, false);
        viewPager2 = view.findViewById(R.id.view_pager);
        tabLayoutLine = view.findViewById(R.id.tablayout);
        LogUtils.log("onCreateView");
        FragPageAdapterVp2<String> fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {
            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
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
        tabAdapter = new TabMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);
        if (getArguments() != null) {
            String tab_name1 = getArguments().getString(TAB_NAME1);
            List<String> list = new ArrayList<>();
            list.add(tab_name1 + "0");
            list.add(tab_name1 + "1");
            list.add(tab_name1 + "2");
            list.add(tab_name1 + "3");
            list.add(tab_name1 + "4");
            list.add(tab_name1 + "5");
            list.add(tab_name1 + "6");
            list.add(tab_name1 + "7");
            list.add(tab_name1 + "8");
            list.add(tab_name1 + "9");
            list.add(tab_name1 + "10");
            list.add(tab_name1 + "11");
            list.add(tab_name1 + "12");
            list.add(tab_name1 + "13");
            fragmentPageAdapter.add(list);
            tabAdapter.add(list);
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        LogUtils.log("onResume");
//        tabAdapter.notifyTabSelectedChanged();
    }
}
