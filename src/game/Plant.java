package game;

import datastructures.Vector2D;

public class Plant extends Creature{

    public Plant(int posX, int posY){
        super(new Vector2D(posX,posY), 5, 5);
        setProperties();
        this.graphicsChanged = true;
    }

    private void setProperties(){
        DNAProperty[][] properties = new DNAProperty[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                properties[i][j] = new DNAWood();
            }
        }
        dna.setProperties(properties);
    }
}
