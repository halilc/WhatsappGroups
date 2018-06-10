package com.hllcnapp.whatsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewCategories extends AppCompatActivity {

    Toolbar toolbar;
    GridView gridView;
    ArrayList<Category> mItems = new ArrayList<>();
    private FirebaseFirestore db;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcategories);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.grid);
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


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
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                progressBar.setVisibility(View.VISIBLE);

                final ArrayList<Group> groups = new ArrayList<>();

                db.collection(mItems.get(position).getName()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()) {

                            for(int i=0; i < task.getResult().size(); i++){

                                DocumentSnapshot snapshot = task.getResult().getDocuments().get(i);

                                groups.add(new Group(snapshot.getString("name"), snapshot.getString("url"), snapshot.getString("type")));

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
        });

    }

}
