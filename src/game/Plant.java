package game;

import datastructures.Vector2D;

class Plant extends Creature{

    Plant(int posX, int posY){
        super(new Vector2D(posX,posY), 5, 5);
        setProperties();
    }

    Plant(Vector2D position){
        super(position, 5, 5);
        setProperties();
    }


    public Creature createCreature(Vector2D position, int width, int height){
        return new Plant(position);
    }

    private void setProperties(){
        DNAProperty[][] adultProperties = new DNAProperty[width][height];
        DNAProperty[][] childProperties = new DNAProperty[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(i < width/4 || i > 3*width/4 || j < height/4 || j > 3*height/4){
                    adultProperties[i][j] = new DNALeaf();
                }
                else{
                    adultProperties[i][j] = new DNAWood();
                    childProperties[i][j] = new DNAWood();
                }
            }
        }
        dna.setAdultProperties(adultProperties);
        dna.setChildProperties(childProperties);
        dna.copyChildProperties();
    }
}
