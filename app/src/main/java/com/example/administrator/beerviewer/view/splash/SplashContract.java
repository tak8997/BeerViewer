package com.example.administrator.beerviewer.view.splash;

import com.example.administrator.beerviewer.BasePresenter;
import com.example.administrator.beerviewer.BaseView;

public interface SplashContract {

    interface View extends BaseView<Presenter> {

        void startBeerViewActivity();

        void showFailureMessage(String msg);

        void showSplashAnimation();
    }

    interface Presenter extends BasePresenter<View> {

    }

}
