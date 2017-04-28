package com.example.faxianchina.faxian;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class Frag_Train extends Fragment {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mapstorage;
    private StorageReference swordmstorage, archerstorage,cavalrystorage,spearmanstorage;
    private ImageView swordman,spearman,archer,cavalry;
    private Button buyArcherButton, buySwordsmenButton, buySpearmenButton, buyCavalryButton;
    private EditText archerInput, swordsmenInput, spearmenInput, cavalryInput;
    private TextView archerPrice, swordsmenPrice, spearmentPrice, cavalryPrice;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = database.getReference();
    private String amount,money,sPop;
    private Integer units,total,perUnit,balance;//For decreasing coins
    private Integer population;//For decreasing population


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

        // BUTTONS
        buyArcherButton = (Button)v.findViewById(R.id.buyArchers);
        buySwordsmenButton = (Button)v.findViewById(R.id.buySwordmen);
        buySpearmenButton = (Button)v.findViewById(R.id.buySpearmen);
        buyCavalryButton = (Button)v.findViewById(R.id.buyCavalry);

        // Text boxes for amount to buy
        archerInput = (EditText)v.findViewById(R.id.archersNum);
        swordsmenInput = (EditText)v.findViewById(R.id.swordmenNum);
        spearmenInput = (EditText)v.findViewById(R.id.spearmenNum);
        cavalryInput = (EditText)v.findViewById(R.id.cavalryNum);

        // Text boxes of price
        /*archerPrice = (TextView)v.findViewById(R.id.archerPrice);
        swordsmenPrice = (TextView)v.findViewById(R.id.swordsmenPrice);
        spearmentPrice = (TextView)v.findViewById(R.id.spearmenPrice);
        cavalryPrice = (TextView)v.findViewById(R.id.cavalryPrice);*/

        archer = (ImageView)v.findViewById(R.id.archers);
        Glide.with(this).using(new FirebaseImageLoader()).load(archerstorage).into(archer);
        swordman = (ImageView)v.findViewById(R.id.swordman);
        Glide.with(this).using(new FirebaseImageLoader()).load(swordmstorage).into(swordman);
        spearman = (ImageView)v.findViewById(R.id.spearman);
        Glide.with(this).using(new FirebaseImageLoader()).load(spearmanstorage).into(spearman);
        cavalry = (ImageView)v.findViewById(R.id.cavalry);
        Glide.with(this).using(new FirebaseImageLoader()).load(cavalrystorage).into(cavalry);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                buyArcherButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sPop = dataSnapshot.child("population").child("currPopulation").getValue().toString();//Retreives original population
                        money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();//Retrieves the number of coins
                        amount = archerInput.getText().toString();//Retrieves what the user put for number of archers to train
                        mRef.child("Market_soldiers").child("Archer").child("amountBought").setValue(amount);
                        balance = Integer.parseInt(money);

                        if(!TextUtils.isEmpty(amount)){
                            Toast archerToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought "+ amount + " archers!", Toast.LENGTH_SHORT);
                        }else{
                            Toast archerToast = Toast.makeText(v.getContext().getApplicationContext(),"You bought zero archers!", Toast.LENGTH_SHORT);
                            archerToast.show();
                        }

                        //**MONEY**//
                        units = Integer.parseInt(amount);
                        perUnit = Integer.parseInt(dataSnapshot.child("Market_soldiers").child("Archer").child("costPeople").getValue().toString());//Retrieves the value of each archer/batallion from DB
                        total = units*perUnit;

                        mRef.child("Coins").child("totalCoins").setValue(balance-total);//Stores in DB the number of coins minus the amount of soldiers trained multiplied by cost of each soldier
                        //**PEOPLE**//
                        population = Integer.parseInt(sPop);
                        mRef.child("population").child("currPopulation").setValue(population-units);//Stores in DB users current population minus by amount of soldiers trained

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
                buySwordsmenButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amount = swordsmenInput.getText().toString();
                        sPop = dataSnapshot.child("population").child("currPopulation").getValue().toString();//Retreives original population
                        mRef.child("Market_soldiers").child("Swordsmen").child("amountBought").setValue(amount);
                        balance = Integer.parseInt(money);

                        if(!TextUtils.isEmpty(amount)){
                            Toast archerToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought "+ amount + " swordsmen!", Toast.LENGTH_SHORT);
                            archerToast.show();
                        }else{
                            Toast archerToast = Toast.makeText(v.getContext().getApplicationContext(),"You bought zero swordsmen!", Toast.LENGTH_SHORT);
                            archerToast.show();
                        }

                        //**MONEY**//
                        units = Integer.parseInt(amount);
                        perUnit = Integer.parseInt(dataSnapshot.child("Market_soldiers").child("Swordsmen").child("costPeople").getValue().toString());//Retrieves the value of each swordsmen/batallion from DB
                        total = units*perUnit;

                        mRef.child("Coins").child("totalCoins").setValue(balance-total);//Stores in DB the number of coins minus the amount of soldiers trained multiplied by cost of each soldier
                        //**PEOPLE**//
                        population = Integer.parseInt(sPop);
                        mRef.child("population").child("currPopulation").setValue(population-units);//Stores in DB users current population minus by amount of soldiers trained

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buyCavalryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amount = cavalryInput.getText().toString();
                        mRef.child("Market_soldiers").child("Cavalry").child("amountBought").setValue(amount);

                        if(!TextUtils.isEmpty(amount)){
                            Toast cavalryToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought "+ amount + " horsemen!", Toast.LENGTH_SHORT);
                            cavalryToast.show();
                        }else{
                            Toast cavalryToast = Toast.makeText(v.getContext().getApplicationContext(),"You bought zero horsemen!", Toast.LENGTH_SHORT);
                            cavalryToast.show();
                        }

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