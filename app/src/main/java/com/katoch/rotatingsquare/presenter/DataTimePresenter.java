package com.katoch.rotatingsquare.presenter;

import android.util.Log;

import com.katoch.rotatingsquare.IDateTimeView;
import com.katoch.rotatingsquare.data.DataTimeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataTimePresenter implements IDataTimePresenter {
    private static final String TAG = "YelpPresenter";

    private DataTimeRepository mRepository;
    private IDateTimeView mActivityView;

    public DataTimePresenter(DataTimeRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attach(IDateTimeView view) {
        mActivityView = view;
    }

    @Override
    public void requestDateTimeInfo() {
        mRepository.getDateTime()
                .subscribeOn(Schedulers.io())
                .repeat()
                .timeInterval(TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(datetime -> {
                    //Log.d(TAG, (datetime).value().getTime());
                    //2018-11-24 04:20:25.672415
                    if (datetime != null && datetime.value().getTime() != null) {
                        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                        Date date = null;
                        try {
                            date = time.parse(datetime.value().getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (date != null ) {
                            String formatedTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                            mActivityView.setDateTime(formatedTime);
                        }

                    }

                }, e -> {
                    Log.d(TAG, e.toString());
                });

    }

    @Override
    public void detach() {
        mRepository.cancelOngoingCommand();
        mRepository = null;
        mActivityView = null;
    }

}
