package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.MyAdapter;
import engine.Elevator;
import engine.system;



public class Simulation extends AppCompatActivity {

    SharedPreferences preferences;
    private ArrayList<HashMap<String, Integer>> ListGuest = new ArrayList<HashMap<String, Integer>>();
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private TextView numOfGuest;
    private system sys;
    private  List<Elevator> status = new ArrayList<Elevator>();
    private List<Elevator> nearest = new ArrayList<>();
    private static final int REQUEST_CODE_EXAMPLE = 0x9988;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        preferences = getSharedPreferences("Shared", MODE_PRIVATE);
        int numOfEle = preferences.getInt("ELEVATOR", 1);

        sys = new system(numOfEle);

        ListGuest = (ArrayList<HashMap<String, Integer>>) getIntent().getSerializableExtra("ListGuest");

        if(ListGuest == null) {
            ListGuest = new ArrayList<HashMap<String, Integer>>();
        }

        updateStatus();

        numOfGuest = findViewById(R.id.numOfGuest);
        updateGuest();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyAdapter(status);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        startSimulation(sys);

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
                updateGuest();
                startSimulation(sys);
            } else {
                // AnotherActivity was not successful. No data to retrieve.
            }
        }
    }

    public void updateGuest(){
        numOfGuest.setText("Number of guest: " + String.valueOf(ListGuest.size()));
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(Simulation.this, newGuest.class);
        startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
    }

    public void updateStatus(){
        status.clear();
        for(int i = 0 ; i < sys.allElevator.size(); i++) {
            status.add(sys.allElevator.get(i));
        }
    }

    public void onClickStart(View view){
        move();
    }

    public void startSimulation(system sys){
        int ListGuestSize = ListGuest.size();
        for(int i = 0; i < ListGuestSize; i++){
            System.out.println("GUEST: " + ListGuest);
            int direction = 0;
            if(ListGuest.get(i).get("Position") > ListGuest.get(i).get("Target")){
                direction = -1;
            }
            else if(ListGuest.get(i).get("Position") < ListGuest.get(i).get("Target")){
                direction = 1;
            }
            Elevator near = sys.pickup(ListGuest.get(i).get("Position"), direction);
            near.takeTarget(ListGuest.get(i).get("Position"), direction);
            near.takeTarget(ListGuest.get(i).get("Target"), direction);
            move();
        }
        ListGuest.clear();
    }

    public void onClickGuest(View view){

    }

    public void move(){
        for(int i = 0; i < sys.allElevator.size(); i++) {//sprawdzenie targetu - jesli pusty to zeby winda nie jechala
            if(sys.allElevator.get(i).getStatus().get("target") != null) {
                sys.allElevator.get(i).moving();
                updateGuest();
                updateStatus();
                adapter.notifyDataSetChanged();
            }
        }
    }
}