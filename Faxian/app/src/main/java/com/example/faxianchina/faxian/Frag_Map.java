package com.example.faxianchina.faxian;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Frag_Map extends Fragment {


    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mapstorage;
    private StorageReference citystorage;
    private ImageView map;
    private ImageButton city1;
    private ImageButton city2;
    private ImageButton city3;
    private ImageButton city4;
    private ImageButton city5;
    private ImageButton city6;

    public Frag_Map(){


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mapstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/hanmap.png");
        citystorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/cityicon1.png");

        View v = inflater.inflate(R.layout.frag__map, container,false);
        map = (ImageView)v.findViewById(R.id.map);
        city1 = (ImageButton)v.findViewById(R.id.Loyang);
        city2 = (ImageButton)v.findViewById(R.id.Changan);
        city3 = (ImageButton)v.findViewById(R.id.Chengdu);
        city4 = (ImageButton)v.findViewById(R.id.Linzi);
        city5 = (ImageButton)v.findViewById(R.id.Taiyuan);
        city6 = (ImageButton)v.findViewById(R.id.Changsha);

        Glide.with(this).using(new FirebaseImageLoader()).load(mapstorage).into(map);
        Glide.with(this).using(new FirebaseImageLoader()).load(citystorage).into(city1);
        Glide.with(this).using(new FirebaseImageLoader()).load(citystorage).into(city2);
        Glide.with(this).using(new FirebaseImageLoader()).load(citystorage).into(city3);
        Glide.with(this).using(new FirebaseImageLoader()).load(citystorage).into(city4);
        Glide.with(this).using(new FirebaseImageLoader()).load(citystorage).into(city5);
        Glide.with(this).using(new FirebaseImageLoader()).load(citystorage).into(city6);
        return v;
    }
  //  @Override
  //  public void onDestroyView() {
   //     super.onDestroyView();
    //    Frag_Map f = (Frag_Map) getFragmentManager()
    //            .findFragmentById(R.id.map);
     //   if (f != null)
     //       getFragmentManager().beginTransaction().remove(f).commit();
    //}
}
