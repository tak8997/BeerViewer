package com.example.administrator.beerviewer.view.beerdetail;

import com.example.administrator.beerviewer.data.source.local.BeerDatabase;
import com.example.administrator.beerviewer.data.source.model.BeerModel;

public class BeerDetailPresenter implements BeerDetailContract.Presenter {

    private BeerDetailContract.View view;

    private BeerModel beer;
    private int beerId;

    public BeerDetailPresenter() {

    }

    @Override
    public void start() {
        getBeer();
    }

    private void getBeer() {
        beer = BeerDatabase.getInstance().beerDao().getBeer(beerId);
        if (beer != null)
            view.showDetailBeer(beer);
    }

    @Override
    public void appendBeerContent() {
        String beerInfo = beer.getName() +"\n" + beer.getTagline() + "\n" + beer.getDescription() + "\n"
                + beer.getBrewersTips() + "\n" + beer.getContributedBy() + "\n" +beer.getFirstBrewed();

        view.showShareDialog(beerInfo);
    }

    @Override
    public void takeView(BeerDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }
}
