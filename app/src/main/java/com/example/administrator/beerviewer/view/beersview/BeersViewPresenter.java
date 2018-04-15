package com.example.administrator.beerviewer.view.beersview;

import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;

import java.util.List;

import javax.inject.Inject;

public class BeersViewPresenter implements BeersViewContract.Presenter {

    private BeersViewContract.View view;
    private BeerDataSource beerRepository;

    @Inject
    public BeersViewPresenter(BeerDataSource beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void start() { }

    @Override
    public void getBeers(int pageStart, int pageEnd) {
        beerRepository.getBeers(pageStart, pageEnd, new BeerDataSource.LoadBeersCallback() {
            @Override
            public void onTaskLoaded(List<BeerModel> beers) {
                view.showItems(beers);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

//        List<BeerModel> beers
//                = BeerDatabase.getInstance().getBeerDao().getBeers(pageStart, pageEnd);
//
//        view.showItems(beers);
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
