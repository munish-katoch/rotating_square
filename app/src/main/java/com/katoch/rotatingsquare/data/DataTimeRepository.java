package com.katoch.rotatingsquare.data;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataTimeRepository {
    private static final String TAG = "YelpRepository";
    //ToDo Inject it.
    private WebService mWebservice;
    private OkHttpClient mClient;

    public DataTimeRepository() {
        mClient = getOkHttpClient();
        mWebservice = getRetrofit().create(WebService.class);
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().build();
                return chain.proceed(newRequest);
            }
        }).build();
    }

    public Retrofit getRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                // Need for RxJava adapter.
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Single<DateTime> getDateTime() {
        return mWebservice.getTime();
    }

    public void cancelOngoingCommand() {
        if (mClient != null) {
            mClient.dispatcher().cancelAll();
        }
    }
}
