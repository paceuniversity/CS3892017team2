package com.example.shirley.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
    final ProgressBar iv = (ProgressBar)findViewById(R.id.progressBar);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
    iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener(){
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();

            }


            public void onAnimationRepeat(Animation animation){

            }
        });


    }

}
