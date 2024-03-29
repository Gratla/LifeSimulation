package game;

import datastructures.Vector2D;


public class Animal extends Creature {

    Animal(int posX, int posY){
        super(new Vector2D(posX,posY), 2, 2);
        setProperties();
    }

    Animal(Vector2D position, int width, int height){
        super(position, width, height);
        setProperties();
    }

    public Creature createChild(Creature partner){
        int childWidth, childHeight;

        childWidth = calcChildWidth(partner);
        childHeight = calcChildHeight(partner);

        if(childWidth < 2)childWidth = 2;
        if(childHeight < 2)childHeight = 2;

        Creature child = new Animal(new Vector2D((position.x + partner.position.x)/2, (position.y + partner.position.y)/2), childWidth, childHeight);

        DNAProperty[][] adultProperties = new DNAProperty[childWidth][childHeight];
        DNAProperty[][] childProperties = new DNAProperty[childWidth][childHeight];

        for (int i = 0; i < childWidth; i++) {
            for (int j = 0; j < childHeight; j++) {
                if(Math.random() < 0.5 && i < getWidth() && j < getHeight()){
                    adultProperties[i][j] = dna.getAdultProperty(i,j);
                    childProperties[i][j] = dna.getChildProperty(i,j);
                }
                else if(i < partner.getWidth() && j < partner.getHeight()){
                    adultProperties[i][j] = partner.dna.getAdultProperty(i,j);
                    childProperties[i][j] = partner.dna.getChildProperty(i,j);
                }

                if(Math.random() < child.mutationRate){
                    DNAProperty mutatedProperty = DNAProperty.getRandomAnimalProperty();
                    adultProperties[i][j] = mutatedProperty;

                    if(Math.random() < 0.3){
                        childProperties[i][j] = mutatedProperty;
                    }
                    else{
                        childProperties[i][j] = null;
                    }
                }
            }
        }
        dna.setAdultProperties(adultProperties);
        dna.setChildProperties(childProperties);
        dna.copyChildProperties();
        return child;
    }

    private int calcChildWidth(Creature partner){
        if(Math.random() < 0.5){
            return (int) (getWidth() + Math.round(-1 + Math.random() * 3));
        }
        else{
            return (int) (partner.getWidth() + Math.round(-1 + Math.random() * 3));
        }
    }

    private int calcChildHeight(Creature partner){
        if(Math.random() < 0.5){
            return (int) (getHeight() + Math.round(-1 + Math.random() * 3));
        }
        else{
            return (int) (partner.getHeight() + Math.round(-1 + Math.random() * 3));
        }
    }

    public Creature createCreature(Vector2D position, int width, int height){
        return new Animal(position, width, height);
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
