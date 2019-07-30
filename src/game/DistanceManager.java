package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class DistanceManager {
    private HashMap<Integer,TreeMap<Integer,Creature>> nearestNeighbors;

    public DistanceManager(ArrayList<Creature> creatures){
        nearestNeighbors = new HashMap<>();
        addCreatures(creatures);
        addNearestNeighbors();
    }

    private void addCreatures(ArrayList<Creature> creatures){
        for(Creature creature: creatures){
            nearestNeighbors.put(creature.getId(), new TreeMap<>());
        }
    }

    private void addNearestNeighbors(){

    }
}
