package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Looper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addpetinfo extends AppCompatActivity implements View.OnClickListener {

    String userid;
    int pid;
    String gender;
    String variety;
    int PICK_IMAGE_MULTIPLE = 1;
    ImageView pic;
    String uri = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpetinfo);
        getSupportActionBar().hide();
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        userid = bundle.getString("userid");
        pid = bundle.getInt("pid");

        setSpinner();
        TextView textView = findViewById(R.id.userid);
        textView.setText(userid);

        Button check = (Button) findViewById(R.id.check1);
        check.setOnClickListener(this);
        pic = findViewById(R.id.pic);
        Button picker = findViewById(R.id.picker);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                // 若要選取影片則改為 intent.setType("video/*");
                // 設置選取的檔案的 MIME 屬性，要是想過濾特定的格式也可以把 * 換成目標檔案格式
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                // 設置可以多選的選取屬性，若不需要多選可以移除
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // 設置動作類型，這邊只進行讀取
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
                // 交辦 intent 以及接收回傳選取結果
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
                Uri currentUri = data.getData();
                pic.setImageURI(currentUri);
                uri = currentUri.toString();
                // 這邊便可以對輸入的 data 進行我們想要做的處理
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        EditText Petname = (EditText) findViewById(R.id.petname);
        EditText Birthday = (EditText) findViewById(R.id.birthday);
//        EditText Variety = (EditText) findViewById(R.id.variety);

        String petname = String.valueOf(Petname.getText());
        String birthday = String.valueOf(Birthday.getText());
//        String variety = String.valueOf(Variety.getText());
        Date Birth = null;

        boolean check = false;
        try {
            Birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            check = true;
        } catch (ParseException e) {
            Toast.makeText(addpetinfo.this, "生日需要照 yyyy-MM-dd 格式輸入喔", Toast.LENGTH_SHORT).show();
            check = false;
        }

        if (uri.equals("")) {
            Toast.makeText(addpetinfo.this, "一定要選擇一張照片喔", Toast.LENGTH_SHORT).show();
        }else if (petname.length() > 15) {
            Toast.makeText(addpetinfo.this, "寵物名稱長度不能大於15個字符", Toast.LENGTH_SHORT).show();
        } else if (petname.length() < 1) {
            Toast.makeText(addpetinfo.this, "寵物名稱長度不能小於1個字符", Toast.LENGTH_SHORT).show();
        } else {
            if (petname.length() == 0 || birthday.length() == 0 || variety.length() == 0) {
                Toast.makeText(addpetinfo.this, "每個欄位都要填寫喔", Toast.LENGTH_SHORT).show();
            } else {
                if (check) {
                    int limit = getAgeByBirth(Birth);
                    if (limit > 22) {
                        Toast.makeText(addpetinfo.this, "您的狗狗年齡已經超過世界紀錄了?", Toast.LENGTH_SHORT).show();
                    } else if (limit < 0) {
                        Toast.makeText(addpetinfo.this, "狗狗還在媽媽的肚子裡?", Toast.LENGTH_SHORT).show();
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String finalBirth = sdf.format(Birth);
                        setContentView(R.layout.loading);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TimeSql con = new TimeSql();
                                con.run();
                                con.addPetInfo(userid, petname, finalBirth, variety, pid, gender, uri);
                                Intent i = new Intent();
                                i.setClass(addpetinfo.this, login1.class);
                                startActivity(i);
                            }
                        }).start();
                    }
                }
            }
        }
    }

    private void setSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(view.getContext(),parent.getSelectedItem().toString()/*這行可直接取得選中內容*/,Toast.LENGTH_SHORT).show();
                gender = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.dogs, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(view.getContext(),parent.getSelectedItem().toString()/*這行可直接取得選中內容*/,Toast.LENGTH_SHORT).show();
                variety = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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