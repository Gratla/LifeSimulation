package game;

import game.graphics.GraphicsData;
import javafx.scene.paint.Color;

public class Plant extends Creature{

    public Plant(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.width = 5;
        this.height = 5;
        this.graphicsChanged = true;
    }

    @Override
    public GraphicsData getGraphicsData(){
        Color[][] image = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image[i][j] = Color.DARKGREEN;
            }
        }
        return new GraphicsData(posX,posY,image);
    }
}
