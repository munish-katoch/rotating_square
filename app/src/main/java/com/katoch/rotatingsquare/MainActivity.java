package com.katoch.rotatingsquare;

import android.app.Activity;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.katoch.rotatingsquare.presenter.IDataTimePresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, IDateTimeView {

    private static final String TAG = "MainActivity";

    @Inject
    protected IDataTimePresenter mPresenter;

    @Inject
    public DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.time).setOnTouchListener(this);
        rotate(findViewById(R.id.time));

        findViewById(R.id.main_area).setOnDragListener(this);
        findViewById(R.id.drawable_area).setOnDragListener(this);

        mPresenter.attach(this);
        mPresenter.requestDateTimeInfo();
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View v = (View) dragEvent.getLocalState();
                if (view.getId() == R.id.drawable_area) {

                    ViewGroup source = (ViewGroup) v.getParent();
                    source.removeView(v);

                    LinearLayout target = (LinearLayout) view;
                    rotate(v);
                    target.addView(v);
                    v.setVisibility(View.VISIBLE);
                }  else {
                    findViewById(R.id.time).setVisibility(View.VISIBLE);
                    rotate(findViewById(R.id.time));
                    return false;
                }

                //Toast.makeText(view.getContext(), "Drop", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void setDateTime(String time) {
        ((TextView) findViewById(R.id.time)).setText(time);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = (View.DragShadowBuilder) new View.DragShadowBuilder(view);

            view.startDrag(data, shadowBuilder, view, 0);

            view.setVisibility(View.INVISIBLE);
            view.clearAnimation();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    private void rotate(View view) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise_animation);
        view.startAnimation(animation);
    }

}
