package com.sharepunjabishayari;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList;
    myadapter mDataAdapter;
    public FloatingActionButton floatingActionButton;
    private InterstitialAd mInterstitialAd;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       Floating Button Vireyables Start
        floatingActionButton = findViewById(R.id.fab_action1);
        rvList = findViewById(R.id.reshow);
        rvList.setLayoutManager(new LinearLayoutManager(this));
//      Floting Button Vireyables End

//      Banner Ads Start
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//      Banner Ads End

//       Floating Button Start
        floatingActionButton = findViewById(R.id.fab_action1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddShayariAC.class);
                startActivity(intent);
            }

        });
//         Floating Button End

//         Firebase Retriv Data Start
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), Model.class)
                        .build();
        mDataAdapter = new myadapter(options, this);
        rvList.setAdapter(mDataAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDataAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDataAdapter.stopListening();
    }
//         Firebase Retriv Data End


//            No Intenet Dialog Box Start
    public AlertDialog.Builder buidDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please Turn ON Mobile Data or wifi");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        return builder;
//      No Intenet Dialog Box End

//     Back Exit Button Start
    }
    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Do You Want To Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
//       Interstitial Ads Time Loader Start
        prepaperAD();
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "Interstital not Loaded");
                        }
                        prepaperAD();
                    }
                });
            }
        }, 60, 60, TimeUnit.SECONDS);

//      Interstitial Ads Time Loader End

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

 //      Back Exit Button End

 //      Interstitial Ads Banner Start
    }
    private void prepaperAD() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

//     Interstitial Ads Banner End

 //      No Internet Connected All Vireyables Start
    }
    public boolean isconnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return (mobile != null && mobile.isConnectedOrConnecting()) || wifi != null && wifi.isConnectedOrConnecting();
        } else {
            return false;
        }
 //     No Internet Connected All Vireyables End

 //     No Internet Connected All Vireyables Start ( Problom Is This Is On Crete Vireyables )

        //       if (!isconnected(MainActivity.this)) buidDialog(MainActivity.this).show();
        //        userList = new ArrayList<>();
        //        rvList = findViewById(R.id.reshow);
        //        rvList.setHasFixedSize(true);
        //        rvList.setLayoutManager(new LinearLayoutManager(this));

//     No Internet Connected All Vireyables End ( Problom Is This Is On Crete Vireyables )

    }
}