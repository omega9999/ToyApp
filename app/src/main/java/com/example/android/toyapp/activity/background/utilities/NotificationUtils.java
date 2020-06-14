package com.example.android.toyapp.activity.background.utilities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.android.toyapp.activity.HydrationReminderActivity;
import com.example.android.toyapp.activity.background.sync.WaterReminderIntentService;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    // TODO (7) Create a method called remindUserBecauseCharging which takes a Context.
    // This method will create a notification for charging. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html
    // TODO (8) Get the NotificationManager using context.getSystemService
    // TODO (9) Create a notification channel for Android O devices
    // TODO (10) In the remindUserBecauseCharging method use NotificationCompat.Builder to create a notification
    // that:
    // - has a color of R.color.colorPrimary - use ContextCompat.getColor to get a compatible color
    // - has ic_drink_notification as the small icon
    // - uses icon returned by the largeIcon helper method as the large icon
    // - sets the title to the charging_reminder_notification_title String resource
    // - sets the text to the charging_reminder_notification_body String resource
    // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
    // - sets the notification defaults to vibrate
    // - uses the content intent returned by the contentIntent helper method for the contentIntent
    // - automatically cancels the notification when the notification is clicked
    // TODO (11) If the build version is greater than or equal to JELLY_BEAN and less than OREO,
    // set the notification's priority to PRIORITY_HIGH.
    // TODO (12) Trigger the notification by calling notify on the NotificationManager.
    // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()


    private static  final int PENDING_INTENT_ID = 1;
    public static PendingIntent contentIntent(@NonNull final Context context){

        final Intent intent = new Intent(context, HydrationReminderActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, PENDING_INTENT_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }



    // TODO (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
    // TODO (5) Get a Resources object from the context.
    // TODO (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
    // resources object and R.drawable.ic_local_drink_black_24px


}