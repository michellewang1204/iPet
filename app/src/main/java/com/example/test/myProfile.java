package com.example.test;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class myProfile extends AppCompatActivity {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    int PICK_IMAGE_MULTIPLE =1;
    ImageView pic ;
    String[] result;
    int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        bundle = getIntent().getExtras();
        String userid = bundle.getString("userid");
        pid = bundle.getInt("pid");
        result = bundle.getStringArray("result");
        ArrayList<Integer> food = (ArrayList<Integer>) bundle.get("food");
        ArrayList<Integer> poop = (ArrayList<Integer>) bundle.get("poop");
        ArrayList<Integer> medicine = (ArrayList<Integer>) bundle.get("medicine");
        ArrayList<String> deworm = (ArrayList<String>) bundle.get("deworm");
        ArrayList<String> vaccine = (ArrayList<String>) bundle.get("vaccine");

        bundle1.putString("userid", userid);
        bundle1.putInt("pid", pid);
        bundle1.putStringArray("result", result);
        bundle1.putIntegerArrayList("food", food);
        bundle1.putIntegerArrayList("medicine", medicine);
        bundle1.putIntegerArrayList("poop", poop);
        bundle1.putStringArrayList("deworm", deworm);
        bundle1.putStringArrayList("vaccine", vaccine);

        TextView name = findViewById(R.id.name);
        TextView name1 = findViewById(R.id.name1);
        TextView gender = findViewById(R.id.gender);
        TextView birthday = findViewById(R.id.birthday);
        TextView age = findViewById(R.id.age);
        TextView account = findViewById(R.id.account);
        TextView feed = findViewById(R.id.feed);
        TextView poop1 = findViewById(R.id.poop);
        TextView medicine1 = findViewById(R.id.medicine);
        Intent intent = new Intent();
        pic = findViewById(R.id.pic);

        name.setText(result[0]);
        name1.setText(result[0]);
        gender.setText(result[1]);
//        String Bday = result[2].substring(5);
        birthday.setText(result[2].replace('-', '/'));

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

        Date bday = null;
        try {
            bday = new SimpleDateFormat("yyyy-MM-dd").parse(result[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(getAgeByBirth(bday));
        String myage = String.valueOf(getAgeByBirth(bday));
        age.setText(myage);
        account.setText(userid);
        int foodCount = 0;
        int poopCount = 0;
        int medicineCount = 0;

        for (int n = 0; n < food.size() / 3; n++) {
            if (food.get(n * 3 + 2) == 0) {
                foodCount = foodCount + 1;
            }
        }
        for (int n = 0; n < poop.size() / 3; n++) {
            if (poop.get(n * 3 + 2) == 0) {
                poopCount = poopCount + 1;
            }
        }
        for (int n = 0; n < medicine.size() / 3; n++) {
            if (medicine.get(n * 3 + 2) == 0) {
                medicineCount = medicineCount + 1;
            }
        }

        feed.setText(Integer.toString(foodCount));
        poop1.setText(Integer.toString(poopCount));
        medicine1.setText(Integer.toString(medicineCount));


        BottomNavigationView bottomNavigationView
                = (BottomNavigationView) findViewById(R.id.nvgationview);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.testStart:
                    intent.putExtras(bundle1);
                    intent.setClass(this, testStart.class);
                    startActivity(intent);
                    break;
                case R.id.home:
                    break;
                case R.id.health:
                    intent.putExtras(bundle1);
                    intent.setClass(this, health.class);
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

        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.setting:
                intent.setClass(this, setting.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    picture picker
    public void imageClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        // 若要選取影片則改為 intent.setType("video/*");
        // 設置選取的檔案的 MIME 屬性，要是想過濾特定的格式也可以把 * 換成目標檔案格式
        intent.putExtras(bundle1);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        // 設置可以多選的選取屬性，若不需要多選可以移除
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // 設置動作類型，這邊只進行讀取
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
        // 交辦 intent 以及接收回傳選取結果
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
                Toast.makeText(this, "Success to pick image", Toast.LENGTH_LONG).show();
                Uri currentUri = data.getData();
                pic.setImageURI(currentUri);
                String uri = currentUri.toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimeSql con = new TimeSql();
                        con.run();
                        con.setUri(pid,uri);
                    }
                }).start();
                result[3] = uri;
                bundle1.putStringArray("result", result);
                // 這邊便可以對輸入的 data 進行我們想要做的處理
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private long timeSave = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Step02-判斷是否按下按鍵，並且確認該按鍵是否為返回鍵:
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // Step03-判斷目前時間與上次按下返回鍵時間是否間隔2000毫秒(2秒):
            if ((System.currentTimeMillis() - timeSave) > 2000) {
                Toast.makeText(this, "再按一次退出!!", Toast.LENGTH_SHORT).show();
                // Step04-紀錄第一次案返回鍵的時間:
                timeSave = System.currentTimeMillis();
            } else {
                Intent it = new Intent(getApplicationContext(), login.class);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                it.putExtra("EXIT", true);
                startActivity(it); //啟動A的時候，關閉所有在堆疊中在A上方的Activity，然後根據傳過去的EXI值判斷是否關閉
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    public int getAgeByBirth(Date birthday) {
        Calendar cal = Calendar.getInstance();
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthday is before now,It's unbelievable");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);

        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
            age--;
        }
        return age;
    }
}