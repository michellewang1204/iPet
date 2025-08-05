package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

public class no_Adopt extends AppCompatActivity implements View.OnTouchListener {
    ImageView iv_dog1;
    Point touch_xy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_adopt);
        initiallize();
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
                    intent.setClass(this, no_Post.class);
                    startActivity(intent);
                    break;
                case R.id.home:
                    intent.setClass(this, myProfile.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
    }
    public void initiallize(){
        //iv_dog1=(ImageView) findViewById(R.id.iv_dog1);
        //iv_dog1.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touch_xy.x=(int) event.getRawX();
                touch_xy.y=(int) event.getRawY();
                Log.i("touch","up");
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                Log.i("touch","down");
                break;
        }
        return true;
    }
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.setting:
                intent.setClass(this,setting.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void pictureOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, no_adopt_AdoptDogDetail.class);
        startActivity(intent);
    }
}