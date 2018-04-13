package com.example.administrator.beerviewer.view.beersview;

import android.util.Log;

import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.source.local.BeerDatabase;

import java.util.List;

public class BeersViewPresenter implements BeersViewContract.Presenter {

    private BeersViewContract.View view;

    public BeersViewPresenter() {

    }

    @Override
    public void start() { }

    @Override
    public void getBeers(int pageStart, int pageEnd) {
        List<BeerModel> beers
                = BeerDatabase.getInstance().getBeerDao().getBeers(pageStart, pageEnd);

        for (BeerModel beer : beers)
            Log.d("beer_item", beer.getId()+"");

        view.addItems(beers);
    }

    @Override
    public void takeView(BeersViewContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
