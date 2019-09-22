package game;

import datastructures.Vector2D;

class Plant extends Creature{

    Plant(int posX, int posY){
        super(new Vector2D(posX,posY), 5, 5);
        setProperties();
        this.graphicsChanged = true;
    }

    private void setProperties(){
        DNAProperty[][] properties = new DNAProperty[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(i < width/4 || i > 3*width/4 || j < height/4 || j > 3*height/4){
                    properties[i][j] = new DNALeaf();
                }
                else{
                    properties[i][j] = new DNAWood();
                }
            }
        }
        dna.setAdultProperties(properties);
        dna.setChildProperties(properties);
        dna.copyChildProperties();
    }
}
