package game;

import datastructures.Vector2D;

public class Animal extends Creature {

    private double speed;   //speed <= 1 && speed >= 0
    private double deltaMove;

    private Vector2D direction;

    public Animal(int posX, int posY){
        super(new Vector2D(posX,posY), 1, 1);

        this.graphicsChanged = true;

        this.direction = new Vector2D(Math.random()-0.5, Math.random()-0.5);
        this.direction.normalize();

        this.speed = 0.9;
        this.deltaMove = 1;
    }

    public void move(){
        deltaMove -= speed;

        if(deltaMove < 0){
            deltaMove += 1;
            position.add(direction);
            graphicsChanged = true;
        }
    }
}
