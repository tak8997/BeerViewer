package com.example.administrator.beerviewer.view.beersview;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.source.local.BeerDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class BeersViewActivity extends DaggerAppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener, BeersViewContract.View {

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.beer_recyler)
    RecyclerView beerRecycler;

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    private BeersViewContract.Presenter presenter;

    private BeersAdapter adapter;

    private int pageStart = 1;
    private int pageEnd = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        initView();

        presenter = new BeersViewPresenter();
        presenter.takeView(this);
        presenter.getBeers(pageStart, pageEnd);
    }

    private void initView() {
        setSupportActionBar(toolbar);

        adapter = new BeersAdapter();
        beerRecycler.setLayoutManager(new LinearLayoutManager(this));
        beerRecycler.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(Color.YELLOW, Color.RED, Color.GREEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void addItems(List<BeerModel> beers) {
        adapter.addItems(beers);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getBeers(pageStart += 10, pageEnd += 10);
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private void addItem() {
        List<BeerModel> beers
                = BeerDatabase.getInstance().getBeerDao().getBeers(pageStart += 10, pageEnd += 10);
        adapter.addItems(beers);

        for (BeerModel beer : beers)
            Log.d("beer_item", beer.getId()+"");

        refreshLayout.setRefreshing(false);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());
}
