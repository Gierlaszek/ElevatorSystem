package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class SettingsPage extends AppCompatActivity {

    Button prev;
    TextView numOfElevator;
    TextView numOfFloor;
    TextView time;
    SharedPreferences sharedPreferences;
    SharedPreferences preferences;
    private static final int REQUEST_CODE_EXAMPLE = 0x9988;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);

        prev = (Button) findViewById(R.id.prev);

        numOfElevator = findViewById(R.id.value_ele);
        numOfFloor = findViewById(R.id.value_floor);
        time = findViewById(R.id.value_time);

        preferences = getSharedPreferences("Shared", MODE_PRIVATE);
        numOfFloor.setText(String.valueOf(preferences.getInt("FLOOR", 1)));
        numOfElevator.setText(String.valueOf(preferences.getInt("ELEVATOR", 1)));
        time.setText(String.valueOf(preferences.getInt("TIME", 1)));

        Intent intent = getIntent();
        String name = intent.getStringExtra("class");

        if(name != null){
            switch (name) {
                case "Time": {
                    int num = intent.getIntExtra("value", 1);
                    time.setText(String.valueOf(num));
                    break;
                }
                case "Floor": {
                    int num = intent.getIntExtra("value", 1);
                    numOfFloor.setText(String.valueOf(num));
                    break;
                }
                case "Elev": {
                    int num = intent.getIntExtra("value", 1);
                    numOfElevator.setText(String.valueOf(num));
                    break;
                }
            }
        }
    }


    public void onClickTime(View view){
        Intent intent = new Intent(this, PopUp.class);
        intent.putExtra("value", 5);
        intent.putExtra("class", "Time");
        sharedValue();
        startActivity(intent);
    }

    public void onClickFloor(View view){
        Intent intent = new Intent(this, PopUp.class);
        intent.putExtra("value", 20);
        intent.putExtra("class", "Floor");
        sharedValue();
        startActivity(intent);
    }

    public void onClickElev(View view){
        Intent intent = new Intent(this, PopUp.class);
        intent.putExtra("value", 16);
        intent.putExtra("class", "Elev");
        sharedValue();
        startActivity(intent);
    }

    private void sharedValue(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("ELEVATOR", Integer.parseInt(numOfElevator.getText().toString()));
        edit.putInt("FLOOR", Integer.parseInt(numOfFloor.getText().toString()));
        edit.putInt("TIME", Integer.parseInt(time.getText().toString()));
        edit.apply();
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        sharedValue();
        startActivity(intent);
    }
}