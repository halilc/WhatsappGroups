package com.hllcnapp.whatsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton viewCategoriesBtn;
    private ImageButton addGroupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewCategoriesBtn = findViewById(R.id.viewCategories);
        addGroupBtn = findViewById(R.id.addGroup);
        addGroupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, addGroup.class);
                startActivity(intent);

            }
        });
        viewCategoriesBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, viewCategories.class);
                MainActivity.this.startActivity(myIntent);


            }
        });
    }

}


