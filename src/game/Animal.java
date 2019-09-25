package game;

import datastructures.Vector2D;


public class Animal extends Creature {

    Animal(int posX, int posY){
        super(new Vector2D(posX,posY), 2, 2);
        setProperties();
    }

    Animal(Vector2D position){
        super(position, 2, 2);
        setProperties();
    }

    public Creature createCreature(Vector2D position, int width, int height){
        return new Animal(position);
    }

    private void setProperties(){
        DNAProperty[][] adultProperties = new DNAProperty[width][height];
        DNAProperty[][] childProperties = new DNAProperty[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                adultProperties[i][j] = new DNAMovement();
            }
        }
        adultProperties[0][0] = new DNAEyes();
        adultProperties[0][1] = new DNATeeth();

        childProperties[1][0] = new DNAMovement();
        childProperties[1][1] = new DNAMovement();

        dna.setAdultProperties(adultProperties);
        dna.setChildProperties(childProperties);
        dna.copyChildProperties();
    }
}
