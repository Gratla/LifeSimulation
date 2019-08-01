package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DistanceManager {
    private HashMap<Integer,TreeMap<Double,Creature>> nearestNeighbors;

    public DistanceManager(ArrayList<Creature> creatures){
        nearestNeighbors = new HashMap<>();
        addCreatures(creatures);
        System.out.println(toString());
    }

    private void addCreatures(ArrayList<Creature> creatures){
        for(Creature creature: creatures){
            nearestNeighbors.put(creature.getId(), new TreeMap<>());
        }
        recalculateDistances(creatures);
    }

    public void recalculateDistances(ArrayList<Creature> creatures){
        for(Creature currentCreature: creatures){
            TreeMap<Double, Creature> currentTree = nearestNeighbors.get(currentCreature.getId());
            currentTree.clear();

            for(Creature creature: creatures){
                if(creature.getId() != currentCreature.getId()){
                    currentTree.put(currentCreature.distance(creature), creature);
                }
            }
        }
    }

    @Override
    public String toString(){
        String output = "{";

        for(Map.Entry<Integer, TreeMap<Double, Creature>> entry : nearestNeighbors.entrySet()) {
            int key = entry.getKey();
            TreeMap<Double, Creature> value = entry.getValue();
            output += key + "[";

            for(Map.Entry<Double,Creature> tree : value.entrySet()) {
                Double treeKey = tree.getKey();
                Creature treeValue = tree.getValue();
                output += treeKey + ",";
                //System.out.println(key + " => " + value);
            }

            output += "],\n";
        }

        output += "}";
        return output;
    }
}
