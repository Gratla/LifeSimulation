package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;

public abstract class Creature {

    private static int numberOfCreatures = 0;

    private int id;
    Vector2D oldPosition;
    Vector2D position;
    DNA dna;
    int width, height;

    boolean graphicsChanged;

    public Creature(Vector2D position, int width, int height){

        this.id = ++numberOfCreatures;
        this.position = new Vector2D(position);
        this.oldPosition = new Vector2D(position);
        this.dna = new DNA(width,height);
        this.width = width;
        this.height = height;
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

    void useProperties(){
        dna.useProperties(this);
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


}
