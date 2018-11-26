package com.katoch.rotatingsquare.di;

import com.katoch.rotatingsquare.data.DataTimeRepository;
import com.katoch.rotatingsquare.data.WebService;

import dagger.Module;
import dagger.Provides;

@Module (includes = WebModule.class)
public class RepositoryModule {

    @Provides
    public DataTimeRepository getRepository(WebService webService){
        return new DataTimeRepository(webService);
    }
}