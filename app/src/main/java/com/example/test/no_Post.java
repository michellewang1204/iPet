package com.example.test;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class no_Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_post);
        Intent intent = new Intent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        BottomNavigationView bottomNavigationView
                = (BottomNavigationView) findViewById(R.id.nvgationview);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, false);

        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {

                case R.id.testStart:
                    break;
                case R.id.home:
                    intent.setClass(this, myProfile.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });

    }
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent1 = new Intent();
        switch (item.getItemId()){
            case R.id.setting:
                intent1.setClass(this,setting.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
