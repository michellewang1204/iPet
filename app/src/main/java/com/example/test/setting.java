package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class setting extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        findViewById(R.id.bt_logout).setOnClickListener(this);
        findViewById(R.id.bt_changePassword).setOnClickListener(this);
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

    }
    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent();
        switch (v.getId()) {
            case R.id.bt_logout:
                intent1.setClass(this, login1.class);
                startActivity(intent1);
                break;
            case R.id.bt_changePassword:
                intent1.setClass(this,changePassword.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
        }
    }
}