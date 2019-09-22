package game;

import datastructures.Vector2D;

import java.util.ArrayList;

public class Mind {
    private Creature creature;
    private Vector2D desiredPosition;
    private Vector2D idleDirection;
    private Creature target;

    private int thinkCounter;

    private int rethinkIdleDirectionLimit;

    Mind(Creature creature){
        this.creature = creature;
        this.desiredPosition = new Vector2D(1,1);
        this.idleDirection = new Vector2D(1,1);

        this.thinkCounter = 0;
        this.rethinkIdleDirectionLimit = 50;
    }

    public void think(DistanceManager distanceManager){
        int numberOfEyesDNA = creature.getDna().getNumberOfDNAProperties(DNAEyes.class);

        if(thinkCounter%rethinkIdleDirectionLimit == 0){
            idleDirection = getNewIdleDirection();
        }

        if(numberOfEyesDNA > 0){
            ArrayList<Creature> nearestNeighbors = distanceManager.getAllCreaturesInRadius(creature, numberOfEyesDNA*10);

            if(!nearestNeighbors.isEmpty()){
                desiredPosition = new Vector2D(nearestNeighbors.get(0).position);
            }
            else {
                desiredPosition = new Vector2D(Vector2D.add(creature.position,idleDirection));
            }
        }
        else{
            desiredPosition = new Vector2D(Vector2D.add(creature.position,idleDirection));
        }

        int numberOfTeethDNA = creature.getDna().getNumberOfDNAProperties(DNATeeth.class);
        if(numberOfTeethDNA > 0){
            ArrayList<Creature> nearestNeighbors = distanceManager.getAllCreaturesInRadius(creature, 7);

            if(!nearestNeighbors.isEmpty()){
                target = nearestNeighbors.get(0);
            }
            else {
                target = null;
            }
        }

        thinkCounter++;
    }

    private Vector2D getNewIdleDirection() {
        double angle = Math.random() * Math.PI * 2;
        return Vector2D.toCartesian(1, angle);
    }

    public Vector2D getDesiredPosition(){
        return desiredPosition;
    }

    public Creature getTarget(){
        return target;
    }
}
