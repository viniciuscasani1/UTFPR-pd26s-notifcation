package com.sartainstudios.notificationtutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getExtras().getString("extra");
        Intent serviceIntent = new Intent(context, NotificationAlarm.class);
        serviceIntent.putExtra("extra", state);

        context.startService(serviceIntent);
    }

}
