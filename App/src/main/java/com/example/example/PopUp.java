package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.NumberPicker;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.TextView;


public class PopUp extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences preferences;
    Button ok;
    int maxNum;
    int num;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        Intent intent = getIntent();
        maxNum = intent.getIntExtra("value", 16);
        name = intent.getStringExtra("class");

        NumberPicker numPick = findViewById(R.id.NumPick4);

        ok = (Button) findViewById(R.id.button_ok);

        numPick.setMinValue(0);
        numPick.setMaxValue(maxNum);

        numPick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                num = newVal;
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void onClickOk(View view) {
        Intent intent = new Intent(this, SettingsPage.class);
        intent.putExtra("value", num);
        intent.putExtra("class", name);
        startActivity(intent);
    }
}