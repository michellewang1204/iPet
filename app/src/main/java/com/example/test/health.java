package com.example.test;

import android.app.PendingIntent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class health extends AppCompatActivity implements View.OnClickListener {
    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health1);

//        取得&傳送參數
        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        int pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");
        ImageView pic = findViewById(R.id.pic);

        if(result[3].equals("")){
            int ID = getResources().getIdentifier("corgi", "drawable", getPackageName());
            pic.setImageResource(ID);
        }else{
            Uri uri = Uri.parse(result[3]);
            try{
                pic.setImageURI(uri);
            }catch (Exception e){
                int ID = getResources().getIdentifier("corgi", "drawable", getPackageName());
                pic.setImageResource(ID);
            }
        }

        bundle1.putString("userid", userid);
        bundle1.putInt("pid", pid);
        bundle1.putStringArray("result", result);
        bundle1.putIntegerArrayList("food", food);
        bundle1.putIntegerArrayList("medicine", medicine);
        bundle1.putIntegerArrayList("poop", poop);
        bundle1.putStringArrayList("deworm", deworm);
        bundle1.putStringArrayList("vaccine", vaccine);

        Button button1 = findViewById(R.id.feed);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.poop);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.medicine);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.vaccine);
        button4.setOnClickListener(this);
        Button button5 = findViewById(R.id.deworming);
        button5.setOnClickListener(this);

        TextView name = findViewById(R.id.name);
//        TextView gender = findViewById(R.id.gender);
//        TextView birthday = findViewById(R.id.birthday);
//        TextView age = findViewById(R.id.age);

        name.setText(result[0]);
//        gender.setText(result[1]);
//        birthday.setText(result[2]);
//        age.setText(result[3]);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nvgationview);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.getMenu().findItem(R.id.health).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {

                case R.id.testStart:
                    intent.setClass(this, testStart.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                    break;
                case R.id.home:
                    intent.setClass(this, myProfile.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                    break;
                case R.id.health:
                    break;
            }
            return true;
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                intent.setClass(this, setting.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        int pid = bundle.getInt("pid");
        switch (v.getId()) {
            case R.id.feed:
                Intent intent1 = new Intent(this, food.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.poop:
                Intent intent2 = new Intent(this, poop.class);
                intent2.putExtras(bundle1);
                startActivity(intent2);
                break;
            case R.id.medicine:
                Intent intent3 = new Intent(this, medicine.class);
                intent3.putExtras(bundle1);
                startActivity(intent3);
                break;
            case R.id.vaccine:
                Intent intent4 = new Intent(this, vaccine.class);
                intent4.putExtras(bundle1);
                startActivity(intent4);
                break;
            case R.id.deworming:
                Intent intent5 = new Intent(this, deworming.class);
                intent5.putExtras(bundle1);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(this, myProfile.class);
            i.putExtras(bundle1);
            startActivity(i);
        }
        return super.onKeyDown(keycode, event);
    }
}