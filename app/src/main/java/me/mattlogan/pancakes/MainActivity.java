package me.mattlogan.pancakes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import me.mattlogan.library.ViewStack;
import me.mattlogan.library.ViewStackDelegate;

public class MainActivity extends AppCompatActivity
        implements ViewStackActivity, ViewStackDelegate {

    private static final String STACK_TAG = "stack";

    private ViewStack viewStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewStack = ViewStack.create((ViewGroup) findViewById(R.id.container), this);

        if (savedInstanceState != null) {
            viewStack.rebuildFromBundle(savedInstanceState, STACK_TAG);
        } else {
            viewStack.push(new RedView.Factory());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        viewStack.saveToBundle(outState, STACK_TAG);
        super.onSaveInstanceState(outState);
        Log.d("testing", "MainActivity onSaveInstanceState bundle:" + outState);
    }

    @Override
    public void onBackPressed() {
        viewStack.pop();
    }

    @Override
    public ViewStack viewStack() {
        return viewStack;
    }

    @Override
    public void finishStack() {
        Log.d("testing", "finishStack");
        finish();
    }
}
