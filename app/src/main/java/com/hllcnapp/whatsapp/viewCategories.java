package com.hllcnapp.whatsapp;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
public class viewCategories extends AppCompatActivity {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    ArrayList<Category> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcategories);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.grid);

        initInstances();

        mItems.add(new Category("All", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Buy & Sell", getResources().getDrawable(R.drawable.buy)));
        mItems.add(new Category("Animal & Pets", getResources().getDrawable(R.drawable.animal)));
        mItems.add(new Category("Art & Photography", getResources().getDrawable(R.drawable.art)));
        mItems.add(new Category("Business", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Community", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Fan Clubs", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Food", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Funny", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Games", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Dating & Love", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Health & Fitness", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Politics & News", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Relationships", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("School & Education", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Science & Tech", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Sports", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Travel & Places", getResources().getDrawable(R.drawable.all)));
        mItems.add(new Category("Medicals", getResources().getDrawable(R.drawable.all)));

        gridView.setAdapter(new CustomAndroidGridViewAdapter(this, mItems));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                new MaterialDialog.Builder(viewCategories.this)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                         Log.e("CLİCKED",text.toString());
                            }
                        })
                        .show();

            }
        });
    }
    private void initInstances() {
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        collapsingToolbarLayoutAndroid.setTitle("All Categories");
    }

}
