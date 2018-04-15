package com.example.administrator.beerviewer.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.data.source.model.BeerModel;
import com.example.administrator.beerviewer.data.source.model.WishModel;

import java.util.List;



/**
 * Created by Tak on 2018. 1. 27..
 */

@Database(
        entities = {
                BeerModel.class,
                WishModel.class
        },
        version = 2
)
public abstract class BeerDatabase extends RoomDatabase {

    private static final String DB_NAME = "beer_db";
    private static BeerDatabase instance;

    public static BeerDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    BeerViewerApplication.getInstance(),
                    BeerDatabase.class,
                    DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract BeerDao beerDao();

    public void addBeers(List<BeerModel> beers) {
        List<BeerModel> previous = beerDao().getAllBeers();
        List<BeerModel> inserts = beers;

        beerDao().deleteBeers(previous);
        beerDao().insertBeers(inserts);
    }

    public void insertOrUpdateWish(WishModel wish) {
        beerDao().insertWish(wish);
    }
}
















