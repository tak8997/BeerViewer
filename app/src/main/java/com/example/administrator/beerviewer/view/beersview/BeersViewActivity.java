package com.example.administrator.beerviewer.view.beersview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.model.BeerModel;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class BeersViewActivity extends DaggerAppCompatActivity
        implements BeersViewContract.View, SwipyRefreshLayout.OnRefreshListener {

    private static final String TAG = BeersViewActivity.class.getSimpleName();

    @BindView(R.id.refreshlayout)
    com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout refreshLayout;

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

        presenter.takeView(this);
        presenter.getBeers(pageStart++, perPage, SwipyRefreshLayoutDirection.TOP);
    }

    private void initView() {
        setSupportActionBar(toolbar);

        adapter = new BeersAdapter();
//        adapter.setOnBottomReachedListener(this);
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
        presenter.unsubscribeEventBus();
    }

    @Override
    public void showItems(List<BeerModel> beers) {
        adapter.addItems(beers);
    }

    @Override
    public void showItemsFromBottom(final List<BeerModel> beers) {
        adapter.addItemsFromBottom(beers);
    }

//    @Override
//    public void onRefresh() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                presenter.getBeers(pageStart++, perPage);
//                refreshLayout.setRefreshing(false);
//            }
//        }, 1000);
//    }
//
//    @Override
//    public void onBottomReached(int position) {
//        presenter.getBeersFromBottom(pageStart++, perPage, position);
//    }

    @Override
    public void setPageStart() {
        pageStart = 1;
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.processDirection(pageStart++, perPage, direction);
                refreshLayout.setRefreshing(false);
            }
        }, 1000);

//        presenter.getBeers(pageStart++, perPage);
    }
}
