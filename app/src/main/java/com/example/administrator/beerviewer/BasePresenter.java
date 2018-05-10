package com.example.administrator.beerviewer;

public interface BasePresenter<T> {

    void subscribe();

    void unsubscribe();

    void takeView(T view);

    void dropView();
}
