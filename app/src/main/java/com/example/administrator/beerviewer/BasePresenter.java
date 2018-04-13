package com.example.administrator.beerviewer;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

    void start();
}
