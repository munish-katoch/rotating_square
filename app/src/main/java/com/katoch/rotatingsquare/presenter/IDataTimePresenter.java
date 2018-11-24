package com.katoch.rotatingsquare.presenter;

import com.katoch.rotatingsquare.IDateTimeView;

public interface IDataTimePresenter {
    void attach(IDateTimeView view);
    void requestDateTimeInfo();
    void detach();
}
