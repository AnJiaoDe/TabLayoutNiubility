package com.cy.tablayoutsimple_;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTab2 extends Fragment {
    public static final String TAB_NAME2 = "TAB_NAME2";
    private View view;

    public FragmentTab2() {
    }

    public static FragmentTab2 newInstance(String key, String value) {
        FragmentTab2 fragment = new FragmentTab2();
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab2, parent, false);


//        LogUtils.log("onCreateView");

        final LinearLayout rootView = view.findViewById(R.id.rootView);
        String tab_name2 = "";
        if (getArguments() != null) {
            tab_name2 = getArguments().getString(TAB_NAME2);
        }

        TextView textView = view.findViewById(R.id.tv);
        textView.setText(tab_name2);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.log("onResume");

    }
}
