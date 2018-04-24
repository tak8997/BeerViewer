package com.example.administrator.beerviewer.view.beersview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.rx.rxbus.Events;
import com.example.administrator.beerviewer.rx.rxbus.RxEventBus;
import com.example.administrator.beerviewer.view.OnBottomReachedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BeersViewActivity extends DaggerAppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener, OnBottomReachedListener, BeersViewContract.View {

    private static final String TAG = BeersViewActivity.class.getSimpleName();

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.beer_recyler)
    RecyclerView beerRecycler;

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @Inject
    BeersViewContract.Presenter presenter;

    private BeersAdapter adapter;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public int pageStart = 1;
    private int perPage = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        initView();
        onEventBusCalled();

        presenter.takeView(this);
        presenter.getBeers(pageStart++, perPage);
    }

    private void initView() {
        setSupportActionBar(toolbar);

        adapter = new BeersAdapter();
        adapter.setOnBottomReachedListener(this);
        beerRecycler.setLayoutManager(new LinearLayoutManager(this));
        beerRecycler.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(Color.YELLOW, Color.RED, Color.GREEN);
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
    public void showItems(List<BeerModel> beers) {
        adapter.addItems(beers);
    }

    @Override
    public void showItemsFromBottom(final List<BeerModel> beers, final int position) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.addItemsFromBottom(beers, position);
            }
        });
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getBeers(pageStart++, perPage);
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onBottomReached(int position) {
        presenter.getBeersFromBottom(pageStart++, perPage, position);
    }

    private void onEventBusCalled() {
        RxEventBus.getInstance().getBusObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event instanceof Events.PageEvent)
                        pageStart = 1;
                });
    }
}
