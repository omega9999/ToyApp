package com.example.android.toyapp.activity.background.sync;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.android.toyapp.activity.background.utilities.PreferenceUtilities;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "ACTION_INCREMENT_WATER_COUNT";

    public static void executeTask(@NonNull final Context context, @NonNull final String action){
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)){
            incrementWaterCount(context);
        }
    }

    private static void incrementWaterCount(@NonNull final Context context){
        PreferenceUtilities.incrementWaterCount(context);
    }

    private static final String TAG = ReminderTasks.class.getSimpleName();
}
