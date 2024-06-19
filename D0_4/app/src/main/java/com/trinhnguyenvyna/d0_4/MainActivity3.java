package com.trinhnguyenvyna.d0_4;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trinhnguyenvyna.d0_4.databinding.ActivityMain3Binding;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;
    Handler handler;
    int count = 0;
    Random rdNumber ;
    LinearLayout currentRow;

    // DE 2016 - 05
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rdNumber = new Random();

        addEvents();
        initHandler();
    }

    private void initHandler() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                int randomNumber = (int) msg.obj;
                LinearLayout.LayoutParams params;

                if (count % 2 == 0) {
                    currentRow = new LinearLayout(getApplicationContext());
                    currentRow.setOrientation(LinearLayout.HORIZONTAL);
                    currentRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    binding.lnLayoutContainer.addView(currentRow);
                }

                Button btn = new Button(getApplicationContext());
                btn.setText(String.valueOf(randomNumber));
                btn.setTextSize(25);
                btn.setTextColor(Color.WHITE);

                if (randomNumber % 2 == 0) {
                    btn.setBackgroundColor(Color.BLUE);
                } else {
                    btn.setBackgroundColor(Color.GRAY);
                }

                if ((count / 2) % 2 == 0) {
                    if (count % 2 == 0) {
                        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 7f);
                    } else {
                        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f);
                    }
                } else {
                    if (count % 2 == 0) {
                        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f);
                    } else {
                        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 7f);
                    }
                }

                params.setMargins(10, 10, 10, 10);
                btn.setLayoutParams(params);
                currentRow.addView(btn);

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
        int numbRow = Integer.parseInt(binding.edtNumber.getText().toString());

        Thread tDraw = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=numbRow; i++) {
                    int randomNumber = rdNumber.nextInt(10);
                    count = i-1;
                    Message msg = Message.obtain();
                    msg.obj = randomNumber;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(155);
                    } catch (InterruptedException e) {
                        Log.i("Error!:", e.getMessage());
                    }
                }
            }
        });
        tDraw.start();
    }

}