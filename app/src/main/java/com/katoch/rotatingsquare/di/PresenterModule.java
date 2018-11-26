package com.katoch.rotatingsquare.di;

import com.katoch.rotatingsquare.data.DataTimeRepository;
import com.katoch.rotatingsquare.presenter.DataTimePresenter;
import com.katoch.rotatingsquare.presenter.IDataTimePresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module (includes = RepositoryModule.class)
public class PresenterModule {
    @Provides
    public IDataTimePresenter getPresenter(DataTimeRepository repository) {
        return new DataTimePresenter(repository);
    }


}