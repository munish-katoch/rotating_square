package com.katoch.rotatingsquare.di;

import android.app.Activity;

import com.katoch.rotatingsquare.MainActivity;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Subcomponent
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}