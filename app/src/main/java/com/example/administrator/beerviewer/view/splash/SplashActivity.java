package com.example.administrator.beerviewer.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.di.ActivityScope;
import com.example.administrator.beerviewer.view.beersview.BeersViewActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@ActivityScope
public class SplashActivity extends DaggerAppCompatActivity
        implements SplashContract.View {

    @Inject
    public SplashContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void showSplashAnimation() {
        final ImageView imgSplash = findViewById(R.id.beer_animation);
        Glide.with(this)
                .asGif()
                .load(R.raw.beer_splash)
                .into(imgSplash);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startBeerViewActivity() {
        Intent intent = new Intent(SplashActivity.this, BeersViewActivity.class);
        startActivity(intent);
        finish();
    }
}
