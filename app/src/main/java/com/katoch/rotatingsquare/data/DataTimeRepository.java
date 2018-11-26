package com.katoch.rotatingsquare.data;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.OkHttpClient;


public class DataTimeRepository {
    private static final String TAG = "YelpRepository";


    private WebService mWebservice;

    @Inject
    public DataTimeRepository (WebService webservice) {
        mWebservice = webservice;
    }

//    @Inject
//    protected OkHttpClient mClient;

    public Single<DateTime> getDateTime() {
        return mWebservice.getTime();
    }

    public void cancelOngoingCommand() {
//        if (mClient != null) {
//            mClient.dispatcher().cancelAll();
//        }
    }
}
