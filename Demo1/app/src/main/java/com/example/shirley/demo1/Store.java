package com.example.shirley.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Store extends AppCompatActivity {
    ImageButton b2, b3, b4, b5, b6;
    public void init(){
        b2 = (ImageButton) findViewById(R.id.map_navbar);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Store.this,Map.class);
                startActivity(intent);
            }
        });
    }
    public void init2(){
        b3 = (ImageButton) findViewById(R.id.train_navbar);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Store.this,Train.class);
                startActivity(intent);
            }
        });
    }
    public void init3(){
        b4 = (ImageButton) findViewById(R.id.history_navbar);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Store.this,Battle.class);
                startActivity(intent);
            }
        });
    }
    public void init4() {
        b5 = (ImageButton) findViewById(R.id.market_navbar);
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Store.this,Store.class);
                startActivity(intent);
            }
        });
    }
    public void init5() {
        b6 = (ImageButton) findViewById(R.id.setting_navbar);
        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Store.this,Setting.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        init();
        init2();
        init3();
        init4();
        init5();
    }

}
