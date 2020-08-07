package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        findViewById(R.id.btn_tab_vp_nest2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabLayoutVp2NestActivity.class));
            }
        });
//        findViewById(R.id.btn_tab_vp_custom).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ViewPagerRVActivity.class));
//            }
//        });
    }
}
