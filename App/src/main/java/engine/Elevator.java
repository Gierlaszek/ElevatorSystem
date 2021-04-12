package engine;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collections;


public class Elevator {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int ID;
    private int position;
    private int direction; //-1 down direction, 1 - up direction
    private boolean breakDown = false; // to expand the system, the ability to turn on the alarm in the elevator
    private int time = 3;
    private final int factor = 1000;
    private boolean door = false; //false - door is closed, true - door is open
    public  ArrayList<Integer> target_floor = new ArrayList<Integer>();

    public Elevator(){
        ID = count.incrementAndGet();
        position = 0;
        direction = 0;
    }

    public void moving(){
        if(door){
            checkDoor();
        }
        else{
            if(position > target_floor.get(0)){            //go down
                moveDown();
                direction = -1;
            }
            else if(position < target_floor.get(0)){       //go up
                moveUp();
                direction = 1;
            }
            else if(position == target_floor.get(0)){       //open door
                checkDoor();
                direction = 0;
                target_floor.remove(0);
            }

        }
    }

    public int getDirection(){
        return direction;
    }

    public int getPosition(){
        return position;
    }


    public void takeTarget(int numOfFloor, int direction){
        //pozycja guesta jest wieksza od pozycji windy to wtedy sortuj rosnaco
        target_floor.add(numOfFloor);
        target_floor = removeDuplicate(target_floor);
        Collections.sort(target_floor);

        if(direction == -1 || numOfFloor < getPosition()){
            Collections.reverse(target_floor);
        }

        System.out.println("Target: " + target_floor);
    }

    private <T> ArrayList<T> removeDuplicate(ArrayList<T> list){
        ArrayList<T> newList = list;
        for(T element : list){
            if(!newList.contains(element)){
                newList.add(element);
            }
        }
        return newList;
    }

    private void moveUp(){
        position += 1;
        direction = 1;
    }

    private void moveDown(){
        position -= 1;
        direction = -1;
    }

    private void checkDoor(){
        door = !door;
    }



    public void setTime(int _time){
        time = _time;
    }

    @SuppressLint("DefaultLocale")
    public Map<String, Integer> getStatus(){
        Map<String, Integer> status = new HashMap<String, Integer>();
        status.put("ID", ID);
        status.put("position", position);
        if(!target_floor.isEmpty()){
            status.put("target", target_floor.get(0));
        }
        else {
            status.put("target", null);
        }
        status.put("direction", direction);
        return status;
    }


}

