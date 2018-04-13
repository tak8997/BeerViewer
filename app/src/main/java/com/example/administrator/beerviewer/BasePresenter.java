package com.example.administrator.beerviewer;

public interface BasePresenter<T> {

    void takeView(T View);

    void dropView();

    void start();
}
