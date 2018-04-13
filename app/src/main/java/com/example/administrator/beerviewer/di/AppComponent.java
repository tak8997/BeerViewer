package com.example.administrator.beerviewer.di;


import android.app.Application;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.di.module.BeerApiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class,
        BeerApiModule.class})
public interface AppComponent extends AndroidInjector<BeerViewerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

//    void inject(BeerViewerApplication application);
}
