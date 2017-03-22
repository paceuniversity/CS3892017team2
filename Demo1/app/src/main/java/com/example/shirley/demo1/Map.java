package com.example.shirley.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Map extends AppCompatActivity {

    ImageButton b2, b3, b4, b5;

    public void init(){
        b2 = (ImageButton) findViewById(R.id.imageButton1);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,Map.class);
                startActivity(intent);
            }
        });
    }
    public void init2(){
        b3 = (ImageButton) findViewById(R.id.imageButton2);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this, Train.class);
                startActivity(intent);
            }
        });
    }
    public void init3(){
        b4 = (ImageButton) findViewById(R.id.imageButton4);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,Battle.class);
                startActivity(intent);
            }
        });
    }
    public void init4() {
        b5 = (ImageButton) findViewById(R.id.imageButton4);
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,Store.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
        init2();
        init3();
        init4();

    }

}
