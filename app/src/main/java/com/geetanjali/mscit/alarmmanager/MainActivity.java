package com.geetanjali.mscit.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb1,rb2;
    Button btn1,btn2;

    private void casting()
    {
        rg = findViewById(R.id.rgp);

        rb1 = findViewById(R.id.rbtn1);
        rb2 = findViewById(R.id.rbtn2);

        btn1 = findViewById(R.id.btnOneTime);
        btn2 = findViewById(R.id.btnRepear);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casting();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked())
                    setAlarm(true,false);
                else
                    setAlarm(false,false);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked())
                    setAlarm(true,true);
                else
                    setAlarm(false,true);

            }
        });
    }

    private void setAlarm(boolean isOneTime, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent;

        if(!isOneTime)
        {
            intent  = new Intent(MainActivity.this,AlarmToastReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        }
        else
        {
            intent  = new Intent(MainActivity.this,AlarmNotificationReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        }
        if(!isRepeat)
        {
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,pendingIntent);
        }
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,3000,pendingIntent);

    }
}
