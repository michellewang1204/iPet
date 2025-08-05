package com.example.test;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class testStart extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_start);
        Intent intent = new Intent();

        Button button1 = findViewById(R.id.test_strart3);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.speak);
        button2.setOnClickListener(this);

        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        int pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");

        bundle1.putString("userid",userid);
        bundle1.putInt("pid",pid);
        bundle1.putStringArray("result",result);
        bundle1.putIntegerArrayList("food",food);
        bundle1.putIntegerArrayList("medicine",medicine);
        bundle1.putIntegerArrayList("poop",poop);
        bundle1.putStringArrayList("deworm",deworm);
        bundle1.putStringArrayList("vaccine",vaccine);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        BottomNavigationView bottomNavigationView
                = (BottomNavigationView) findViewById(R.id.nvgationview);
        bottomNavigationView.getMenu().findItem(R.id.testStart).setChecked(true);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.testStart:
                    break;
                case R.id.home:
                    intent.setClass(this, myProfile.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                    break;
                case R.id.health:
                    intent.setClass(this, health.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
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

        Intent intent1 = new Intent();
        switch (item.getItemId()) {
            case R.id.setting:
                intent1.setClass(this, setting.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_strart3:
                Intent intent = new Intent(this, test.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.speak:
                Intent intent1 = new Intent(this, speak.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(this,myProfile.class);
            i.putExtras(bundle1);
            startActivity(i);
        }
        return super.onKeyDown(keycode,event);
    }
}
