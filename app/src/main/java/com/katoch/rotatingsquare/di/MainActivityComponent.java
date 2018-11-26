package com.katoch.rotatingsquare.di;

import com.katoch.rotatingsquare.MainActivity;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@MainActivityScope
@Component(modules = {AndroidInjectionModule.class,MainActivityModule.class,WebModule.class,RepositoryModule.class,PresenterModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    void inject(MainActivity activity);
}
