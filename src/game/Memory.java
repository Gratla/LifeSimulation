package game;

import datastructures.Vector2D;

import java.util.ArrayList;

public class Memory {
    private Vector2D position;
    private ArrayList<Creature> creatures;

    public Memory(Vector2D position, ArrayList<Creature> creatures){
        this.position = new Vector2D(position);
        this.creatures = creatures;
    }

    public Memory(Vector2D position, Creature creature){
        this.position = new Vector2D(position);
        this.creatures = new ArrayList<>();
        this.creatures.add(creature);
    }

    public Vector2D getPosition(){
        return position;
    }
}
