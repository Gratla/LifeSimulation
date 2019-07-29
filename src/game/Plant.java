package game;

import datastructures.Vector2D;

public class Plant extends Creature{

    public Plant(int posX, int posY){
        super(new Vector2D(posX,posY), 5, 5);
        this.graphicsChanged = true;
    }
}
