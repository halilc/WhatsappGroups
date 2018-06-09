package com.hllcnapp.whatsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private ImageButton viewCategories;
    private ImageButton addGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a);
        videoview.setVideoURI(uri);
        videoview.start();
        viewCategories = (ImageButton)  findViewById(R.id.viewCategories);
        addGroup = (ImageButton)  findViewById(R.id.addGroup);
        addGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "addgorup", Toast.LENGTH_LONG).show();

            }
        });
        viewCategories.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, viewCategories.class);
                MainActivity.this.startActivity(myIntent);

            }
        });
    }

}


