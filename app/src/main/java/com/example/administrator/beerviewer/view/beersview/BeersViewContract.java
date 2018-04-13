package com.example.administrator.beerviewer.view.beersview;

import com.example.administrator.beerviewer.BasePresenter;
import com.example.administrator.beerviewer.BaseView;
import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

public interface BeersViewContract {

    interface View extends BaseView<Presenter> {

        void addItems(List<BeerModel> beers);
    }

    interface Presenter extends BasePresenter<View> {

        void getBeers(int pageStart, int pageEnd);
    }

}
