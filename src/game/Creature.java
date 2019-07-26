package game;

import game.graphics.GraphicsData;

public abstract class Creature {
    protected int posX, posY;
    protected int width, height;
    protected int health;

    public boolean graphicsChanged;

    public GraphicsData getGraphicsData(){
        return null;
    }
}
