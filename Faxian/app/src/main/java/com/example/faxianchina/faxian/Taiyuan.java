package com.example.faxianchina.faxian;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felipecsl.gifimageview.library.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Taiyuan extends Fragment {

private GifImageView gifView;
    public Taiyuan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.frag_taiyuan, container, false);
        gifView = (GifImageView) v.findViewById(R.id.GifImage);

        return v;
    }

}
