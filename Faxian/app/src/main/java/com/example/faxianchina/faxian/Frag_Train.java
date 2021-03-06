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

public class Frag_Train extends Fragment {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mapstorage;
    private StorageReference swordmstorage, archerstorage, cavalrystorage, spearmanstorage;
    private ImageView swordman, spearman, archer, cavalry;
    private Button buyArcherButton, buySwordsmenButton, buySpearmenButton, buyCavalryButton;
    private EditText archerInput, swordsmenInput, spearmenInput, cavalryInput;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = database.getReference();
    private String amount, money, sPop, equipment, bows, spears, horses, swords, stArch, stSwordm, stSpearm, stCaval;
    private Integer archerUnit, swordmenUnit, spearmenUnit, cavalryUnit, archerTotal, swordmenTotal,
            spearmenTotal, cavalryTotal, numSword, numBow, numSpear, numHorses, numArcher, numSwordmen,
            numSpearmen, numCavalry;//For decreasing coins
    private Integer units, total, perUnit, balance;//For decreasing coins
    private Integer population;//For decreasing population
    private NumberPicker archerNum, swordmenNum, spearmenNum, cavalryNum;
    private TextView haveArch, haveSwordm, haveSpearm, haveCavalry;


    public Frag_Train() {

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        archerstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/archer.png");
        swordmstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/swordman.png");
        spearmanstorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/spearman.png");
        cavalrystorage = storage.getReferenceFromUrl("gs://faxian-china.appspot.com/cavalry.png");
        View v = inflater.inflate(R.layout.frag__train, container, false);

        // BUTTONS
        buyArcherButton = (Button) v.findViewById(R.id.archersButton);
        buySwordsmenButton = (Button) v.findViewById(R.id.swordsmenButton);
        buySpearmenButton = (Button) v.findViewById(R.id.spearmenButton);
        buyCavalryButton = (Button) v.findViewById(R.id.cavalryButton);
        //Text Views
        haveArch = (TextView) v.findViewById(R.id.haveArchers);
        haveSwordm = (TextView) v.findViewById(R.id.haveSwordmen);
        haveSpearm = (TextView) v.findViewById(R.id.haveSpearmen);
        haveCavalry = (TextView) v.findViewById(R.id.haveCavalry);


        //Number Pickers
        swordmenNum = (NumberPicker) v.findViewById(R.id.swordmenpicker);
        swordmenNum.setMinValue(1);
        swordmenNum.setMaxValue(25);
        archerNum = (NumberPicker) v.findViewById(R.id.archerpicker);
        archerNum.setMinValue(1);
        archerNum.setMaxValue(25);
        spearmenNum = (NumberPicker) v.findViewById(R.id.spearmenpicker);
        spearmenNum.setMinValue(1);
        spearmenNum.setMaxValue(25);
        cavalryNum = (NumberPicker) v.findViewById(R.id.cavalrypicker);
        cavalryNum.setMinValue(1);
        cavalryNum.setMaxValue(25);

        numArcher = 0;
        numSwordmen = 0;
        numSpearmen = 0;
        numCavalry = 0;
        numBow = 0;
        numSpear = 0;
        numSword = 0;
        numHorses = 0;

        archer = (ImageView) v.findViewById(R.id.archers);
        Glide.with(this).using(new FirebaseImageLoader()).load(archerstorage).into(archer);
        swordman = (ImageView) v.findViewById(R.id.swordman);
        Glide.with(this).using(new FirebaseImageLoader()).load(swordmstorage).into(swordman);
        spearman = (ImageView) v.findViewById(R.id.spearman);
        Glide.with(this).using(new FirebaseImageLoader()).load(spearmanstorage).into(spearman);
        cavalry = (ImageView) v.findViewById(R.id.cavalry);
        Glide.with(this).using(new FirebaseImageLoader()).load(cavalrystorage).into(cavalry);

        //Archers
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stArch = dataSnapshot.child("Market_soldiers").child("Archer").
                        child("amountBoughtCum").getValue().toString();
                haveArch.setText(stArch);
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                balance = Integer.parseInt(money);
                // haveBows = Integer.parseInt(dataSnapshot.child("Market_weapons")
                // .child("amountBoughtCum").getValue().toString());
                sPop = dataSnapshot.child("population").child("currPopulation").getValue().toString();
                population = Integer.parseInt(sPop);
                numArcher = Integer.parseInt(stArch);
                bows = dataSnapshot.child("Market_weapons").child("Bow").child("amountBought")
                        .getValue().toString();
                numBow = Integer.parseInt(bows);
                archerUnit = archerNum.getValue();
                archerTotal = archerUnit * 15;

                archerNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            archerUnit = archerNum.getValue();
                            archerTotal = archerUnit * 15;
                        }
                    }
                });
                buyArcherButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if ((balance == archerTotal || balance > archerTotal) &&
                                (population >= archerUnit) && numBow >= archerUnit) {
                            //Database value change
                            mRef.child("Coins").child("totalCoins").setValue(balance - archerTotal);
                            numArcher = numArcher + archerUnit;
                            mRef.child("Market_weapons").child("Bow").child("amountBought").
                                    setValue(numBow - archerUnit);
                            mRef.child("population").child("currPopulation").setValue(population - archerUnit);
                            mRef.child("Market_soldiers").child("Archer").child("amountBoughtCum").setValue(numArcher);
                            final Toast bowsToast = Toast.makeText(v.getContext().getApplicationContext(),
                                    "You trained " + archerUnit + " archers!", Toast.LENGTH_SHORT);
                            bowsToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    bowsToast.cancel();
                                }
                            }, 2000);

                        } else {
                            final Toast bowsToast = Toast.makeText(v.getContext().getApplicationContext(),
                                    "Insufficient funds/population/equipment!", Toast.LENGTH_SHORT);
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
        //Swordmen
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stSwordm = dataSnapshot.child("Market_soldiers").child("Swordsmen").
                        child("amountBoughtCum").getValue().toString();
                haveSwordm.setText(stSwordm);
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();
                balance = Integer.parseInt(money);
                sPop = dataSnapshot.child("population").child("currPopulation").getValue().toString();
                population = Integer.parseInt(sPop);
                swords = dataSnapshot.child("Market_weapons").child("Sword").child("amountBought").getValue().toString();
                numSword = Integer.parseInt(swords);
                numSwordmen = Integer.parseInt(stSwordm);
                swordmenUnit = swordmenNum.getValue();
                swordmenTotal = swordmenUnit * 30;

                swordmenNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            swordmenUnit = swordmenNum.getValue();
                            swordmenTotal = swordmenUnit * 30;
                        }
                    }
                });
                buySwordsmenButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if ((balance >= swordmenTotal) && (population >= swordmenUnit) && numBow >= swordmenUnit) {
                            mRef.child("Coins").child("totalCoins").setValue(balance - swordmenTotal);
                            numSwordmen = numSwordmen + swordmenUnit;
                            mRef.child("Market_weapons").child("Sword").child("amountBought").
                                    setValue(numSword - swordmenUnit);
                            mRef.child("population").child("currPopulation").setValue(population - swordmenUnit);
                            mRef.child("Market_soldiers").child("Swordsmen").child("amountBoughtCum").setValue(numArcher);
                            final Toast swordToast = Toast.makeText(v.getContext().getApplicationContext(),
                                    "You trained " + swordmenUnit + " swordsmen!", Toast.LENGTH_SHORT);
                            swordToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    swordToast.cancel();
                                }
                            }, 2000);

                        } else {
                            final Toast swordToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds/population/equipment!", Toast.LENGTH_SHORT);
                            swordToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    swordToast.cancel();
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
        //Spearmen
        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                stSpearm = dataSnapshot.child("Market_soldiers").child("Spearmen").
                        child("amountBoughtCum").getValue().toString();
                haveSpearm.setText(stSpearm);
                numSpearmen = Integer.parseInt(stSpearm);
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();//Retrieves the number of coins
                balance = Integer.parseInt(money);

                sPop = dataSnapshot.child("population").child("currPopulation").getValue().toString();//Retreives original population
                //get value from number picker
                spearmenUnit = spearmenNum.getValue();
                spearmenTotal = spearmenUnit * 60;
                population = Integer.parseInt(sPop);//changes population string into a number
                spears = dataSnapshot.child("Market_weapons").child("Spear").child("amountBought").getValue().toString();
                numSpear = Integer.parseInt(spears);

                swordmenNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            spearmenUnit = spearmenNum.getValue();
                            spearmenTotal = spearmenUnit * 60;
                        }
                    }
                });
                buySpearmenButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //**MONEY**//

                        if ((balance >= spearmenTotal) && (population >= spearmenUnit) && (numSpear >= spearmenUnit)) {
                            mRef.child("Coins").child("totalCoins").setValue(balance - spearmenTotal);
                            numSpearmen = numSpearmen + spearmenUnit;
                            mRef.child("Market_weapons").child("Spear").child("amountBought").
                                    setValue(numSpear - spearmenUnit);
                            mRef.child("population").child("currPopulation").setValue(population - spearmenUnit);
                            mRef.child("Market_soldiers").child("Spearmen").child("amountBoughtCum").setValue(numSpearmen);
                            final Toast spearToast = Toast.makeText(v.getContext().getApplicationContext(),
                                    "You trained " + spearmenUnit + " spearmen!", Toast.LENGTH_SHORT);
                            spearToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    spearToast.cancel();
                                }
                            }, 2000);
                        } else {
                            Toast spearmenToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds/population/equipment!", Toast.LENGTH_SHORT);
                            spearmenToast.show();
                        }


                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Cavalry
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                stCaval = dataSnapshot.child("Market_soldiers").child("Cavalry").
                        child("amountBoughtCum").getValue().toString();
                haveCavalry.setText(stCaval);
                numCavalry = Integer.parseInt(stCaval);
                money = dataSnapshot.child("Coins").child("totalCoins").getValue().toString();//Retrieves the number of coins
                balance = Integer.parseInt(money);

                sPop = dataSnapshot.child("population").child("currPopulation").getValue().toString();//Retreives original population
                //get value from number picker
                cavalryUnit = cavalryNum.getValue();
                cavalryTotal = cavalryUnit * 180;
                population = Integer.parseInt(sPop);//changes population string into a number
                horses = dataSnapshot.child("Market_weapons").child("Horse").child("amountBought").getValue().toString();
                numHorses = Integer.parseInt(horses);

                cavalryNum.setOnScrollListener(new NumberPicker.OnScrollListener() {

                    @Override
                    public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            cavalryUnit = cavalryNum.getValue();
                            cavalryTotal = cavalryUnit * 180;
                        }
                    }
                });
                buyCavalryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if ((balance == cavalryTotal || balance > cavalryTotal) && (population == cavalryUnit || population > cavalryUnit) && (numHorses >= cavalryUnit)) {
                            mRef.child("Coins").child("totalCoins").setValue(balance - cavalryTotal);
                            numCavalry = numCavalry + cavalryUnit;
                            mRef.child("Market_weapons").child("Horse").child("amountBought").
                                    setValue(numHorses - cavalryUnit);
                            mRef.child("population").child("currPopulation").setValue(population - cavalryUnit);
                            mRef.child("Market_soldiers").child("Cavalry").child("amountBoughtCum").setValue(numCavalry);
                            final Toast cavalryToast = Toast.makeText(v.getContext().getApplicationContext(),
                                    "You trained " + cavalryUnit + " horsemen!", Toast.LENGTH_SHORT);
                            cavalryToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cavalryToast.cancel();
                                }
                            }, 2000);
                        } else {
                            final Toast cavalryToast = Toast.makeText(v.getContext().getApplicationContext(), "Insufficient funds/population/equipment!", Toast.LENGTH_SHORT);
                            cavalryToast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cavalryToast.cancel();
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