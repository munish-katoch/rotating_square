package com.katoch.rotatingsquare.di;

import com.katoch.rotatingsquare.DateTimeApplication;
import com.katoch.rotatingsquare.MainActivity;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;

@Component (modules = {AndroidInjectionModule.class,MainActivityModule.class,WebModule.class,RepositoryModule.class,PresenterModule.class})
public interface DateTimeApplicationComponent {
    void inject(DateTimeApplication dateTimeApplication);
    void inject(MainActivity activity);
}
