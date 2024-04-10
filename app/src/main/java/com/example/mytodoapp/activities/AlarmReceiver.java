package com.example.mytodoapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm Wake up", Toast.LENGTH_SHORT).show();
        Log.d("AlarmReceiver","Alarm when off");
    }
}
