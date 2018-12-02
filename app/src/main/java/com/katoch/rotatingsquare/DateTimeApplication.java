package com.katoch.rotatingsquare;

import android.app.Activity;
import android.app.Application;

import com.katoch.rotatingsquare.di.DaggerDateTimeApplicationComponent;
import com.katoch.rotatingsquare.di.DateTimeApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class DateTimeApplication  extends Application implements HasActivityInjector{

    @Inject
    public DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDateTimeApplicationComponent.create()
                .inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
