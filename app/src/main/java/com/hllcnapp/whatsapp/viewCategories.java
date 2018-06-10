package com.hllcnapp.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewCategories extends AppCompatActivity implements BillingProcessor.IBillingHandler{

    Toolbar toolbar;
    GridView gridView;
    ArrayList<Category> mItems = new ArrayList<>();
    private FirebaseFirestore db;
    private ProgressBar progressBar;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private BillingProcessor bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcategories);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.grid);
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        adView = findViewById(R.id.banner);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjdPvq48grVr26RXuUMbU+VAIVgqa8bfHXFMuxsGEI6Dl+S8econBH7BidikxfEFsmYQR3PDoOTzyb0L1e7xCIN9ue+WyZYA5KsYrJhiq3XI3NcB41f6uPTlROgDaHiC6m25K12N3o0X8ftq3crNamiiEM4JyXFceJjR/bQ0V75BgWwSSp/OyiZ+G5PCh6geRaAilghlTZB0s5o87DL2gctfoQfSG1685f6o887VfAgB9CU0pPvpNYsOb400SakidHa4TVq8ATIADEFX6Pw/N/rJFX/izJA4Z8VBYIvqD3ssOf01VK07vGGvTiB7gb/sHUV+Nid1sd35JdNz3kNwIQwIDAQAB" , this);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();

            }
        });

        mItems.add(new Category("All", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Buy & Sell", getResources().getDrawable(R.drawable.buy)));
        mItems.add(new Category("VIP", getResources().getDrawable(R.drawable.vip)));
        mItems.add(new Category("Animal & Pets", getResources().getDrawable(R.drawable.animal)));
        mItems.add(new Category("Art & Photography", getResources().getDrawable(R.drawable.art)));
        mItems.add(new Category("Business", getResources().getDrawable(R.drawable.business)));
        mItems.add(new Category("Community", getResources().getDrawable(R.drawable.community)));
        mItems.add(new Category("Fan Clubs", getResources().getDrawable(R.drawable.fun)));
        mItems.add(new Category("Food", getResources().getDrawable(R.drawable.diet)));
        mItems.add(new Category("Funny", getResources().getDrawable(R.drawable.amusement)));
        mItems.add(new Category("Games", getResources().getDrawable(R.drawable.battle)));
        mItems.add(new Category("Dating & Love", getResources().getDrawable(R.drawable.calendar)));
        mItems.add(new Category("Health & Fitness", getResources().getDrawable(R.drawable.muscle)));
        mItems.add(new Category("Politics & News", getResources().getDrawable(R.drawable.capitol)));
        mItems.add(new Category("Relationships", getResources().getDrawable(R.drawable.relationship)));
        mItems.add(new Category("School & Education", getResources().getDrawable(R.drawable.classroom)));
        mItems.add(new Category("Science & Tech", getResources().getDrawable(R.drawable.biology)));
        mItems.add(new Category("Sports", getResources().getDrawable(R.drawable.basketball)));
        mItems.add(new Category("Travel & Places", getResources().getDrawable(R.drawable.travel)));

        gridView.setAdapter(new CustomAndroidGridViewAdapter(this, mItems));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                if(mItems.get(position).getName().equals("VIP")){

                    bp.purchase(viewCategories.this, "com.hllcnapp.whatsapp.vip");



                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    final ArrayList<Group> groups = new ArrayList<>();

                    db.collection(mItems.get(position).getName()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()) {

                                for(int i=0; i < task.getResult().size(); i++){

                                    DocumentSnapshot snapshot = task.getResult().getDocuments().get(i);

                                    groups.add(new Group(snapshot.getString("name"), snapshot.getString("link"), snapshot.getString("type")));

                                }

                            }

                            progressBar.setVisibility(View.GONE);

                            AlertDialog.Builder dialog = new AlertDialog.Builder(viewCategories.this);

                            View view = getLayoutInflater().inflate(R.layout.dialog_view, parent, false);
                            dialog.setView(view);
                            ListView listView = view.findViewById(R.id.groupsList);
                            ListViewAdapter adapter = new ListViewAdapter(viewCategories.this, groups);
                            listView.setAdapter(adapter);

                            dialog.setTitle(mItems.get(position).getName());
                            dialog.show();

                        }
                    });
                }



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

        progressBar.setVisibility(View.VISIBLE);

        final ArrayList<Group> groups = new ArrayList<>();

        db.collection("VIP").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()) {

                    for(int i=0; i < task.getResult().size(); i++){

                        DocumentSnapshot snapshot = task.getResult().getDocuments().get(i);

                        groups.add(new Group(snapshot.getString("name"), snapshot.getString("link"), snapshot.getString("type")));

                    }

                }

                progressBar.setVisibility(View.GONE);

                AlertDialog.Builder dialog = new AlertDialog.Builder(viewCategories.this);

                View view = getLayoutInflater().inflate(R.layout.dialog_view, null);
                dialog.setView(view);
                ListView listView = view.findViewById(R.id.groupsList);
                ListViewAdapter adapter = new ListViewAdapter(viewCategories.this, groups);
                listView.setAdapter(adapter);

                dialog.setTitle("VIP");
                dialog.show();

            }
        });

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
}
