package game;

import datastructures.Vector2D;

import java.util.ArrayList;

public class Mind {
    private Creature creature;
    private Vector2D desiredPosition;
    private Vector2D idleDirection;
    private Creature target;
    private Creature partner;

    private int thinkCounter;

    private int rethinkIdleDirectionLimit;
    private int partnerlockCooldown;
    private double reproductionProbability;

    Mind(Creature creature){
        this.creature = creature;
        this.desiredPosition = new Vector2D(1,1);
        this.idleDirection = new Vector2D(1,1);

        this.thinkCounter = 0;
        this.rethinkIdleDirectionLimit = 50;
        this.reproductionProbability = 0.5;
        this.partnerlockCooldown = 0;
    }

    public void think(DistanceManager distanceManager){
        ArrayList<Creature> possiblePartners = distanceManager.getAllCreaturesInRadius(creature, 15);

        if(!possiblePartners.isEmpty() && partnerlockCooldown == 0){
            int partnerIndex = (int)(Math.random() * possiblePartners.size());
            partner = possiblePartners.get(partnerIndex);
            partnerlockCooldown = 1000;
        }
        else if(possiblePartners.isEmpty()){
            partner = null;
        }

        if(thinkCounter%rethinkIdleDirectionLimit == 0){
            idleDirection = getNewIdleDirection();
        }

        int numberOfEyesDNA = creature.getDna().getNumberOfDNAProperties(DNAEyes.class);

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

        if(partnerlockCooldown > 0) partnerlockCooldown--;

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

    public Creature tryReporduction(){
        if(checkPartners(creature, partner) && Math.random() < reproductionProbability){
            Creature child = creature.createChild(partner);
            partnerlockCooldown = 2000;
            partner.mind.partner = null;
            partner = null;

            return child;
        }
        return null;
    }

    public static boolean checkPartners(Creature c1, Creature c2){
        if(c1 != null && c2 != null){
            return c1 == c2.mind.partner && c1.mind.partner == c2;
            //return c1 == c2.mind.partner && c1.mind.partner == c2 && c1.dna.isAdult() && c2.dna.isAdult();
        }

        return false;
    }
}
