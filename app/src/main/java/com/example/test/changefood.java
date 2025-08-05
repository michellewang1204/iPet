package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class changefood extends AppCompatActivity {

    Bundle bundle= new Bundle();
    Bundle bundle1 = new Bundle();
    ArrayList<Integer> food = new ArrayList<>();
    ArrayList<int[]> time = new ArrayList<int[]>();
    int pid;
    public ArrayList<int[]> getTime() {
        return time;
    }

    private int count = 0;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changefood);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffe474"));
        actionBar.setBackgroundDrawable(colorDrawable);


        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        int no_alarm = bundle.getInt("no_alarm");
        int oh = bundle.getInt("hour");
        int om = bundle.getInt("minute");
        int tocheck = bundle.getInt("check");

        bundle1.putString("userid",userid);
        bundle1.putInt("pid",pid);
        bundle1.putStringArray("result",result);
        bundle1.putIntegerArrayList("food",food);
        bundle1.putIntegerArrayList("medicine",medicine);
        bundle1.putIntegerArrayList("poop",poop);
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");
        bundle1.putStringArrayList("deworm",deworm);
        bundle1.putStringArrayList("vaccine",vaccine);

        //建立notification物件
        intent.putExtras(bundle1);
        Button addButton = findViewById(R.id.button1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimeSql con = new TimeSql();
                        con.run();
                        con.updateAlarm("food",pid,oh,om,hour,minute,tocheck);
                    }
                }
                ).start();

                food.set((no_alarm*3),hour);
                food.set((no_alarm*3+1),minute);

                Intent tent1 = new Intent();
                bundle1.putIntegerArrayList("food",food);
                tent1.putExtras(bundle1);
                tent1.setClass(changefood.this,food.class);
                startActivity(tent1);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(this,food.class);
            i.putExtras(bundle1);
            startActivity(i);
        }
        return super.onKeyDown(keycode,event);
    }
}