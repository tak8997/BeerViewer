package com.example.administrator.beerviewer.view.beersview;

import com.example.administrator.beerviewer.BasePresenter;
import com.example.administrator.beerviewer.BaseView;
import com.example.administrator.beerviewer.data.model.BeerModel;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

public interface BeersViewContract {

    interface View extends BaseView<Presenter> {

        void showItems(List<BeerModel> beers);

        void showItemsFromBottom(List<BeerModel> beers);

        void setPageStart();
    }

    interface Presenter extends BasePresenter<View> {

        void getBeers(int pageStart, int perPage, SwipyRefreshLayoutDirection direction);

        void getBeersFromBottom(int pageStart, int perPage, int position);

        void processDirection(int pageStart, int perPage, SwipyRefreshLayoutDirection direction);

        void unsubscribeEventBus();
    }

}
