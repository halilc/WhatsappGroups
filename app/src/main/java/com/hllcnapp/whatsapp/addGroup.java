package com.hllcnapp.whatsapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addGroup extends AppCompatActivity {

    private EditText name, link;
    private Spinner categories;
    private Button save;
    private FirebaseFirestore db;
    private String[] spinnerCategory = {"Categories","All", "Buy&Sell", "Animals&Pets", "Art&Photography", "Business", "Community",
                                        "Fan Clubs", "Food", "Funny", "Games", "Dating&Love", "Health&Fitness",
                                        "Politics&News", "Relationships", "School&Education", "Science&Tech", "Sports", "Travel&Places"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgroup);

        name = findViewById(R.id.groupName);
        link = findViewById(R.id.groupLink);
        categories = findViewById(R.id.groupSpinner);
        save = findViewById(R.id.saveGroup);
        db = FirebaseFirestore.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_spinner, spinnerCategory);
        categories.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!categories.getSelectedItem().toString().equals("Categories")) {
                    if (!name.getText().toString().equals("")) {
                        if (!link.getText().toString().equals("")) {
                            saveLink(categories.getSelectedItem().toString(), name.getText().toString(), link.getText().toString());
                        } else {
                            Toast.makeText(addGroup.this, "Link can't be empty!", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(addGroup.this, "Name can't be empty!", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(addGroup.this, "Category not selected", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void saveLink(String category, String name, String link){

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("link", link);

        db.collection(category).document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(addGroup.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addGroup.this, "Something went wrong :S", Toast.LENGTH_SHORT).show();
            }
        });

    }

}


