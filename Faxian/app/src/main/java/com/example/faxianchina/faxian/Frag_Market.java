package com.example.faxianchina.faxian;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private ImageView bow, sword, spear, horse;

    private Button buyBowButton, buySwordButton, buySpearButton, buyHorseButton;
    private EditText bowInput, swordInput, spearInput, horseInput;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = database.getReference();
    private String money;
    Integer units, perUnit,total,balance;
    //private Context context;

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
   buyBowButton = (Button)v.findViewById(R.id.buyBows);
        buySwordButton = (Button)v.findViewById(R.id.buySwords);
        buySpearButton = (Button)v.findViewById(R.id.buySpears);
        buyHorseButton = (Button)v.findViewById(R.id.buyHorses);

        bowInput = (EditText)v.findViewById(R.id.bowNum);
        swordInput = (EditText)v.findViewById(R.id.swordNum);
        spearInput = (EditText)v.findViewById(R.id.spearNum);
        horseInput = (EditText)v.findViewById(R.id.horseNum);

        bow = (ImageView) v.findViewById(R.id.bow);
        Glide.with(this).using(new FirebaseImageLoader()).load(bowstorage).into(bow);
        sword = (ImageView) v.findViewById(R.id.sword);
        Glide.with(this).using(new FirebaseImageLoader()).load(swordstorage).into(sword);
        spear = (ImageView) v.findViewById(R.id.spear);
        Glide.with(this).using(new FirebaseImageLoader()).load(spearstorage).into(spear);
        horse = (ImageView) v.findViewById(R.id.horse);
        Glide.with(this).using(new FirebaseImageLoader()).load(horsestorage).into(horse);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                //String value = dataSnapshot.child("Market_weapons").child("Bow").toString();
                //String numBow = bowInput.getText().toString();
                buyBowButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();

                        String numBows = bowInput.getText().toString();
                        mRef.child("Market_weapons").child("Bow").child("amountBought").setValue(numBows);

                        if(!TextUtils.isEmpty(numBows)) {
                            //Integer spears = Integer.parseInt(numSpears);
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + numBows + " bows!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }else{
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought zero bows!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }
                        units = Integer.parseInt(numBows);

                        perUnit = Integer.parseInt(dataSnapshot.child("Market_weapons").child("Bow").child("cost").getValue().toString());
                        total = units*perUnit;
                        balance = Integer.parseInt(money);

                        mRef.child("Coins").child("totalCoins").setValue(balance-total);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error", "error");
            }
        });
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                buySwordButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();

                        String numSwords = swordInput.getText().toString();
                        mRef.child("Market_weapons").child("Sword").child("amountBought").setValue(numSwords);

                        if(!TextUtils.isEmpty(numSwords)) {
                            //Integer spears = Integer.parseInt(numSpears);
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + numSwords + " swords!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }else{
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought zero spears!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }
                        units = Integer.parseInt(numSwords);

                        perUnit = Integer.parseInt(dataSnapshot.child("Market_weapons").child("Sword").child("cost").getValue().toString());
                        total = units*perUnit;
                        balance = Integer.parseInt(money);

                        mRef.child("Coins").child("totalCoins").setValue(balance-total);

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                buySpearButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();

                        String numSpears = spearInput.getText().toString();
                        mRef.child("Market_weapons").child("Spear").child("amountBought").setValue(numSpears);

                        if(!TextUtils.isEmpty(numSpears)) {
                            //Integer spears = Integer.parseInt(numSpears);
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + numSpears + " spears!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }else{
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought zero spears!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }

                        units = Integer.parseInt(numSpears);

                        perUnit = Integer.parseInt(dataSnapshot.child("Market_weapons").child("Spear").child("cost").getValue().toString());
                        total = units*perUnit;
                        balance = Integer.parseInt(money);

                        mRef.child("Coins").child("totalCoins").setValue(balance-total);

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                buyHorseButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                        String numHorse = horseInput.getText().toString();
                        mRef.child("Market_weapons").child("Horse").child("amountBought").setValue(numHorse);


                        if(!TextUtils.isEmpty(numHorse)) {
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + numHorse + " horses!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }else{
                            Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought zero horses!", Toast.LENGTH_SHORT);
                            horseToast.show();
                        }

                        units = Integer.parseInt(numHorse);

                        perUnit = Integer.parseInt(dataSnapshot.child("Market_weapons").child("Horse").child("cost").getValue().toString());
                        total = units*perUnit;
                        balance = Integer.parseInt(money);

                        mRef.child("Coins").child("totalCoins").setValue(balance-total);


                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }
}


