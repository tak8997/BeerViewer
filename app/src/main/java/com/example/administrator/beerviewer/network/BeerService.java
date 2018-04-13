package com.example.administrator.beerviewer.network;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tak on 2018. 1. 27..
 */

public interface BeerService {

    @GET("beers/")
    Call<List<BeerModel>> getBeers();

//    @GET("beers/{beer_id}")
//    Call<List<BeerModel>> getBeer(@Path("beer_id") int beerId);

}
