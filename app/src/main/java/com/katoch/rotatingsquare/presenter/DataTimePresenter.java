package com.katoch.rotatingsquare.presenter;

import android.util.Log;

import com.katoch.rotatingsquare.IDateTimeView;
import com.katoch.rotatingsquare.data.DataTimeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataTimePresenter implements IDataTimePresenter {
    private static final String TAG = "YelpPresenter";

    private DataTimeRepository mRepository;
    private IDateTimeView mActivityView;
    private Disposable mDisposable;

    @Inject
    public DataTimePresenter(DataTimeRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attach(IDateTimeView view) {
        mActivityView = view;
    }

    @Override
    public void requestDateTimeInfo() {
        mDisposable = mRepository.getDateTime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .repeat()
            .timeInterval(TimeUnit.SECONDS)
            .map(datetime-> {
                String formatedTime = null;
                if (datetime != null && datetime.value().getTime() != null) {
                    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                    Date date = null;
                    try {
                        date = time.parse(datetime.value().getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (date != null ) {
                        formatedTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                        mActivityView.setDateTime(formatedTime);
                    }

                }

                return formatedTime;
            })
            .subscribe(formatedTime -> {
                //Log.d(TAG, (datetime).value().getTime());
                //2018-11-24 04:20:25.672415
                if (formatedTime != null ) {
                    mActivityView.setDateTime(formatedTime);
                }

            }, e -> {
                Log.d(TAG, e.toString());
            });

    }

    @Override
    public void detach() {
        mRepository.cancelOngoingCommand();
        mDisposable.dispose();
        mRepository = null;
        mActivityView = null;
    }

}
