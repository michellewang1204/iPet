package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginFailed extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.loginfailed);

        ((Button) findViewById(R.id.backToPreviousPage)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(this, login1.class);
        startActivity(intent);
    }
}