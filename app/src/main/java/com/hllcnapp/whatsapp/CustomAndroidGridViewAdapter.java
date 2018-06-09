package com.hllcnapp.whatsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 5/11/2016.
 */

public class CustomAndroidGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Category> categories;

    public CustomAndroidGridViewAdapter(Context c, ArrayList<Category> categories ) {
        mContext = c;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int p) {
        return categories.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(int p, final View convertView, ViewGroup parent) {

        View grid;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = inflater.inflate(R.layout.gridview_custom_layout, parent, false);
            TextView textView = grid.findViewById(R.id.gridview_text);
            ImageView imageView = grid.findViewById(R.id.gridview_image);
            textView.setText(categories.get(p).getName());
            imageView.setBackground(categories.get(p).getDrawable());

        } else {

            grid = (View) convertView;

        }


        return grid;
    }
}