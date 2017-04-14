package com.example.faxianchina.faxian;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// get the reference of FrameLayout and TabLayout
        frame = (FrameLayout) findViewById(R.id.framelayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        TabLayout.Tab time = tabLayout.newTab();

      View view1 = getLayoutInflater().inflate(R.layout.iconlayout, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.timeline_button);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));

        //time.setIcon(R.drawable.timeline_button); // set an icon for the



        TabLayout.Tab map = tabLayout.newTab();
        View view2 = getLayoutInflater().inflate(R.layout.iconlayout, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.map);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));

        TabLayout.Tab train = tabLayout.newTab();
        View view3 = getLayoutInflater().inflate(R.layout.iconlayout, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.trainbutton);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));


        TabLayout.Tab market = tabLayout.newTab();
        View view4 = getLayoutInflater().inflate(R.layout.iconlayout, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.marketbutton);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view4));

        TabLayout.Tab history = tabLayout.newTab();
        View view5 = getLayoutInflater().inflate(R.layout.iconlayout, null);
        view5.findViewById(R.id.icon).setBackgroundResource(R.drawable.history_button);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view5));

        TabLayout.Tab setting = tabLayout.newTab();
        View view6 = getLayoutInflater().inflate(R.layout.iconlayout, null);
        view6.findViewById(R.id.icon).setBackgroundResource(R.drawable.settingbutton);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view6));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new Frag_Timeline();
                        break;
                    case 1:
                        fragment = new Frag_Map();
                        break;
                    case 2:
                        fragment = new Frag_Train();
                        break;
                    case 3:
                        fragment = new Frag_Market();
                        break;
                    case 4:
                        fragment = new Frag_History();
                        break;
                    case 5:
                        fragment = new Frag_Settings();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.framelayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
