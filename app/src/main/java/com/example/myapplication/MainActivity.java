package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

class Msg5{
    public static final UUID  id = UUID.randomUUID();
}


public class MainActivity extends AppCompatActivity {
    TimeController ctrl = new TimeController();
    MsgController mctrl = new MsgController();
    Subscriber s1, s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button_add1);
        s1 = new Subscriber() {
            @Override
            public void noMsgCome() {
                runOnUiThread(() -> Log.v("Button1: ", "msg come!"));

            }
        };
        btn.setOnClickListener(view -> {
            ctrl.add(1000, s1);
        });

        s2 =  new Subscriber() {
            @Override
            public void noMsgCome() {
                runOnUiThread(() -> Log.v("Button2: " , "msg come!"));
            }
        };
        Button btn2 = findViewById(R.id.button_add2);
        btn2.setOnClickListener(view -> {
            ctrl.add(1000, s2);
        });

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(view -> ctrl.remove(1000, s1));
        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(view -> ctrl.remove(1000, s2));

        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(view -> mctrl.add(Msg5.id, s1));
        Button btn6 = findViewById(R.id.button6);
        btn6.setOnClickListener(view -> mctrl.trigger(Msg5.id));
    }

    void onButtonAdd1(View view){

    }
    void onButtonAdd2(View view){

    }
}