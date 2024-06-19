package com.trinhnguyenvyna.d0_4;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trinhnguyenvyna.d0_4.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Handler handler;
    Random random;

    // DE 2016 - 04
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        initHandler();
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                int [] randomNumber = (int[]) msg.obj;

                LinearLayout rowLayout = new LinearLayout(MainActivity.this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rowLayout.setLayoutParams(rowParams);

                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                btnParams.setMargins(10, 10, 10, 10);

                for (int randNumb: randomNumber) {
                    Button btn = new Button(MainActivity.this);
                    btn.setLayoutParams(btnParams);
                    btn.setPadding(8, 8, 8, 8);
                    btn.setTextColor(Color.WHITE);
                    btn.setTextSize(22);
                    btn.setText(String.valueOf(randNumb));

                    if(randNumb % 2 == 0) {
                        btn.setBackgroundColor(Color.GRAY);
                    } else {
                        btn.setBackgroundColor(Color.BLUE);
                    }

                    rowLayout.addView(btn);
                }
                binding.lnLayoutContainer.addView(rowLayout);
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
        int numberOfRows = Integer.parseInt(binding.edtNumber.getText().toString());

        Thread threadDraw = new Thread(new Runnable() {
            @Override
            public void run() {
                random = new Random();
                for (int i=1; i<=numberOfRows; i++) {
                    int[] randNumber = new int[3];

                    for (int j=0; j<3; j++) {
                        randNumber[j] = random.nextInt(10);
                    }

                    Message msg = new Message();
                    msg.obj = randNumber;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(145);
                    } catch (InterruptedException e) {
                        Log.i("Error!:", e.getMessage());
                    }
                }
            }
        });

        threadDraw.start();
    }
}