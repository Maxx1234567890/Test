package com.trinhnguyenvyna.d0_4;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
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

import com.trinhnguyenvyna.d0_4.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    ActivityMain4Binding binding;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        initHandler();
        setupEditText();

    }
    private void setupEditText() {
        // Set the input filter to allow digits, and block '*' and '#'
        InputFilter[] filters = new InputFilter[]{
                new InputFilter() {
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source.equals("")) { // for backspace
                            return source;
                        }
                        if (source.toString().matches("[0-9*#]+")){
                            return source;
                        }
                        return "";
                    }
                }
        };
        binding.edtNumber.setFilters(filters);
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                // Cast the object to String array
                String[] buttonValues = (String[]) msg.obj;

                // Create a new row layout
                LinearLayout rowLayout = new LinearLayout(MainActivity4.this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rowLayout.setLayoutParams(rowParams);

                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                btnParams.setMargins(10, 10, 10, 10);


                for (String value : buttonValues) {
                    Button btn = new Button(MainActivity4.this);
                    btn.setLayoutParams(btnParams);
                    btn.setPadding(8, 8, 8, 8);
                    btn.setTextColor(Color.WHITE);
                    btn.setTextSize(22);
                    btn.setText(value);

                    // Set the click listener for the button
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            binding.edtNumber.append(value);
                        }
                    });

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

        Thread tDraw = new Thread(new Runnable() {
            @Override
            public void run() {
                // Define the buttons for each row
                String[][] buttons = {
                        {"1", "2", "3"},
                        {"4", "5", "6"},
                        {"7", "8", "9"},
                        {"*", "0", "#"}
                };

                // Send each row to the handler
                for (String[] row : buttons) {
                    Message msg = new Message();
                    msg.obj = row;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(155);
                    } catch (InterruptedException e) {
                        Log.i("Error!:", e.getMessage());
                    }
                }}
        });
        tDraw.start();
    }
}