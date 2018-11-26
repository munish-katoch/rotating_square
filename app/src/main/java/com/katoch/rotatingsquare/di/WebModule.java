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
//    @Inject
//    public Retrofit mRetrofit;
//
//    @Inject
//    public OkHttpClient mClient;

    @Provides
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().build();
                return chain.proceed(newRequest);
            }
        }).build();
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient client) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                // Need for RxJava adapter.
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public WebService getWebService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }
}

