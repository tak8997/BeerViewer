package com.example.administrator.beerviewer.view.beersview;

import com.example.administrator.beerviewer.BasePresenter;
import com.example.administrator.beerviewer.BaseView;
import com.example.administrator.beerviewer.data.source.model.BeerModel;

import java.util.List;

public interface BeersViewContract {

    interface View extends BaseView<Presenter> {

        void showItems(List<BeerModel> beers);

        void showItemsFromBottom(List<BeerModel> beers, int position);
    }

    interface Presenter extends BasePresenter<View> {

        void getBeers(int pageStart, int perPage);

        void getBeersFromBottom(int pageStart, int perPage, int position);
    }

}
