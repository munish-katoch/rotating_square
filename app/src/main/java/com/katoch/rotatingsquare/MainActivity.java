package com.katoch.rotatingsquare;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.time).setOnTouchListener(this);
        rotate(findViewById(R.id.time));

        findViewById(R.id.main_area).setOnDragListener(this);
        findViewById(R.id.drawable_area).setOnDragListener(this);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:

                break;
            case DragEvent.ACTION_DRAG_EXITED:

                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                Toast.makeText(view.getContext(),"Drop", Toast.LENGTH_LONG).show();
                View v = (View) dragEvent.getLocalState();

                if (view.getId() == R.id.main_area || view.getId() == R.id.drawable_area) {

                    ViewGroup source = (ViewGroup) v.getParent();
                    source.removeView(v);

                    LinearLayout target = (LinearLayout) view;
                    target.addView(v);
                }
                //make view visible as we set visibility to invisible while starting drag
                v.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:

            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            //view.clearAnimation();
            return true;
        } else {
            return false;
        }
    }

    public void rotate(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise_animation);
        view.startAnimation(animation);
    }
}
