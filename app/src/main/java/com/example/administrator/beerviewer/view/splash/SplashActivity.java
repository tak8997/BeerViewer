package com.example.administrator.beerviewer.view.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.view.beerview.BeerViewActivity;

public class SplashActivity extends Activity
        implements SplashContract.View {

    private SplashContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter();
        presenter.takeView(this);

        showSplashAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void showSplashAnimation() {
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
        Intent intent = new Intent(SplashActivity.this, BeerViewActivity.class);
        startActivity(intent);
        finish();
    }
}
