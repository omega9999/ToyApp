package com.example.android.toyapp;

import android.app.Activity;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import com.example.android.toyapp.activity.AsynkTaskCursorActivity;
import com.example.android.toyapp.activity.CustomRadioButtonActivity;
import com.example.android.toyapp.activity.ExplicitIntentActivity;
import com.example.android.toyapp.activity.HydrationReminderActivity;
import com.example.android.toyapp.activity.ImplicitIntentActivity;
import com.example.android.toyapp.activity.LifecycleActivity;
import com.example.android.toyapp.activity.TestHandlerActivity;
import com.example.android.toyapp.activity.ToDoListActivity;
import com.example.android.toyapp.activity.UrlWebActivity;
import com.example.android.toyapp.activity.recyclerview.RecyclerViewLayoutActivity;
import com.example.android.toyapp.activity.visualizerpreferences.VisualizerActivity;

import java.util.ArrayList;
import java.util.List;

public class ExerciseUtils {

    @CheckResult
    @NonNull
    public static List<Class<? extends Activity>> getIntents(){
        final List<Class<? extends Activity>> activities = new ArrayList<>();
        activities.add(CustomRadioButtonActivity.class);
        activities.add(TestHandlerActivity.class);
        activities.add(ExplicitIntentActivity.class);
        activities.add(RecyclerViewLayoutActivity.class);
        activities.add(UrlWebActivity.class);
        activities.add(ImplicitIntentActivity.class);
        activities.add(LifecycleActivity.class);
        activities.add(VisualizerActivity.class);
        activities.add(AsynkTaskCursorActivity.class);
        activities.add(ToDoListActivity.class);
        activities.add(HydrationReminderActivity.class);
        return activities;
    }

}
