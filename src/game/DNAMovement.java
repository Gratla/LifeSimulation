package game;

import datastructures.Vector2D;

public class DNAMovement extends DNAProperty {

    private Vector2D direction;
    private double speed;
    private double deltaMove;

    public DNAMovement() {
        super(0xFF000000);

        this.direction = new Vector2D(Math.random()-0.5, Math.random()-0.5);
        this.direction.normalize();

        this.speed = 0.9;
        this.deltaMove = 1;
    }

    public void useProperty(Creature creature){
        deltaMove -= speed;

        if(deltaMove < 0){
            deltaMove += 1;
            creature.position.add(direction);
            creature.graphicsChanged = true;
        }
    }
}
