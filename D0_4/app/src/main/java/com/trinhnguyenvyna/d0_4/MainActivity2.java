package com.trinhnguyenvyna.d0_4;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trinhnguyenvyna.d0_4.databinding.ActivityMain2Binding;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    Handler handler;
    Random randomNumebring;


    // DE 2016 - 02
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();

        initHandler();
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                int numb = (int) msg.obj;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 8, 8, 8);

                Button btn = new Button(getApplicationContext());
                btn.setText(String.valueOf(numb));
                btn.setTextSize(22);
                btn.setTextColor(Color.WHITE);

                EditText edt = new EditText(getApplicationContext());
                edt.setText(String.valueOf(numb));
                edt.setTextColor(Color.BLACK);
                edt.setTextSize(22);

                if(numb % 2 == 0) {
                    btn.setLayoutParams(params);
                    binding.lnLayoutContainer.addView(btn);
                } else {
                    edt.setLayoutParams(params);
                    binding.lnLayoutContainer.addView(edt);
                }
                return false;
            }
        });
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView();
            }
        });
    }

    private void drawView() {
        binding.lnLayoutContainer.removeAllViews();
        int numberOfView = Integer.parseInt(binding.edtNumber.getText().toString());

        Thread viewThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=numberOfView; i++) {
                    randomNumebring = new Random();
                    int randNumb = randomNumebring.nextInt(100);
                    Message msg = new Message();
                    msg.obj = randNumb;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        Log.i("Error!:", e.getMessage());
                    }
                }
            }
        });

        viewThread.start();
    }
}