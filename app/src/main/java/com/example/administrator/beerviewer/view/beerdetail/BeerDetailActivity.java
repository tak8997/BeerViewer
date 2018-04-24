package com.example.administrator.beerviewer.view.beerdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.model.BeerModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.Disposable;

public class BeerDetailActivity extends DaggerAppCompatActivity
        implements BeerDetailContract.View {

    @BindView(R.id.beer_img) ImageView image;
    @BindView(R.id.beer_title) TextView tvTitle;
    @BindView(R.id.beer_tagline) TextView tvTagline;
    @BindView(R.id.beer_first_brewed) TextView tvFirstBrewed;
    @BindView(R.id.beer_description) TextView tvDescription;
    @BindView(R.id.beer_brewers_tips) TextView tvBrewersTips;
    @BindView(R.id.beer_contributed_by) TextView tvContributedBy;

    @BindView(R.id.app_bar) Toolbar toolbar;
    @BindView(R.id.app_bar_title) TextView tvBarTitle;

    @BindView(R.id.cardview) CardView cardView;

    @Inject
    int beerId;

    @Inject
    BeerDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);

        presenter.subscribe();

        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        cardView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.snazzy));
    }

    @Override
    public void showDetailBeer(BeerModel beer) {
        Glide.with(BeerViewerApplication.getInstance())
                .load(beer.getImageUrl())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(image);
        tvTitle.setText("이름 :  " + beer.getName());
        tvTagline.setText("태그 :  " + beer.getTagline());
        tvDescription.setText(beer.getDescription());
        tvBrewersTips.setText(beer.getBrewersTips());
        tvContributedBy.setText("기여한 사람 :  " + beer.getContributedBy());
        tvFirstBrewed.setText("제조날짜 :  " + beer.getFirstBrewed());
        tvBarTitle.setText(beer.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                presenter.processBeerContent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showShareDialog(String beerInfo) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, beerInfo);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.send_to)));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }
}
