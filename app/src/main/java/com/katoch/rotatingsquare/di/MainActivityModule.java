package com.katoch.rotatingsquare.di;


import com.katoch.rotatingsquare.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity getMainActivity();
}