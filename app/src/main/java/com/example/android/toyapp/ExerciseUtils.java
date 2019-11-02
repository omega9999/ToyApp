package com.example.android.toyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import com.example.android.toyapp.activity.ExplicitIntentActivity;
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

        return activities;
    }

}
