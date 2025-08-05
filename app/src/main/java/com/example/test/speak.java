package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class speak extends AppCompatActivity {
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    private int SPEECH_REQUEST_CODE = 0;
    private Thread thread;                //執行緒
    private Socket clientSocket;        //客戶端的socket
    String spokenText;    //做為接收時的緩存
    private EditText textView;
    String answer;
    ArrayList<String> theresult = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speak);

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

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySpeechRecognizer();
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View view) {
                textView = (EditText) findViewById(R.id.edt_order_note_text);
                answer = textView.getText().toString();
                if(answer.length()<50){
                    Toast.makeText(view.getContext(), "輸入字數需要超過50字喔", Toast.LENGTH_SHORT).show();
                }else{
                    setContentView(R.layout.matching);
                    getSupportActionBar().hide();
                    thread=new Thread(Connection);
                    thread.start();
                }
            }
        });
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            spokenText = results.get(0);
//            Toast.makeText(this,spokenText,Toast.LENGTH_SHORT).show();
            textView = (EditText) findViewById(R.id.edt_order_note_text);
            String text = String.valueOf(textView.getText());
            textView.setText(text+spokenText);
        }

    }

    private Runnable Connection = new Runnable() {
        @Override
        public void run() {
            try {
                InetAddress serverIp = InetAddress.getByName("140.127.220.78");
                int serverPort = 8000;
                clientSocket = new Socket(serverIp, serverPort);
                DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                while (clientSocket.isConnected()) {
                    String msg = (String) in.readUTF();
                    System.out.println("Server: " + msg);
                    System.out.println("My answer: "+answer);
                    dout.writeUTF(answer);
                    dout.flush();
                    String result = (String) in.readUTF();
                    System.out.println(result);
                    dout.close();
                    clientSocket.close();
                    bundle1.putString("testresult",result);

                    final String[][] personality = {{"", "", "", "",""}};
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            TimeSql con = new TimeSql();
                            con.run();
                            personality[0] = con.getResult(result);
                            while (personality[0][0] == "") {}
                            for(String s:personality[0]){
                                theresult.add(s);
                            }
                            bundle1.putStringArrayList("theresult",theresult);
                            Intent i = new Intent();
                            i.setClass(speak.this,result.class);
                            i.putExtras(bundle1);
                            startActivity(i);
                        }
                    }).start();
                }
            } catch (Exception e) {
                //當斷線時自動關閉房間
            }
        }
    };
}