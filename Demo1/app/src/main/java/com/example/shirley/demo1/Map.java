package com.example.shirley.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class Map extends AppCompatActivity {

    private PopupWindow pop;
    ImageButton b2, b3, b4, b5, b6;
    ImageButton c1,c2,c3,c4,c5;

    public void init(){
        b2 = (ImageButton) findViewById(R.id.map_navbar);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,Map.class);
                startActivity(intent);
            }
        });
    }
    public void init2(){
        b3 = (ImageButton) findViewById(R.id.train_navbar);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this, Train.class);
                startActivity(intent);
            }
        });
    }
    public void init3(){
        b4 = (ImageButton) findViewById(R.id.history_navbar);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,History.class);
                startActivity(intent);
            }
        });
    }
    public void init4() {
        b5 = (ImageButton) findViewById(R.id.market_navbar);
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,Store.class);
                startActivity(intent);
            }
        });
    }
    public void init5() {
        b6 = (ImageButton) findViewById(R.id.setting_navbar);
        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,Setting.class);
                startActivity(intent);
            }
        });
    }
    public void city1() {
        c1 = (ImageButton) findViewById(R.id.Changan);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_battle);

            }
        });
    }
    public void city2() {
        c2 = (ImageButton) findViewById(R.id.Changsha);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_changsha);

            }
        });
    }
    public void city3() {
        c3 = (ImageButton) findViewById(R.id.Luoyang);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_luoyang);

            }
        });
    }
    public void city4() {
        c4 = (ImageButton) findViewById(R.id.Chengdu);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_chengdu);

            }
        });
    }
    public void city5() {
        c5 = (ImageButton) findViewById(R.id.Linzi);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_linzi);

            }
        });
    }
    public void city6() {
        c1 = (ImageButton) findViewById(R.id.Taiyuan);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_taiyuan);

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();init2();init3();init4();init5();
        city1();city2();city3();city4();city5();city6();
    }
}
