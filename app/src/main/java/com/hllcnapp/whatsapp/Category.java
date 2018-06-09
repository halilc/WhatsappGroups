package com.hllcnapp.whatsapp;

import android.graphics.drawable.Drawable;

public class Category {

    private String name;
    private Drawable drawable;

    public Category(String name, Drawable drawable){

        this.name = name;
        this.drawable = drawable;

    }

    public String getName() {
        return name;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
