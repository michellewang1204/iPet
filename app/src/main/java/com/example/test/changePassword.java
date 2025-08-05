package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class changePassword extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    final String[] userid = {""};
    int pid;
    String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        getSupportActionBar().hide();
        bundle = getIntent().getExtras();
        userid[0] = bundle.getString("userid");
        pid = bundle.getInt("pid");
        result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");

        bundle1.putString("userid", userid[0]);
        bundle1.putInt("pid", pid);
        bundle1.putStringArray("result", result);
        bundle1.putIntegerArrayList("food", food);
        bundle1.putIntegerArrayList("medicine", medicine);
        bundle1.putIntegerArrayList("poop", poop);
        bundle1.putStringArrayList("deworm", deworm);
        bundle1.putStringArrayList("vaccine", vaccine);
        Button check = (Button) findViewById(R.id.change);
        TextView textView = findViewById(R.id.ed_changeName);
        textView.setText(userid[0]);
        check.setOnClickListener(this);
        EditText petname = (EditText) findViewById(R.id.petname);
        petname.setText(result[0]);

    }

    @Override
    public void onClick(View v) {
        EditText edPassWord = (EditText) findViewById(R.id.ed_changePassword);
        EditText petname = (EditText) findViewById(R.id.petname);
        if (edPassWord.getText().length() > 15) {
            Toast.makeText(changePassword.this, "密碼長度不能大於15個字符", Toast.LENGTH_SHORT).show();
        } else if (edPassWord.getText().length() < 6) {
            Toast.makeText(changePassword.this, "密碼長度不能小於6個字符", Toast.LENGTH_SHORT).show();
        } else {
            if (petname.getText().length() > 15) {
                Toast.makeText(changePassword.this, "寵物名稱長度不能大於15個字符", Toast.LENGTH_SHORT).show();
            } else if (petname.getText().length() < 1) {
                Toast.makeText(changePassword.this, "寵物名稱長度不能小於1個字符", Toast.LENGTH_SHORT).show();
            } else {
                result[0] = String.valueOf(petname.getText());
                bundle1.putStringArray("result", result);
                final String[] password = {""};
                final String[] pet = {""};
                if (edPassWord.getText() != null) {
                    password[0] = edPassWord.getText().toString();
                    pet[0] = petname.getText().toString();
                }
                final int[] check = {-1};
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimeSql con = new TimeSql();
                        con.run();
                        con.changeAccount(password[0], userid[0]);
                        con.changePetName( pet[0],pid);
                        check[0] = 0;
                    }
                }).start();
                while (check[0] == -1) {
                }
                Intent i = new Intent();
                i.putExtras(bundle);
                i.setClass(this, myProfile.class);
                startActivity(i);
            }
        }
    }
}