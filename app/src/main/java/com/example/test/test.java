package com.example.test;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class test extends AppCompatActivity {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        int pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");
        ArrayList<String> theresult = new ArrayList<String>();

        bundle1.putString("userid",userid);
        bundle1.putInt("pid",pid);
        bundle1.putStringArray("result",result);
        bundle1.putIntegerArrayList("food",food);
        bundle1.putIntegerArrayList("medicine",medicine);
        bundle1.putIntegerArrayList("poop",poop);
        bundle1.putStringArrayList("deworm",deworm);
        bundle1.putStringArrayList("vaccine",vaccine);

        RadioGroup Q1= findViewById(R.id.questionNumber1);
        RadioGroup Q2= findViewById(R.id.questionNumber2);
        RadioGroup Q3= findViewById(R.id.questionNumber3);
        RadioGroup Q4= findViewById(R.id.questionNumber4);
        Button button9= findViewById(R.id.button9);
//        TextView T1=findViewById(R.id.result);

        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int radioId1=Q1.getCheckedRadioButtonId();
                RadioButton R1=findViewById(radioId1);
                String S1=" ";

                switch(R1.getId()){
                    case R.id.button1:
                        S1="E";
                        break;
                    case R.id.button2:
                        S1="I";
                        break;
                }

                int radioId2=Q2.getCheckedRadioButtonId();
                RadioButton R2=findViewById(radioId2);
                String S2=" ";
                switch(R2.getId()){
                    case R.id.button3:
                        S2="S";
                        break;
                    case R.id.button4:
                        S2="N";
                        break;
                }

                int radioId3=Q3.getCheckedRadioButtonId();
                RadioButton R3=findViewById(radioId3);
                String S3=" ";
                switch(R3.getId()){
                    case R.id.button5:
                        S3="T";
                        break;
                    case R.id.button6:
                        S3="F";
                        break;
                }

                int radioId4=Q4.getCheckedRadioButtonId();
                RadioButton R4=findViewById(radioId4);
                String S4=" ";
                switch(R4.getId()){
                    case R.id.button7:
                        S4="J";
                        break;
                    case R.id.button8:
                        S4="P";
                        break;
                }
                String testresult=S1+S2+S3+S4;

                setContentView(R.layout.matching);

                final String[][] personality = {{"", "", "", "",""}};
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimeSql con = new TimeSql();
                        con.run();
                        personality[0] = con.getResult(testresult);
                        while (personality[0][0] == "") {}
                        for(String s:personality[0]){
                            theresult.add(s);
                        }
                        bundle1.putString("testresult",testresult);
                        bundle1.putStringArrayList("theresult",theresult);
                        Intent i = new Intent();
                        i.setClass(test.this,result.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                    }
                }).start();
            }
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
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(this,testStart.class);
            i.putExtras(bundle1);
            startActivity(i);
        }
        return super.onKeyDown(keycode,event);
    }
}
