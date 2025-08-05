package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Looper;

public class register extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle1 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();
        Button check = (Button) findViewById(R.id.check1);
        check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edEmail = (EditText) findViewById(R.id.Email);
        EditText edPassWord = (EditText) findViewById(R.id.passWord);
//        TextView finalcheck=(TextView)findViewById(R.id.check);

        String Email = null;
        Email = edEmail.getText().toString();


        String password = null;
        password = edPassWord.getText().toString();
        if(Email.length()<1){
            Toast.makeText(register.this,"用戶名長度不得為空",Toast.LENGTH_SHORT).show();
        }else if(Email.length()>15){
            Toast.makeText(register.this,"用戶名長度不得多於15字元",Toast.LENGTH_SHORT).show();
        }else if(password.length()<6){
            Toast.makeText(register.this,"密碼長度不得小於6個字元",Toast.LENGTH_SHORT).show();
        }else if(password.length()>15){
            Toast.makeText(register.this,"密碼長度不得多於15字元",Toast.LENGTH_SHORT).show();
        } else{
            String finalEmail = Email;
            String finalPassword = password;
            setContentView(R.layout.loading);
            final int[] pid = new int[1];
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TimeSql con = new TimeSql();
                    con.run();
                    boolean check=con.checkUsername(finalEmail);
                    if(check==true){
                        con.createAccount(finalEmail, finalPassword);
                        pid[0] = con.getMaxPid();
                        Intent i = new Intent();
                        bundle1.putString("userid", finalEmail);
                        bundle1.putInt("pid", pid[0]+1);
                        i.setClass(register.this, addpetinfo.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                    }else{
                        Looper.prepare();
                        Toast.makeText(register.this,finalEmail+"的名稱已有人使用，請更換其他用戶名",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }).start();
        }
    }
}