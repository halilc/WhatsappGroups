package com.hllcnapp.whatsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Group> groups;

    public ListViewAdapter(Activity activity, ArrayList<Group> groups){

        this.activity = activity;
        this.groups = groups;

    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Group getItem(int i) {
        return groups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View v, ViewGroup viewGroup) {

        View view = activity.getLayoutInflater().inflate(R.layout.listview_item, viewGroup, false);

        TextView text = view.findViewById(R.id.text);
        text.setText(groups.get(i).getGroupName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(groups.get(i).getGroupUrl()));
                activity.startActivity(browserIntent);
            }
        });

        return view;

    }
}
