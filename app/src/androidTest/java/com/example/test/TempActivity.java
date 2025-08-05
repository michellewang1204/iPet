package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
    }

    public void picture_Click(View view) {
        Intent intent = new Intent();
        intent.setClass(this, no_post_PostDetail.class);
        startActivity(intent);
    }
}