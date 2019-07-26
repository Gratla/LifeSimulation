package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;

public abstract class Creature {

    protected Vector2D oldPosition;
    protected Vector2D position;
    protected int width, height;
    protected int health;

    public boolean graphicsChanged;

    public GraphicsData getGraphicsData(){
        return null;
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

    public void move(){}

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
