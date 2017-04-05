package com.example.shirley.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Timeline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        TextView textView = (TextView) findViewById(R.id.handyn);
        TextView textView2 = (TextView) findViewById(R.id.zhoudyn);
        TextView textView3= (TextView) findViewById(R.id.mingdyna);
        TextView textView4 = (TextView) findViewById(R.id.qindynas);
        textView.setText("Han Dynasty:206-220CE");
        textView2.setText("Zhou Dynasty:1046-256BCE");
        textView3.setText("Ming Dynasty:1368-1644CE");
        textView.setText("Qin Dynasty:221-206BCE");
    }
}
