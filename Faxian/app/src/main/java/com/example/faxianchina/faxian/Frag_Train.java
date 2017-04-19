package com.example.faxianchina.faxian;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Frag_Train extends Fragment {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mapstorage;
    private StorageReference swordmstorage;
    private StorageReference archerstorage;
    private StorageReference cavalrystorage;
    private StorageReference spearmanstorage;
    private ImageView swordman;
    private ImageView spearman;
    private ImageView archer;
    private ImageView cavalry;
    public Frag_Train(){

    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        archerstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/archer.png");
       swordmstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/swordman.png");
        spearmanstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/spearman.png");
        cavalrystorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/cavalry.png");
        View v = inflater.inflate(R.layout.frag__train, container,false);

        archer = (ImageView)v.findViewById(R.id.archer);
        Glide.with(this).using(new FirebaseImageLoader()).load(archerstorage).into(archer);
        swordman = (ImageView)v.findViewById(R.id.swordman);
        Glide.with(this).using(new FirebaseImageLoader()).load(swordmstorage).into(swordman);
        spearman = (ImageView)v.findViewById(R.id.spearman);
        Glide.with(this).using(new FirebaseImageLoader()).load(spearmanstorage).into(spearman);
        cavalry = (ImageView)v.findViewById(R.id.cavalry);
        Glide.with(this).using(new FirebaseImageLoader()).load(cavalrystorage).into(cavalry);



        return v;
    }
}