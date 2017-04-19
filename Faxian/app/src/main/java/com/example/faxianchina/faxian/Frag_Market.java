package com.example.faxianchina.faxian;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_Market extends Fragment {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference bowstorage;
    private StorageReference swordstorage;
    private StorageReference spearstorage;
    private StorageReference horsestorage;
    private ImageView bow;
    private ImageView sword;
    private ImageView spear;
    private ImageView horse;

    public Frag_Market() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bowstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/bow.png");
        swordstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/sword2.png");
        spearstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/spear.png");
        horsestorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/horse.png");
        View v = inflater.inflate(R.layout.frag_market, container, false);
        bow = (ImageView)v.findViewById(R.id.bow);
        Glide.with(this).using(new FirebaseImageLoader()).load(bowstorage).into(bow);
        sword = (ImageView)v.findViewById(R.id.sword);
        Glide.with(this).using(new FirebaseImageLoader()).load(swordstorage).into(sword);
        spear = (ImageView)v.findViewById(R.id.spear);
        Glide.with(this).using(new FirebaseImageLoader()).load(spearstorage).into(spear);
        horse = (ImageView)v.findViewById(R.id.horse);
        Glide.with(this).using(new FirebaseImageLoader()).load(horsestorage).into(horse);
        return v;
    }

}
