package game;

import datastructures.Vector2D;

import java.util.ArrayList;

public class Mind {
    private Creature creature;
    private Vector2D desiredPosition;

    Mind(Creature creature){
        this.creature = creature;
        this.desiredPosition = new Vector2D(1,1);
    }

    public void think(DistanceManager distanceManager){
        int numberOfEyesDNA = creature.getDna().getNumberOfDNAProperties(DNAEyes.class);
        if(numberOfEyesDNA > 0){
            ArrayList<Creature> nearestNeighbors = distanceManager.getAllCreaturesInRadius(creature, numberOfEyesDNA*10);

            if(!nearestNeighbors.isEmpty()){
                desiredPosition = new Vector2D(nearestNeighbors.get(0).position);
            }
            else {
                desiredPosition = null;
            }
        }
    }

    public Vector2D getDesiredPosition(){
        return desiredPosition;
    }
}
