package com.example.android.toyapp.activity.background.sync;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class WaterReminderIntentService extends IntentService {
    public WaterReminderIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (action != null) {
                ReminderTasks.executeTask(this, action);
            }
        }
    }

    private static final String TAG = WaterReminderIntentService.class.getSimpleName();
}
