package com.example.administrator.beerviewer.view.beerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.beerviewer.R;

import dagger.android.support.DaggerAppCompatActivity;

public class BeerViewActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
