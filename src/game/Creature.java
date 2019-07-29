package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;

public abstract class Creature {

    protected Vector2D oldPosition;
    protected Vector2D position;
    protected DNA dna;
    protected int width, height;

    public boolean graphicsChanged;

    public Creature(Vector2D position, int width, int height){
        this.position = new Vector2D(position);
        this.oldPosition = new Vector2D(position);
        this.dna = new DNA(width,height);
        this.width = width;
        this.height = height;
    }

    public GraphicsData getGraphicsData(){
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

    public Vector2D getOldPosition(){
        return oldPosition;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void useProperties(){
        dna.useProperties(this);
    }

    public int getPixelPosX(){
        return (int)Math.round(position.x);
    }

    public int getPixelPosY(){
        return (int)Math.round(position.y);
    }

    public int getPixelOldPosX(){
        return (int)Math.round(oldPosition.x);
    }

    public int getPixelOldPosY(){
        return (int)Math.round(oldPosition.y);
    }


}
