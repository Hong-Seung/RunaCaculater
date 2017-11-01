package com.hseunghyun.runacaculater.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junsuk on 2017. 9. 8..
 */

public class RunUtil {
    private final RunaApi mApiService;

    public RunUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RunaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(RunaApi.class);
    }

    public RunaApi getApiService() {
        return mApiService;
    }
}
