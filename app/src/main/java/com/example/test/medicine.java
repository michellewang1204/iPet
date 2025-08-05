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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class medicine extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    ArrayList<Integer> medicine = new ArrayList<>();
    ArrayList<int[]> time = new ArrayList<int[]>();
    Button[] buttonlist;
    int pid;
    ImageButton[] dlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine);
        createNotificationCannel();
        getSupportActionBar().hide();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffe474"));
        actionBar.setBackgroundDrawable(colorDrawable);
        final TextView text;

        text = (TextView) findViewById(R.id.info);
        Button alarm1 = (Button) findViewById(R.id.alarm1);
        Button alarm2 = (Button) findViewById(R.id.alarm2);
        Button alarm3 = (Button) findViewById(R.id.alarm3);
        Button alarm4 = (Button) findViewById(R.id.alarm4);
        Button alarm5 = (Button) findViewById(R.id.alarm5);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        Switch switch3 = (Switch) findViewById(R.id.switch3);
        Switch switch4 = (Switch) findViewById(R.id.switch4);
        Switch switch5 = (Switch) findViewById(R.id.switch5);
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
        LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
        LinearLayout layout5 = (LinearLayout) findViewById(R.id.layout5);
        buttonlist = new Button[]{alarm1, alarm2, alarm3, alarm4, alarm5};
        Switch[] switchlist = {switch1, switch2, switch3, switch4, switch5};
        LinearLayout[] layoutlist = {layout1, layout2, layout3, layout4, layout5};
        ImageButton d1 = (ImageButton) findViewById(R.id.delete1);
        ImageButton d2 = (ImageButton) findViewById(R.id.delete2);
        ImageButton d3 = (ImageButton) findViewById(R.id.delete3);
        ImageButton d4 = (ImageButton) findViewById(R.id.delete4);
        ImageButton d5 = (ImageButton) findViewById(R.id.delete5);
        dlist = new ImageButton[]{d1, d2, d3, d4, d5};
        showAlarm(buttonlist, switchlist, layoutlist, text);

        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");

        bundle1.putString("userid", userid);
        bundle1.putInt("pid", pid);
        bundle1.putStringArray("result", result);
        bundle1.putIntegerArrayList("food", food);
        bundle1.putIntegerArrayList("medicine", medicine);
        bundle1.putIntegerArrayList("poop", poop);
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");
        bundle1.putStringArrayList("deworm", deworm);
        bundle1.putStringArrayList("vaccine", vaccine);

//        Arraylist型態轉換
        for (int n = 0; n < (medicine.size() / 3); n++) {
            int[] alarm = new int[3];
            for (int i = 0; i < 3; i++) {
                alarm[i] = medicine.get(n * 3 + i);
            }
            time.add(alarm);
        }
        bundle1.putInt("alarm_num", time.size());
        showAlarm(buttonlist, switchlist, layoutlist, text);
        setAlarmTime();
        //        setButtonsListener
        for (Button buts : buttonlist) {
            buts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.alarm1:
                            Intent intent1 = new Intent(medicine.this, changemedicine.class);
                            bundle1.putInt("hour", time.get(0)[0]);
                            bundle1.putInt("minute", time.get(0)[1]);
                            bundle1.putInt("check", time.get(0)[2]);
                            bundle1.putInt("no_alarm", 0);
                            intent1.putExtras(bundle1);
                            startActivity(intent1);
                            break;
                        case R.id.alarm2:
                            Intent intent2 = new Intent(medicine.this, changemedicine.class);
                            bundle1.putInt("hour", time.get(1)[0]);
                            bundle1.putInt("minute", time.get(1)[1]);
                            bundle1.putInt("check", time.get(1)[2]);
                            bundle1.putInt("no_alarm", 1);
                            intent2.putExtras(bundle1);
                            startActivity(intent2);
                            break;
                        case R.id.alarm3:
                            Intent intent3 = new Intent(medicine.this, changemedicine.class);
                            bundle1.putInt("hour", time.get(2)[0]);
                            bundle1.putInt("minute", time.get(2)[1]);
                            bundle1.putInt("check", time.get(2)[2]);
                            bundle1.putInt("no_alarm", 2);
                            intent3.putExtras(bundle1);
                            startActivity(intent3);
                            break;
                        case R.id.alarm4:
                            Intent intent4 = new Intent(medicine.this, changemedicine.class);
                            bundle1.putInt("hour", time.get(3)[0]);
                            bundle1.putInt("minute", time.get(3)[1]);
                            bundle1.putInt("check", time.get(3)[2]);
                            bundle1.putInt("no_alarm", 3);
                            intent4.putExtras(bundle1);
                            startActivity(intent4);
                            break;
                        case R.id.alarm5:
                            Intent intent5 = new Intent(medicine.this, changemedicine.class);
                            bundle1.putInt("hour", time.get(4)[0]);
                            bundle1.putInt("minute", time.get(4)[1]);
                            bundle1.putInt("check", time.get(4)[2]);
                            bundle1.putInt("no_alarm", 4);
                            intent5.putExtras(bundle1);
                            startActivity(intent5);
                            break;
                    }
                }
            });
        }
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(medicine.this, health.class);
                i.putExtras(bundle1);
                startActivity(i);
            }
        });
        ImageButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time.size() >= 5) {
                    Toast.makeText(medicine.this, "Sorry 最多只能設定5個鬧鐘", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtras(bundle1);
                    intent.setClass(medicine.this, addmedicine.class);
                    startActivity(intent);
                }
            }
        });

        for (ImageButton d : dlist) {
            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String deleteTime = null;
                    int alarm_code = 0;
                    int num;
                    switch (view.getId()) {
                        case R.id.delete1:
                            time.remove(0);
                            deleteTime = String.valueOf(medicine.get(0));
                            medicine.remove(0);
                            deleteTime = deleteTime+":"+String.valueOf(medicine.get(0));
                            medicine.remove(0);
                            deleteTime = deleteTime+":00";
                            medicine.remove(0);
                            alarm_code = 0;
                            break;
                        case R.id.delete2:
                            num = 3;
                            time.remove(1);
                            deleteTime = String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":"+String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":00";
                            medicine.remove(num);
                            alarm_code = 1;
                            break;
                        case R.id.delete3:
                            num = 6;
                            time.remove(2);
                            deleteTime = String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":"+String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":00";
                            medicine.remove(num);
                            alarm_code = 2;
                            break;
                        case R.id.delete4:
                            num = 9;
                            time.remove(3);
                            deleteTime = String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":"+String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":00";
                            medicine.remove(num);
                            alarm_code = 3;
                            break;
                        case R.id.delete5:
                            num = 12;
                            time.remove(4);
                            deleteTime = String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":"+String.valueOf(medicine.get(num));
                            medicine.remove(num);
                            deleteTime = deleteTime+":00";
                            medicine.remove(num);
                            alarm_code = 4;
                            break;
                    }
                    bundle1.putIntegerArrayList("medicine", medicine);
                    showAlarm(buttonlist, switchlist, layoutlist, text);
                    Intent intent = new Intent(medicine.this, medicinebroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(medicine.this, alarm_code, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    String finalDeleteTime = deleteTime;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            TimeSql con = new TimeSql();
                            con.run();
                            con.deleteAlarm("medicine",pid, finalDeleteTime);
                        }
                    }).start();
                }
            });

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.addfood:
                intent.putExtras(bundle1);
                intent.setClass(this, addmedicine.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNotificationCannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LRC";
            String description = "CLR";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    public void setAlarm(ArrayList<int[]> time) {
        Intent intent = new Intent(this, medicinebroadcast.class);
        if (time.size() != 0) {
            for (int n = 0; n < time.size(); n++) {
                if (time.get(n)[2] == 0) {
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(medicine.this, n, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, time.get(n)[0]);
                    calendar.set(Calendar.MINUTE, time.get(n)[1]);
                    calendar.set(Calendar.SECOND, 0);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(this, health.class);
            i.putExtras(bundle1);
            startActivity(i);
        }
        return super.onKeyDown(keycode, event);
    }

    public void showAlarm(Button[] buttonlist, Switch[] switchlist, LinearLayout[] layoutlist, TextView text) {
        //      設定在不同鬧鐘數量時的改動
        System.out.println("timesize" + time.size());
        if (time.size() == 0) {
            for (Button buts : buttonlist) {
                buts.setVisibility(View.INVISIBLE);
            }
            for (Switch switchs : switchlist) {
                switchs.setVisibility(View.INVISIBLE);
            }
            for (LinearLayout layouts : layoutlist) {
                layouts.setVisibility(View.INVISIBLE);
                layouts.setEnabled(false);
            }
            text.setText("還沒有設定任何提醒喔");
        } else if (time.size() == 5) {
            for (int i = 0; i < 5; i++) {
                if (time.get(i)[2] == 1) {
                    switchlist[i].setChecked(false);
                }else{
                    switchlist[i].setChecked(true);
                }
                buttonlist[i].setVisibility(View.VISIBLE);
                switchlist[i].setVisibility(View.VISIBLE);
                layoutlist[i].setVisibility(View.VISIBLE);
                if (time.get(i)[0] > 12) {
                    if (time.get(i)[1] < 10) {
                        buttonlist[i].setText("  下午  " + (time.get(i)[0] - 12) + " : 0" + time.get(i)[1]);
                    } else {
                        buttonlist[i].setText("  下午  " + (time.get(i)[0] - 12) + " : " + time.get(i)[1]);
                    }
                } else {
                    if (time.get(i)[1] < 10) {
                        buttonlist[i].setText("  上午  " + (time.get(i)[0]) + " : 0" + time.get(i)[1]);
                    } else {
                        buttonlist[i].setText("  上午  " + (time.get(i)[0]) + " : " + time.get(i)[1]);
                    }
                }
            }
            text.setText("已設定的鬧鐘");
        } else {
            int num = time.size();
            for (int i = num; i < 5; i++) {
                buttonlist[i].setVisibility(View.INVISIBLE);
                switchlist[i].setVisibility(View.INVISIBLE);
                layoutlist[i].setVisibility(View.INVISIBLE);
                layoutlist[i].setEnabled(false);
            }
            for (int n = 0; n < num; n++) {
                if (time.get(n)[2] == 1) {
                    switchlist[n].setChecked(false);
                }else{
                    switchlist[n].setChecked(true);
                }
                buttonlist[n].setVisibility(View.VISIBLE);
                switchlist[n].setVisibility(View.VISIBLE);
                layoutlist[n].setVisibility(View.VISIBLE);
                if (time.get(n)[0] > 12) {
                    if (time.get(n)[1] < 10) {
                        buttonlist[n].setText("  下午  " + (time.get(n)[0] - 12) + " : 0" + time.get(n)[1]);
                    } else {
                        buttonlist[n].setText("  下午  " + (time.get(n)[0] - 12) + " : " + time.get(n)[1]);
                    }
                } else {
                    if (time.get(n)[1] < 10) {
                        buttonlist[n].setText("  上午  " + (time.get(n)[0]) + " : 0" + time.get(n)[1]);
                    } else {
                        buttonlist[n].setText("  上午  " + (time.get(n)[0]) + " : " + time.get(n)[1]);
                    }
                }
            }
            text.setText("已設定的鬧鐘");
        }
        int num = 0;
        for (Switch sw : switchlist) {
            if (sw.isChecked()) {
                buttonlist[num].setTextColor(Color.parseColor("#26395F"));
                buttonlist[num].setCompoundDrawablesWithIntrinsicBounds(R.drawable.alarm_clock, 0, 0, 0);
                dlist[num].setImageResource(R.drawable.trash);
            } else {
                buttonlist[num].setTextColor(Color.parseColor("#787166"));
                buttonlist[num].setCompoundDrawablesWithIntrinsicBounds(R.drawable.alarm_clock1, 0, 0, 0);
                dlist[num].setImageResource(R.drawable.trash1);
            }
            sw.setOnCheckedChangeListener(this);
            num = num + 1;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        final int[] switch_no = {-1};
        final boolean[] check = {true};
        switch (compoundButton.getId()) {
            case R.id.switch1:
                switch_no[0] = 0;
                break;
            case R.id.switch2:
                switch_no[0] = 1;
                break;
            case R.id.switch3:
                switch_no[0] = 2;
                break;
            case R.id.switch4:
                switch_no[0] = 3;
                break;
            case R.id.switch5:
                switch_no[0] = 4;
                break;
        }

        if (isChecked) {
            buttonlist[switch_no[0]].setTextColor(Color.parseColor("#26395F"));
            buttonlist[switch_no[0]].setCompoundDrawablesWithIntrinsicBounds(R.drawable.alarm_clock, 0, 0, 0);
            dlist[switch_no[0]].setImageResource(R.drawable.trash);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TimeSql con = new TimeSql();
                    con.run();
                    con.updateAlarm("medicine", pid, time.get(switch_no[0])[0], time.get(switch_no[0])[1],
                            time.get(switch_no[0])[0], time.get(switch_no[0])[1], 0);
                    check[0] = false;
                }
            }
            ).start();
            medicine.set(switch_no[0] * 3 + 2, 0);
            bundle1.putIntegerArrayList("medicine", medicine);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(this, medicinebroadcast.class);
            PendingIntent sender = PendingIntent.getBroadcast(this, switch_no[0], intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            long now = calendar.getTimeInMillis();
            calendar.set(Calendar.HOUR_OF_DAY, time.get(switch_no[0])[0]);
            calendar.set(Calendar.MINUTE, time.get(switch_no[0])[1]);
            calendar.set(Calendar.SECOND, 0);
            if (now > calendar.getTimeInMillis()) {
                calendar.add(calendar.DATE, 1);
            }
            System.out.println("check time: " + calendar.getTime());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
            }
        } else {
            buttonlist[switch_no[0]].setTextColor(Color.parseColor("#787166"));
            buttonlist[switch_no[0]].setCompoundDrawablesWithIntrinsicBounds(R.drawable.alarm_clock1, 0, 0, 0);
            dlist[switch_no[0]].setImageResource(R.drawable.trash1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TimeSql con = new TimeSql();
                    con.run();
                    con.updateAlarm("medicine", pid, time.get(switch_no[0])[0], time.get(switch_no[0])[1],
                            time.get(switch_no[0])[0], time.get(switch_no[0])[1], 1);
                    check[0] = false;
                }
            }
            ).start();
            medicine.set(switch_no[0] * 3 + 2, 1);
            bundle1.putIntegerArrayList("medicine", medicine);
            Intent intent = new Intent(this, medicinebroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(medicine.this, switch_no[0], intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }

    public void setAlarmTime() {
        int code = 0;
        for (int[] t : time) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(this, medicinebroadcast.class);
            PendingIntent sender = PendingIntent.getBroadcast(this, code, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            long now = calendar.getTimeInMillis();
            calendar.set(Calendar.HOUR_OF_DAY, t[0]);
            calendar.set(Calendar.MINUTE, t[1]);
            calendar.set(Calendar.SECOND, 0);
            if (now > calendar.getTimeInMillis()) {
                calendar.add(calendar.DATE, 1);
            }
            System.out.println("the time: " + calendar.getTime());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
            }
            code++;
        }
    }
}