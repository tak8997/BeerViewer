package com.example.administrator.beerviewer.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.WishModel;

import java.util.List;


/**
 * Created by Tak on 2018. 1. 27..
 */

@Dao
public interface BeerDao {

    @Query("SELECT * FROM beer")
    List<BeerModel> getAllBeers();

    @Query("SELECT * FROM beer WHERE id >= :pageStart AND id <= :pageEnd")
    List<BeerModel> getBeers(int pageStart, int pageEnd);

    @Delete
    void deleteBeers(List<BeerModel> deletes);

    @Insert
    void insertBeers(List<BeerModel> inserts);

    @Query("SELECT * FROM beer WHERE id = :beerId")
    BeerModel getBeer(int beerId);


    @Query("SELECT * FROM wish WHERE id = :beerId")
    WishModel getWish(int beerId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWish(WishModel wish);

}
