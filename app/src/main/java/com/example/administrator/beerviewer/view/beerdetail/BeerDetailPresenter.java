package com.example.administrator.beerviewer.view.beerdetail;

import android.util.Log;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.data.source.model.BeerModel;

import javax.inject.Inject;

public class BeerDetailPresenter implements BeerDetailContract.Presenter {

    private BeerDetailContract.View view;
    private BeerDataSource beerRepository;

    private int beerId;

    @Inject
    public BeerDetailPresenter(BeerDataSource beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void start() {
        getBeer();
    }

    private void getBeer() {
        beerRepository.getBeer(beerId, new BeerDataSource.GetBeerCallback() {
            @Override
            public void onBeerLoaded(BeerModel beer) {
                view.showDetailBeer(beer);

                appendBeerContent(beer);
            }

            @Override
            public void onDataNotAvailable() {
                view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.cannot_load_data));
            }
        });

//        beer = BeerDatabase.getInstance().beerDao().getBeer(beerId);
//        if (beer != null)
//            view.showDetailBeer(beer);
    }

    private String beerInfo;
    public void appendBeerContent(BeerModel beer) {
        beerInfo = beer.getName() +"\n" + beer.getTagline() + "\n" + beer.getDescription() + "\n"
                + beer.getBrewersTips() + "\n" + beer.getContributedBy() + "\n" + beer.getFirstBrewed();
    }

    @Override
    public void processBeerContent() {
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
