package com.example.android.toyapp;

import android.app.Activity;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import com.example.android.toyapp.activity.ExplicitIntentActivity;
import com.example.android.toyapp.activity.ImplicitIntentActivity;
import com.example.android.toyapp.activity.LifecycleActivity;
import com.example.android.toyapp.activity.UrlWebActivity;
import com.example.android.toyapp.activity.recyclerview.RecyclerViewLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class ExerciseUtils {

    @CheckResult
    @NonNull
    public static List<Class<? extends Activity>> getIntents(){
        final List<Class<? extends Activity>> activities = new ArrayList<>();
        activities.add(ExplicitIntentActivity.class);
        activities.add(RecyclerViewLayoutActivity.class);
        activities.add(UrlWebActivity.class);
        activities.add(ImplicitIntentActivity.class);
        activities.add(LifecycleActivity.class);

        return activities;
    }

}
