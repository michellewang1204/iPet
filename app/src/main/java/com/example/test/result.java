package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class result extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    ArrayList<String> ans = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        int pid = bundle.getInt("pid");
        String[] result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");
        ArrayList<String> personality = (ArrayList<String>) bundle.get("theresult");
        String testresult = bundle.getString("testresult");

        bundle1.putString("userid", userid);
        bundle1.putInt("pid", pid);
        bundle1.putStringArray("result", result);
        bundle1.putIntegerArrayList("food", food);
        bundle1.putIntegerArrayList("medicine", medicine);
        bundle1.putIntegerArrayList("poop", poop);
        bundle1.putStringArrayList("deworm", deworm);
        bundle1.putStringArrayList("vaccine", vaccine);
        bundle1.putStringArray("result", result);
        TextView text = findViewById(R.id.alarm1);
        TextView text1 = findViewById(R.id.text1);
        ImageView img = findViewById(R.id.img);
        ImageView dimg1 = findViewById(R.id.dimg1);
        ImageView dimg2 = findViewById(R.id.dimg2);
        ImageView dimg3 = findViewById(R.id.dimg3);
        TextView dog1 = findViewById(R.id.dog1);
        TextView dog2 = findViewById(R.id.dog2);
        TextView dog3 = findViewById(R.id.dog3);
        System.out.println(personality.size());
        text.setText(testresult + "-" + personality.get(4));
        String lower = testresult.toLowerCase();
        int resouce = getResources().getIdentifier(lower + "_bk", "drawable", getPackageName());
        String[] D = {"", "", ""};
        for (int n = 1; n < 4; n++) {
            switch (personality.get(n)) {
                case "哈士奇":
                    D[n - 1] = "husky";
                    break;
                case "柯基":
                    D[n - 1] = "corgi";
                    break;
                case "博美":
                    D[n - 1] = "pome";
                    break;
                case "柴犬":
                    D[n - 1] = "shiba";
                    break;
                case "法鬥":
                    D[n - 1] = "french";
                    break;
                case "米克斯":
                    D[n - 1] = "mix";
                    break;
                case "吉娃娃":
                    D[n - 1] = "chi";
                    break;
                case "拉布拉多犬":
                    D[n - 1] = "labrado";
                    break;
                case "瑪爾濟斯":
                    D[n - 1] = "mars";
                    break;
            }
        }
        int d1 = getResources().getIdentifier(D[0], "drawable", getPackageName());
        int d2 = getResources().getIdentifier(D[1], "drawable", getPackageName());
        int d3 = getResources().getIdentifier(D[2], "drawable", getPackageName());
        img.setImageResource(resouce);
        dimg1.setImageResource(d1);
        dimg2.setImageResource(d2);
        dimg3.setImageResource(d3);
        dog1.setText(personality.get(1));
        dog2.setText(personality.get(2));
        dog3.setText(personality.get(3));
        text1.setText(personality.get(0));
        Button back = findViewById(R.id.back);
        back.setOnClickListener(result.this);
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

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, myProfile.class);
        i.putExtras(bundle1);
        startActivity(i);
    }
}