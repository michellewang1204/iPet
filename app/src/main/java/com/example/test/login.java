package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class login extends AppCompatActivity implements View.OnClickListener {
    private int num = 0;
    Bundle bundle;
    String userid;
    ArrayList<int[]> foodtime = new ArrayList<int[]>();
    ArrayList<int[]> pooptime = new ArrayList<int[]>();
    ArrayList<int[]> medicinetime = new ArrayList<int[]>();
    ArrayList<String> deworm = new ArrayList<String>();
    ArrayList<String> vaccine = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (getIntent().getBooleanExtra("EXIT", false)) {//根據EXIT值關閉A
            finish();
            return;
        }
        setContentView(R.layout.login);
        Button button1 = findViewById(R.id.bt_check);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.register);
        button2.setOnClickListener(this);
//        Button button3 = findViewById(R.id.forgetPassword);
//        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_check:
                EditText etUserName = (EditText) findViewById(R.id.ed_userName);
                EditText edPassWord = (EditText) findViewById(R.id.ed_passWord);
                bundle = new Bundle();
                final String[] password = {""};
                if (etUserName.length() == 0) {
                    Toast.makeText(v.getContext(), "Email不能為空值", Toast.LENGTH_SHORT).show();
                } else if (edPassWord.length() == 0) {
                    Toast.makeText(v.getContext(), "Password不能為空值", Toast.LENGTH_SHORT).show();
                } else {
                    setContentView(R.layout.loading);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            TimeSql con = new TimeSql();
                            con.run();
                            password[0] = con.getPassword(etUserName.getText().toString());
                            System.out.println(password[0]);
                            System.out.println(edPassWord.getText().toString());
                            if (password[0] == "") {
                                Intent intent1 = new Intent();
                                intent1.setClass(login.this, LoginFailed.class);
                                startActivity(intent1);
                            } else if (password[0].equals(edPassWord.getText().toString())) {
                                Intent intent1 = new Intent();
                                userid = etUserName.getText().toString();
                                bundle.putString("userid", userid);
                                intent1.putExtras(bundle);
                                getData();
                            } else {
                                Intent intent1 = new Intent();
                                intent1.setClass(login.this, LoginFailed.class);
                                startActivity(intent1);
                            }
                        }
                    }).start();
                }
                break;
            case R.id.register:
                Intent intent1 = new Intent();
                intent1.setClass(this, register.class);
                startActivity(intent1);
                break;
        }
    }

    public void getData() {
        ArrayList<Integer> food = new ArrayList<Integer>();
        ArrayList<Integer> poop = new ArrayList<Integer>();
        ArrayList<Integer> medicine = new ArrayList<Integer>();
        final int[] check = {-1};
        final int[] petid = {-1};
        final String[][] result = new String[1][4];
        new Thread(new Runnable() {
            @Override
            public void run() {
                TimeSql con = new TimeSql();
                con.run();
                petid[0] = con.getPid(userid);
                ;
                result[0] = con.getInfo(petid[0]);
                pooptime = con.getAlarm("poop", petid[0]);
                foodtime = con.getAlarm("food", petid[0]);
                medicinetime = con.getAlarm("medicine", petid[0]);
                deworm = con.getDate("deworm", petid[0]);
                vaccine = con.getDate("vaccine", petid[0]);
                check[0] = 0;
            }
        }).start();
        while (check[0] == -1) {
        }
        for (int[] n : pooptime) {
            for (int i : n) {
                Integer k = Integer.valueOf(i);
                poop.add(k);
            }
        }
        for (int[] n : foodtime) {
            for (int i : n) {
                Integer k = Integer.valueOf(i);
                food.add(k);
            }
        }
        for (int[] n : medicinetime) {
            for (int i : n) {
                Integer k = Integer.valueOf(i);
                medicine.add(k);
            }
        }
        bundle.putInt("pid", petid[0]);
        bundle.putStringArray("result", result[0]);
        bundle.putIntegerArrayList("food", food);
        bundle.putIntegerArrayList("medicine", medicine);
        bundle.putIntegerArrayList("poop", poop);
        bundle.putStringArrayList("deworm", deworm);
        bundle.putStringArrayList("vaccine", vaccine);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, myProfile.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Step02-判斷是否按下按鍵，並且確認該按鍵是否為返回鍵:
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // Step03-判斷目前時間與上次按下返回鍵時間是否間隔2000毫秒(2秒):
            Intent it = new Intent(getApplicationContext(), login.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.putExtra("EXIT", true);
            startActivity(it); //啟動A的時候，關閉所有在堆疊中在A上方的Activity，然後根據傳過去的EXI值判斷是否關閉
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}