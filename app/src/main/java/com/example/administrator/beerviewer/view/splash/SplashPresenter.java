package com.example.administrator.beerviewer.view.splash;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.db.BeerDatabase;
import com.example.administrator.beerviewer.network.BeerService;
import com.example.administrator.beerviewer.network.BeerViewerClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;



    @Override
    public void start() {
        loadBeers();
    }

    private void loadBeers() {
        BeerService beerService = BeerViewerClient.createService(BeerService.class);
        beerService.getBeers().enqueue(new Callback<List<BeerModel>>() {
            @Override
            public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
                if (response.isSuccessful()) {

                    List<BeerModel> beers = response.body();
                    Log.d("SplashActivity", "beer_size : " + beers.size() + "");
                    if(beers != null) {
                        BeerDatabase.getInstance().addBeers(beers);
                        view.startBeerViewActivity();
                    } else
                        view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.no_data));
                }
            }

            @Override
            public void onFailure(Call<List<BeerModel>> call, Throwable t) {
                view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.data_load_fail));
                t.printStackTrace();
            }
        });
    }

    @Override
    public void takeView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }
}
