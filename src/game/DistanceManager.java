package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DistanceManager {
    private HashMap<Integer,TreeMap<Double,Creature>> nearestNeighbors;

    DistanceManager(ArrayList<Creature> creatures){
        nearestNeighbors = new HashMap<>();
        addCreatures(creatures);
    }

    private void addCreatures(ArrayList<Creature> creatures){
        for(Creature creature: creatures){
            nearestNeighbors.put(creature.getId(), new TreeMap<>());
        }
        recalculateDistances(creatures);
    }

    void recalculateDistances(ArrayList<Creature> creatures){
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

    ArrayList<Creature> getAllCreaturesInRadius(Creature currentCreature, double r){
        ArrayList<Creature> result = new ArrayList<>();
        TreeMap<Double, Creature> currentTree = nearestNeighbors.get(currentCreature.getId());

        for(Map.Entry<Double,Creature> treeEntry : currentTree.entrySet()) {
            Double treeKey = treeEntry.getKey();
            Creature treeValue = treeEntry.getValue();

            if(treeKey <= r){
                result.add(treeValue);
            }
            else {
                break;
            }
        }
        return result;
    }

    @Override
    public String toString(){
        String output = "{";

        for(Map.Entry<Integer, TreeMap<Double, Creature>> entry : nearestNeighbors.entrySet()) {
            int key = entry.getKey();
            TreeMap<Double, Creature> value = entry.getValue();
            output += key + "[";

            for(Map.Entry<Double,Creature> treeEntry : value.entrySet()) {
                Double treeKey = treeEntry.getKey();
                Creature treeValue = treeEntry.getValue();
                output += treeKey + ",";
            }

            output += "],\n";
        }

        output += "}";
        return output;
    }
}
