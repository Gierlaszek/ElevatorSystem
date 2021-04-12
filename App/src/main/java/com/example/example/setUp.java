package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.MyAdapter;
import Adapter.MyAdapterGuest;
import engine.system;

public class setUp extends AppCompatActivity {

    private ArrayList<HashMap<String, Integer>> ListGuest;
    SharedPreferences preferences;
    private HashMap<String, Integer> guest = new HashMap<String, Integer>();
    private static final int REQUEST_CODE_EXAMPLE = 0x9988;
    private MyAdapterGuest adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        if(ListGuest == null) {
            ListGuest = new ArrayList<HashMap<String, Integer>>();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        adapter = new MyAdapterGuest(ListGuest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // First we need to check if the requestCode matches the one we used.
        if(requestCode == REQUEST_CODE_EXAMPLE) {

            // The resultCode is set by the AnotherActivity
            // By convention RESULT_OK means that what ever
            // AnotherActivity did was successful
            if(resultCode == Activity.RESULT_OK) {
                // Get the result from the returned Intent
                int target = data.getIntExtra("Target", 0);
                int position = data.getIntExtra("Position", 0);
                HashMap<String, Integer> guest = new HashMap<String, Integer>();
                guest.put("Target", target);
                guest.put("Position", position);
                ListGuest.add(guest);
                adapter.notifyDataSetChanged();
            } else {
                // AnotherActivity was not successful. No data to retrieve.
            }
        }
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(setUp.this, newGuest.class);
        startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
    }

    public void onClickDelete(){
        //TODO po nacisnieciu kolesia w tabeli mozna go usunac lub edytowac ? - ale to tez jak zostanie czas

    }

    public void onClickStart(View view){
        preferences = getSharedPreferences("Shared", MODE_PRIVATE);
        int numOfEle = preferences.getInt("ELEVATOR", 1);

        Intent intent = new Intent(this, Simulation.class);
        intent.putExtra("ListGuest", ListGuest);
        intent.putExtra("system", "start");
        startActivity(intent);

    }

}