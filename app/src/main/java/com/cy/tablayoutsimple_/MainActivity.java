package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  volatile Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_tab_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutVP2ItemCenterActivity.class));
            }
        });
        findViewById(R.id.btn_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutVP2Activity.class));
            }
        });
        findViewById(R.id.btn_tab_viewPager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutVPActivity.class));
            }
        });
        findViewById(R.id.btn_tab_gradient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutVP2GradientActivity.class));
            }
        });
        findViewById(R.id.btn_tab_triangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabTriangleVP2Activity.class));
            }
        });
        findViewById(R.id.btn_tab_vp_nest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutVpNestActivity.class));
            }
        });
        findViewById(R.id.btn_tab_vp2_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutMainActivity.class));
            }
        });
        findViewById(R.id.btn_tab_vp2_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutMultiActivity.class));
            }
        });
//        findViewById(R.id.btn_tab_vp_nest2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, TabLayoutVp2NestActivity.class));
//            }
//        });
//        findViewById(R.id.btn_tab_vp_custom).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ViewPagerRVActivity.class));
//            }
//        });

        obj = new Object();
        A(obj);
        obj = null;
        //虽然置为null，但是new 出来的对象一直在堆中，因为对象被线程持有了，如果A不使用参数，输出是true
        LogUtils.log("obj=null", obj == null);
    }

    private void A(final Object obj) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //输出false
                    LogUtils.log("obj", obj == null);
                }
            }
        });
        thread.start();
    }
}
