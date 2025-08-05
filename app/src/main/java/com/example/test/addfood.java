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

public class addfood extends AppCompatActivity {
    private ArrayList<PendingIntent> array = new ArrayList<PendingIntent>();
    public ArrayList<int[]> getTime() {
        return time;
    }
    ArrayList<Integer> food = new ArrayList<>();
    private ArrayList<int[]> time = new ArrayList<int[]>();
    private int pendingnum = 0;
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfood);
//        createNotificationCannel();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffe474"));
        actionBar.setBackgroundDrawable(colorDrawable);

        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        int pid = bundle.getInt("pid");
        int alarm_num = bundle.getInt("alarm_num");
        String[] result = bundle.getStringArray("result");
        food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");

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
        Intent intent = new Intent(this, foodbroadcast.class);
        intent.putExtras(bundle1);
        Button addButton = findViewById(R.id.button1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(addfood.this, time.size(), intent, 0);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                取消鬧鐘
//                if(alarmManager!= null) {
//                    alarmManager.cancel(pendingIntent);
//                }

//                限制鬧鐘次數
                if(alarm_num>=5){
                    Toast.makeText(addfood.this, "Sorry 最多只能設定5個鬧鐘", Toast.LENGTH_LONG).show();
                }
                else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND,0);
                    if(hour>12){
                        if(minute<10){
                            Toast.makeText(addfood.this, "提醒設定成功!!\n下午 "+(hour-12)+" : 0"+minute, Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(addfood.this, "提醒設定成功!!\n下午 "+(hour-12)+" : "+minute, Toast.LENGTH_LONG).show();
                        }
                    }else {
                        if (minute < 10) {
                            Toast.makeText(addfood.this, "提醒設定成功!!\n上午 " + hour + " : 0" + minute, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(addfood.this, "提醒設定成功!!\n上午 " + hour + " : " + minute, Toast.LENGTH_LONG).show();
                        }
                    }
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                    int[] theTime = {hour,minute};
                    time.add(theTime);
//                    array.add(pendingIntent);
                    final boolean[] check = {true};
                    new Thread(new Runnable(){
                        @Override
                        public void run(){
                            TimeSql con = new TimeSql();
                            con.run();
                            con.setAlarm("food",pid,hour,minute);
                            check[0] = false;
                        }
                    }
                    ).start();
                    food.add(hour);
                    food.add(minute);
                    food.add(0);
                }
                Intent tent1 = new Intent();
                bundle1.putIntegerArrayList("food",food);
                tent1.putExtras(bundle1);
                tent1.setClass(addfood.this,food.class);
                startActivity(tent1);
            }
        });
    }
    public  ArrayList<int[]> getAlarm(){
        return time;
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