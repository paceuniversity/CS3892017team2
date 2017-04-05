package com.example.shirley.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Battle extends AppCompatActivity {

    ImageButton b2, b3, b4, b5, b6;
    public void init(){
        b2 = (ImageButton) findViewById(R.id.map_navbar);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this,Map.class);
                startActivity(intent);
            }
        });
    }
    public void init2(){
        b3 = (ImageButton) findViewById(R.id.train_navbar);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this,Train.class);
                startActivity(intent);
            }
        });
    }
    public void init3(){
        b4 = (ImageButton) findViewById(R.id.history_navbar);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this,History.class);
                startActivity(intent);
            }
        });
    }
    public void init4() {
        b5 = (ImageButton) findViewById(R.id.market_navbar);
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this,Store.class);
                startActivity(intent);
            }
        });
    }
    public void init5() {
        b6 = (ImageButton) findViewById(R.id.setting_navbar);
        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this,Setting.class);
                startActivity(intent);
            }
        });
    }
    public void init6() {
        b6 = (ImageButton) findViewById(R.id.timeline);
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this, Timeline.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        init();
        init2();
        init3();
        init4();
        init5();
        init6();
        TextView textView = (TextView) findViewById(R.id.dummy);
        textView.setText("Battle of Changan");

    }
}
