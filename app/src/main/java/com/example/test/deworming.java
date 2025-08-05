package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class deworming extends AppCompatActivity {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    ArrayList<Date> date = new ArrayList<Date>();
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    int pid;
    ArrayList<String> deworm;
    ArrayList<String[]> thedate;
    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");

        bundle1.putString("userid", userid);
        bundle1.putInt("pid", pid);
        bundle1.putStringArray("result", result);
        bundle1.putIntegerArrayList("food", food);
        bundle1.putIntegerArrayList("medicine", medicine);
        bundle1.putIntegerArrayList("poop", poop);
        bundle1.putStringArrayList("deworm", deworm);
        bundle1.putStringArrayList("vaccine", vaccine);

        thedate = new ArrayList<String[]>();

        for (int n = 0; n < deworm.size() / 2; n++) {
            String[] temp = {deworm.get(n * 2), deworm.get(n * 2 + 1)};
            thedate.add(temp);
        }
        for (String[] d : thedate) {
            System.out.println(d);
            String thedate = d[0].substring(0, 4) + "-" + d[0].substring(5, 7) + "-" + d[0].substring(8, 10);
            Date day = null;
            try {
                day = (Date) f.parse(thedate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date.add(day);
        }
        ProgressDialog dialog = ProgressDialog.show(this, "", "請稍候");
        new Thread(() -> {
            /**由於此開源庫的Calender為耗時工作，故加入背景執行使載入介面時不會閃退*/
            runOnUiThread(() -> {
                setContentView(R.layout.deworming);
                ImageButton back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(deworming.this, health.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                    }
                });
                dialog.dismiss();
                setView();
                setSpinner();
                setAlarm();
            });
        }).start();
    }

    private void setSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(view.getContext(),parent.getSelectedItem().toString()/*這行可直接取得選中內容*/,Toast.LENGTH_SHORT).show();
                cat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void setAlarm() {
        int num = 50;
        for (String[] d : thedate) {
            Intent intent = new Intent(this, dewormbroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, num, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(d[0].substring(0, 4)), Integer.valueOf(d[0].substring(5, 7)), Integer.valueOf(d[0].substring(8, 10)));
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    private void setView() {
        Button btSetTarget, btClearTarget, btToday, btGetDay;
        CalendarView calendarView = findViewById(R.id.calendarView);
        btSetTarget = findViewById(R.id.button_SetTarget);
        btClearTarget = findViewById(R.id.button_CancelTarget);
        btToday = findViewById(R.id.button_Today);
        btGetDay = findViewById(R.id.button_GetTheDay);

        List<EventDay> event = new ArrayList<>();

        for (Date d : date) {
            Calendar c = Calendar.getInstance();
            /**取得選定日之Date*/
            c.setTime(d);
            /**在event陣列中新增一個元素*/
            event.add(new EventDay(c, R.drawable.star));
            runOnUiThread(() -> {
                /**刷新介面*/
                calendarView.setEvents(event);
            });
        }

        /**設置標記*/
        btSetTarget.setOnClickListener(v -> {
            new Thread(() -> {
                /**利用forEach迴圈找出指定元素*/
                boolean check = true;
                for (Calendar calendar : calendarView.getSelectedDates()) {
                    /**取得選定日之Date*/
                    calendar.setTime(calendar.getTime());
                    String inActiveDate = null;
                    inActiveDate = f.format(calendar.getTime());
                    String finalInActiveDate = inActiveDate;
                    for (int n = 0; n < deworm.size() - 1; n++) {
                        if (deworm.get(n).equals(finalInActiveDate) && deworm.get(n + 1).equals(cat)) {
                            check = false;
                        }
                    }
                    if (check) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TimeSql con = new TimeSql();
                                con.run();
                                con.setDate(finalInActiveDate, pid, cat, "deworm");
                            }
                        }
                        ).start();
                        deworm.add(finalInActiveDate);
                        deworm.add(cat);
                        bundle1.putStringArrayList("deworm", deworm);
                        /**在event陣列中新增一個元素*/
                        event.add(new EventDay(calendar, R.drawable.star));
                        runOnUiThread(() -> {
                            /**刷新介面*/
                            calendarView.setEvents(event);
                        });
                    }
                }if(check==false){
                    Looper.prepare();
                    Toast.makeText(this, "當天已經有設定" + cat + "的提醒了", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }).start();
        });

        /**解除標記*/
        btClearTarget.setOnClickListener(v -> {
            new Thread(() -> {
                /**利用forEach迴圈找出指定元素*/
                for (Calendar calendar : calendarView.getSelectedDates()) {
                    /**取得選定日之Date*/
                    calendar.setTime(calendar.getTime());
                    String inActiveDate = null;
                    inActiveDate = f.format(calendar.getTime());
                    String finalInActiveDate = inActiveDate;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            TimeSql con = new TimeSql();
                            con.run();
                            con.deleteDate(finalInActiveDate, pid, "deworm");
                        }
                    }
                    ).start();
                    int index = deworm.indexOf(finalInActiveDate);
                    deworm.remove(finalInActiveDate);
                    deworm.remove(index);
                    bundle1.putStringArrayList("deworm", deworm);
                    /**利用for迴圈找出指定元素之index*/
                    for (int i = 0; i < event.size(); i++) {
                        Long select = calendar.getTimeInMillis();
                        Long target = event.get(i).getCalendar().getTimeInMillis();
                        if (select.equals(target)) {
                            /**刪除指定元素*/
                            event.remove(i);
                            runOnUiThread(() -> {
                                /**刷新介面*/
                                calendarView.setEvents(event);
                            });
                        }
                    }
                }
            }).start();
        });

        /**取得選中之日期*/
        btGetDay.setOnClickListener(v -> {
            String category = "";
            /**利用forEach迴圈找出指定元素*/
            for (Calendar calendar : calendarView.getSelectedDates()) {
                calendar.setTime(calendar.getTime());
                String inActiveDate = null;
                inActiveDate = f.format(calendar.getTime());
                String finalInActiveDate = inActiveDate;
                System.out.println(finalInActiveDate);
                for (int n = 0; n < deworm.size(); n++) {
                    if (deworm.get(n).equals(finalInActiveDate)) {
                        category = category + " " + deworm.get(n + 1);
                    }
                }
                if (category != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    Toast.makeText(this, sdf.format(calendar.getTime()) + " " + category, Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    Toast.makeText(this, sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**跳至今日(指定)日期*/
        btToday.setOnClickListener(v -> {
            /**取得今日之Date*/
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfM = new SimpleDateFormat("MM");
            SimpleDateFormat sdfD = new SimpleDateFormat("dd");
            /**calender設置為今日*/
            calendar.set(Integer.parseInt(sdfY.format(date))
                    , Integer.parseInt(sdfM.format(date)) - 1
                    , Integer.parseInt(sdfD.format(date)));
            try {
                /**刷新介面*/
                calendarView.setDate(calendar);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
        });
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
}
