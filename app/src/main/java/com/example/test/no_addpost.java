package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.Menu;

public class no_addpost extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_post_addpost);
        BottomNavigationView bottomNavigationView
                = (BottomNavigationView) findViewById(R.id.nvgationview);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, false);
    }
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
