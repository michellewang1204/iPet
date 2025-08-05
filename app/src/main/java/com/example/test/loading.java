package com.example.test;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class loading extends AppCompatActivity {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    ArrayList<int[]> foodtime = new ArrayList<int[]>();
    ArrayList<int[]> pooptime = new ArrayList<int[]>();
    ArrayList<int[]> medicinetime = new ArrayList<int[]>();
    int num = 1;
    boolean check;
    TextView load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffe474"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getData();
    }
    private long timeSave = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Step02-判斷是否按下按鍵，並且確認該按鍵是否為返回鍵:
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            // Step03-判斷目前時間與上次按下返回鍵時間是否間隔2000毫秒(2秒):
            if((System.currentTimeMillis()-timeSave) > 2000){
                Toast.makeText(this, "再按一次退出!!", Toast.LENGTH_SHORT).show();
                // Step04-紀錄第一次案返回鍵的時間:
                timeSave = System.currentTimeMillis();
            }else {
                Intent it = new Intent(getApplicationContext(), login.class);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                it.putExtra("EXIT", true);
                startActivity(it); //啟動A的時候，關閉所有在堆疊中在A上方的Activity，然後根據傳過去的EXI值判斷是否關閉
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
    public void getData(){
        load = findViewById(R.id.load);
        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        ArrayList<Integer> food = new ArrayList<Integer>();
        ArrayList<Integer> poop = new ArrayList<Integer>();
        ArrayList<Integer> medicine = new ArrayList<Integer>();
        final int[] check = {-1};
        final int[] petid = {-1};
        final String[][] result = new String[1][4];
        new Thread(new Runnable(){
            @Override
            public void run(){
                TimeSql con = new TimeSql();
                con.run();
                petid[0] = con.getPid(userid);;
                result[0] = con.getInfo(petid[0]);
                pooptime = con.getAlarm("poop",petid[0]);
                foodtime = con.getAlarm("food",petid[0]);
                medicinetime = con.getAlarm("medicine",petid[0]);
                check[0]=0;
            }
        }).start();
        setContentView(R.layout.loading);
        while(check[0]==-1){
            if(num%3==1){
                load.setText("Loading.");
            }else if(num%3==2){
                load.setText("Loading..");
            }else{
                load.setText("Loading...");
            }
            num = num + 1;
        }

        for(int[] n:pooptime){
            for(int i:n){
                Integer k =Integer.valueOf(i);
                poop.add(k);
            }
        }
        for(int[] n:foodtime){
            for(int i:n){
                Integer k =Integer.valueOf(i);
                food.add(k);
            }
        }
        for(int[] n:medicinetime){
            for(int i:n){
                Integer k =Integer.valueOf(i);
                medicine.add(k);
            }
        }

        bundle1.putString("userid",userid);
        bundle1.putInt("pid",petid[0]);
        bundle1.putStringArray("result",result[0]);
        bundle1.putIntegerArrayList("food",food);
        bundle1.putIntegerArrayList("medicine",medicine);
        bundle1.putIntegerArrayList("poop",poop);

        Intent intent = new Intent();
        intent.putExtras(bundle1);
        intent.setClass(this,myProfile.class);
        startActivity(intent);
    }
}