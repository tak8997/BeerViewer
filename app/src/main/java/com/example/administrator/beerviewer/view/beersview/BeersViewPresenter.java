package com.example.administrator.beerviewer.view.beersview;


import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;

import java.util.List;

import javax.inject.Inject;

public class BeersViewPresenter implements BeersViewContract.Presenter {

    private BeerDataSource beerRepository;
    private BeersViewContract.View view;

    @Inject
    public BeersViewPresenter(BeerDataSource beerRepository, BeersViewContract.View view) {
        this.beerRepository = beerRepository;
        this.view = view;
    }

    @Override
    public void getBeers(Integer pageStart, int perPage) {
        beerRepository.getBeers(pageStart, perPage, new BeerDataSource.LoadBeersCallback() {
            @Override
            public void onTaskLoaded(List<BeerModel> beers) {
                view.showItems(beers);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void getBeersFromBottom(int pageStart, int perPage, final int position) {
        beerRepository.getBeers(pageStart, perPage, new BeerDataSource.LoadBeersCallback() {
            @Override
            public void onTaskLoaded(List<BeerModel> beers) {
                view.showItemsFromBottom(beers, position);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void subscribe() { }

    @Override
    public void unsubscribe() {

    }
}
