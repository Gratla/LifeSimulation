package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;

public abstract class Creature {

    private static int numberOfCreatures = 0;

    private int id;
    Vector2D oldPosition;
    Vector2D position;
    Mind mind;
    DNA dna;
    int width, height;
    double mutationRate;

    private boolean dead;

    public Creature(Vector2D position, int width, int height){

        this.id = ++numberOfCreatures;
        this.position = new Vector2D(position);
        this.oldPosition = new Vector2D(position);
        this.dna = new DNA(this, width,height);
        this.width = width;
        this.height = height;
        this.mutationRate = 0.03;
        this.mind = new Mind(this);
        this.dead = false;
    }

    public Creature createChild(Creature partner){
        return null;
    }

    public Creature createCreature(Vector2D position, int width, int height){
        return null;
    }

    public void underAttack(double damage){
        dna.deleteRandomProperty();
        dead = dna.isEmpty();
    }

    public Creature reproduce(){
        return mind.tryReporduction();
    }

    public boolean isDead(){
        return dead;
    }

    GraphicsData getGraphicsData(){
        byte[] image = new byte[width * height * 4];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int intValue = dna.getColor(i,j);

                for (int k = 0; k < 4; k++) {
                    image[i * 4 + j * height * 4 + k] =
                            (byte)((intValue >>> (k * 8))  & (0x000000FF));
                }
            }
        }
        return new GraphicsData(getPixelPosX(),getPixelPosY(), width, height,image);
    }

    int getId(){
        return id;
    }

    public Vector2D getOldPosition(){
        return oldPosition;
    }

    int getWidth(){
        return width;
    }

    int getHeight(){
        return height;
    }

    DNA getDna(){
        return dna;
    }

    void processDNA(){
        if(!dna.isAdult()){
            dna.grow();
        }
        dna.useProperties(this);
    }

    void think(DistanceManager distanceManager){
        mind.think(distanceManager);
    }

    int getPixelPosX(){
        return (int)Math.round(position.x);
    }

    int getPixelPosY(){
        return (int)Math.round(position.y);
    }

    int getPixelOldPosX(){
        return (int)Math.round(oldPosition.x);
    }

    int getPixelOldPosY(){
        return (int)Math.round(oldPosition.y);
    }

    double distance(Creature creature){
        double deltaX = this.position.x - creature.position.x;
        double deltaY = this.position.y - creature.position.y;
        return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    }


}
