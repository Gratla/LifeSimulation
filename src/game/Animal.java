package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;
import javafx.scene.paint.Color;

public class Animal extends Creature {

    private double speed;   //speed <= 1 && speed >= 0
    private double deltaMove;

    private Vector2D direction;

    public Animal(int posX, int posY){
        this.oldPosition = new Vector2D(posX,posY);
        this.position = new Vector2D(posX,posY);
        this.width = 1;
        this.height = 1;
        this.graphicsChanged = true;

        this.direction = new Vector2D(Math.random()-0.5, Math.random()-0.5);
        this.direction.normalize();

        this.speed = 0.4;
        this.deltaMove = 1;
    }

    public void move(){
        deltaMove -= speed;

        if(deltaMove < 0){
            deltaMove += 1;
            position.add(direction);
            graphicsChanged = true;
        }
    }

    @Override
    public GraphicsData getGraphicsData(){
        Color[][] image = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image[i][j] = Color.BLACK;
            }
        }
        return new GraphicsData(getPixelPosX(),getPixelPosY(),image);
    }
}
