package com.example.administrator.beerviewer.network;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;
import java.util.Observable;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tak on 2018. 1. 27..
 */

public interface BeerApiService {

    @GET("beers/")
    Single<List<BeerModel>> getBeers();

//    @GET("beers/{beer_id}")
//    Call<List<BeerModel>> getBeer(@Path("beer_id") int beerId);

}
