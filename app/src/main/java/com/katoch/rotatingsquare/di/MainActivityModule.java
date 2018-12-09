package com.katoch.rotatingsquare.di;


import com.katoch.rotatingsquare.MainActivity;

import javax.inject.Scope;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivitySubComponent.class)
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeActivity();
}