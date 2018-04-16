package com.example.administrator.beerviewer.view.beerdetail;

import com.example.administrator.beerviewer.BasePresenter;
import com.example.administrator.beerviewer.BaseView;
import com.example.administrator.beerviewer.data.model.BeerModel;

public interface BeerDetailContract {

    interface View extends BaseView<Presenter> {

        void showDetailBeer(BeerModel beer);

        void showShareDialog(String beerInfo);

        void showFailureMessage(String msg);
    }

    interface Presenter extends BasePresenter<View> {

        void processBeerContent();
    }

}
