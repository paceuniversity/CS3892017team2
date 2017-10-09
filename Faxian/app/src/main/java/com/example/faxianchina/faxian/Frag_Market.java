package com.example.faxianchina.faxian;

import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
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

import org.w3c.dom.Text;

public class Frag_Market extends Fragment {

//    private FirebaseStorage storage = FirebaseStorage.getInstance();
//    private StorageReference mapstorage;
//    private StorageReference swordmstorage, archerstorage,cavalrystorage,spearmanstorage;
//    private ImageView swordman,spearman,archer,cavalry;
//    private Button buyBowButton, buySwordsButton, buySpearsButton, buyHorsesButton;
//
//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference mRef = database.getReference();
//    private String amount,money,sPop,equipment,bows, spears, horses, swords,stArch, stSwordm, stSpearm, stCaval;
//    private Integer archerUnit, swordmenUnit, spearmenUnit, cavalryUnit,archerTotal,swordmenTotal,
//            spearmenTotal, cavalryTotal, numSword, numBow, numSpear, numHorses, numArcher, numSwordmen,
//            numSpearmen, numCavalry, balance;//For decreasing coins
//    private Integer population;//For decreasing population
//    private NumberPicker archerNum, swordmenNum, spearmenNum, cavalryNum;
//    private TextView haveArch, haveSwordm, haveSpearm, haveCavalry;
//
//    private ImageView bows, swords, spears, horses;
//    private boolean b;
//    private Button buyBowButton, buySwordButton, buySpearButton, buyHorseButton;
//    private EditText bowInput, swordInput, spearInput, horseInput;
//
//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference mRef = database.getReference();
//    private DatabaseReference mRef2 = database.getReference();
//    private String money,havswords, havspears, havhorses, havbows;
//    private Integer haveBows;
//    private NumberPicker bowNum,swordNum, spearNum, horseNum;
//    private Integer bowUnit,swordUnit, numBows, numSpears, numSwords, numHorses, spearUnit, horseUnit, bowTotal, swordTotal,
//            spearTotal, horseTotal, balance;
private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference bowstorage;
    private StorageReference swordstorage;
    private StorageReference spearstorage;
    private StorageReference horsestorage;
    private TextView bows, swords, spears,horses;

    private ImageView bow, sword, spear, horse;
    private boolean b;
    private Button buyBowButton, buySwordButton, buySpearButton, buyHorseButton;
    private EditText bowInput, swordInput, spearInput, horseInput;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = database.getReference();
    private DatabaseReference mRef2 = database.getReference();
    private String money;
    private Integer haveBows;
    private NumberPicker bowNum,swordNum, spearNum, horseNum;
    private Integer bowUnit,swordUnit, numBows, numSpears, numSwords, numHorses, spearUnit, horseUnit, bowTotal, swordTotal,
            spearTotal, horseTotal, balance;
    public Frag_Market(){

    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        bowstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/bow.png");
        swordstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/sword2.png");
        spearstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/spear.png");
        horsestorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/horse.png");
        View v = inflater.inflate(R.layout.frag_market, container,false);

        // BUTTONS
        buyBowButton = (Button)v.findViewById(R.id.bowButton);
        buySwordButton = (Button)v.findViewById(R.id.swordButton);
        buySpearButton = (Button)v.findViewById(R.id.spearButton);
        buyHorseButton = (Button)v.findViewById(R.id.horseButton);
        //Text Views
        bows = (TextView)v.findViewById(R.id.haveBows);
        swords = (TextView)v.findViewById(R.id.haveSwords);
        spears = (TextView)v.findViewById(R.id.haveSpears);
        horses = (TextView)v.findViewById(R.id.haveHorses);

        bowNum = (NumberPicker)v.findViewById(R.id.bowpicker);
        bowNum.setMinValue(1);
        bowNum.setMaxValue(25);
        swordNum = (NumberPicker) v.findViewById(R.id.swordpicker);
        swordNum.setMinValue(1);
        swordNum.setMaxValue(25);
        spearNum = (NumberPicker) v.findViewById(R.id.spearpicker);
        spearNum.setMinValue(1);
        spearNum.setMaxValue(25);
        horseNum = (NumberPicker) v.findViewById(R.id.horsepicker);
        horseNum.setMinValue(1);
        horseNum.setMaxValue(25);
        //initialize values
        numBows = 0;
        numSpears = 0;
        numSwords = 0;
        numHorses = 0;

        bow = (ImageView) v.findViewById(R.id.bow);
        Glide.with(this).using(new FirebaseImageLoader()).load(bowstorage).into(bow);
        sword = (ImageView) v.findViewById(R.id.sword);
        Glide.with(this).using(new FirebaseImageLoader()).load(swordstorage).into(sword);
        spear = (ImageView) v.findViewById(R.id.spear);
        Glide.with(this).using(new FirebaseImageLoader()).load(spearstorage).into(spear);
        horse = (ImageView) v.findViewById(R.id.horse);
        Glide.with(this).using(new FirebaseImageLoader()).load(horsestorage).into(horse);

        //initalize values;

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                balance = Integer.parseInt(money);
                haveBows = Integer.parseInt(dataSnapshot.child("Market_weapons").child("Bow").child("amountBoughtCum").getValue().toString());

                bowUnit = bowNum.getValue();
                bowTotal = bowUnit * 15;

                bowNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            bowUnit = bowNum.getValue();
                            bowTotal = bowUnit * 15;
                        }
                    }
                });
                bows.setText(dataSnapshot.child("Market_weapons").child("Bow").child("amountBought").getValue().toString());

                buyBowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (balance == bowTotal || balance > bowTotal) {
                            //Database value change
                            mRef.child("Coins").child("totalCoins").setValue(balance - bowTotal);
                            numBows = numBows + bowUnit;
                            mRef.child("Market_weapons").child("Bow").child("amountBought").setValue(numBows);
                            //bows = Integer.parseInt(dataSnapshot.child("Market_weapons").child("Bow").child("amountBoughtCum").getValue().toString());
                            // ^ commented that out to make it work
                            bows.setText(dataSnapshot.child("Market_weapons").child("Bow").child("amountBoughtCum").getValue().toString());

                            final Toast bowsToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + bowUnit + " bows!", Toast.LENGTH_SHORT);
                            bowsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    bowsToast.cancel();
                                }
                            }, 2000);
                        } else {
                            final Toast bowsToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds, no bows bought!", Toast.LENGTH_SHORT);
                            bowsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    bowsToast.cancel();
                                }
                            }, 2000);
                        }
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
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                balance = Integer.parseInt(money);

                // haveBows = Integer.parseInt(dataSnapshot.child("Market_weapons").child("amountBoughtCum").getValue().toString());
                swordUnit = swordNum.getValue();
                swordTotal = swordUnit * 30;

                swordNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            swordUnit = swordNum.getValue();
                            swordTotal = swordUnit * 30;
                        }
                    }
                });
                swords.setText(dataSnapshot.child("Market_weapons").child("Sword").child("amountBought").getValue().toString());

                buySwordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (balance == swordTotal || balance > swordTotal) {
                            //Database value change
                            mRef.child("Coins").child("totalCoins").setValue(balance - swordTotal);
                            mRef.child("Market_weapons").child("Sword").child("amountBought").setValue(swordUnit);
                            numSwords = numSwords + swordUnit;
                            mRef.child("Market_weapons").child("Sword").child("amountBought").setValue(numSwords);
                            final Toast swordsToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + swordUnit + " swords!", Toast.LENGTH_SHORT);
                            swordsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    swordsToast.cancel();
                                }
                            }, 2000);
                        } else {
                            final Toast swordsToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds, no swords bought!", Toast.LENGTH_SHORT);
                            swordsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    swordsToast.cancel();
                                }
                            }, 2000);
                        }
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
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                balance = Integer.parseInt(money);

                // haveBows = Integer.parseInt(dataSnapshot.child("Market_weapons").child("amountBoughtCum").getValue().toString());
                spearUnit = spearNum.getValue();
                spearTotal = spearUnit * 60;

                spearNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            spearUnit = spearNum.getValue();
                            spearTotal = spearUnit * 60;
                        }
                    }
                });
                spears.setText(dataSnapshot.child("Market_weapons").child("Spear").child("amountBought").getValue().toString());

                buySpearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (balance == spearTotal || balance > spearTotal) {
                            //Database value change
                            mRef.child("Coins").child("totalCoins").setValue(balance - spearTotal);
                            mRef.child("Market_weapons").child("Spear").child("amountBought").setValue(spearUnit);
                            numSpears = numSpears + spearUnit;
                            mRef.child("Market_weapons").child("Spear").child("amountBought").setValue(numSpears);
                            final Toast spearsToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + spearUnit + " spears!", Toast.LENGTH_SHORT);
                            spearsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    spearsToast.cancel();
                                }
                            }, 2000);
                        } else {
                            final Toast spearsToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds, no spears bought!", Toast.LENGTH_SHORT);
                            spearsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    spearsToast.cancel();
                                }
                            }, 2000);

                        }
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
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                balance = Integer.parseInt(money);

                // haveBows = Integer.parseInt(dataSnapshot.child("Market_weapons").child("amountBoughtCum").getValue().toString());
                horseUnit = horseNum.getValue();
                horseTotal = horseUnit * 180;

                horseNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            horseUnit = horseNum.getValue();
                            horseTotal = horseUnit * 180;
                        }
                    }
                });
                horses.setText(dataSnapshot.child("Market_weapons").child("Horse").child("amountBought").getValue().toString());

                buyHorseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (balance == horseTotal || balance > horseTotal) {
                            //Database value change
                            mRef.child("Coins").child("totalCoins").setValue(balance - horseTotal);
                            mRef.child("Market_weapons").child("Horse").child("amountBought").setValue(horseUnit);
                            numHorses = numHorses + horseUnit;
                            mRef.child("Market_weapons").child("Horse").child("amountBought").setValue(numHorses);
                            final Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "You bought " + horseUnit + " horses!", Toast.LENGTH_SHORT);
                            horseToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    horseToast.cancel();
                                }
                            }, 2000);
                        } else {
                            final Toast horseToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds, no horses bought!", Toast.LENGTH_SHORT);
                            horseToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    horseToast.cancel();
                                }
                            }, 2000);
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