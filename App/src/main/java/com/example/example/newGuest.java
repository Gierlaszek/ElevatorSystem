package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class newGuest extends AppCompatActivity {

    SharedPreferences preferences;
    int MaxNum;
    Intent intent;
    ArrayList<HashMap<String, Integer>> ListGuest = new ArrayList<HashMap<String, Integer>>();
    private HashMap<String, Integer> guest = new HashMap<String, Integer>();
    TextView targetValue;
    TextView positionValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guest);

        NumberPicker target = findViewById(R.id.target);
        NumberPicker position = findViewById(R.id.position);

        preferences = getSharedPreferences("Shared", MODE_PRIVATE);
        MaxNum = preferences.getInt("FLOOR", 0);

        targetValue = findViewById(R.id.targetValue);
        positionValue = findViewById(R.id.PositionValue);

        target.setMinValue(0);
        position.setMinValue(0);
        target.setMaxValue(MaxNum);
        position.setMaxValue(MaxNum);


        target.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                targetValue.setText(String.valueOf(newVal));
            }
        });

        position.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                positionValue.setText(String.valueOf(newVal));
            }
        });
    }

    public void onClickOk(View view){
        Intent data = new Intent();
        data.putExtra("Target", Integer.parseInt(targetValue.getText().toString()));
        data.putExtra("Position", Integer.parseInt(positionValue.getText().toString()));
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}