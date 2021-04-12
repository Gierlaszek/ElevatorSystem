package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class system {
    public  ArrayList<Elevator> allElevator = new ArrayList<Elevator>();

    public system(int numOfElevator){
        for(int i = 0; i < numOfElevator; i++){
            allElevator.add(new Elevator());
        }
    }

    public Elevator pickup(int numOfFloor, int direction){

        Map<Elevator, Integer> nearestElevator = new HashMap<>();

        for(int i = 0; i < allElevator.size(); i++){
            int distance = numOfFloor - allElevator.get(i).getPosition();
            if(distance < 0){
                distance = -distance;
            }
            nearestElevator.put(allElevator.get(i), distance);
            if(allElevator.get(i).getDirection() != direction){
                if(allElevator.get(i).getDirection() != 0){
                    nearestElevator.remove(allElevator.get(i));
                }
            }
            //jezeli pozycja windy jest na mniejszym poziomie niz guesta to zeby nie przyjezdzala
            if(numOfFloor < allElevator.get(i).getPosition() && direction == 1){
                if(allElevator.get(i).getDirection() != 0){
                    nearestElevator.remove(allElevator.get(i));
                }
            }

            if(numOfFloor > allElevator.get(i).getPosition() && direction == -1){
                if(allElevator.get(i).getDirection() != 0){
                    nearestElevator.remove(allElevator.get(i));
                }
            }
        }

        //map sorting, the first element with the shortest distance is the elevator that goes to the guest
        List<Integer> dinstanceElevator = new ArrayList<>(nearestElevator.values());
        Collections.sort(dinstanceElevator);

        Elevator nearest = getKey(nearestElevator, dinstanceElevator.get(0));//the small distance
        //jezeli nie ma zadnej najblizszej windy to wtedy musi zwrocic cos innego, np null i zeby zaraz sprawdzic czy moze wziac winde
        return  nearest;
    }

    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
