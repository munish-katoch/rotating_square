package com.katoch.rotatingsquare.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WebService {
    public static final String BASE_URL = "https://dateandtimeasjson.appspot.com";

    @GET(".")
    Single<DateTime> getTime();

}
