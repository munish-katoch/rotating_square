package com.katoch.rotatingsquare.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.katoch.rotatingsquare.data.WebService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebModule {
    @Inject
    @NonNull
    public Retrofit mRetrofit;

    @Provides
    public Retrofit getRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // Need for RxJava adapter.
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public WebService getWebService() {
        return mRetrofit.create(WebService.class);
    }
}

