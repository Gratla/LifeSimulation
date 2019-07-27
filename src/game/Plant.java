package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;
import javafx.scene.paint.Color;

public class Plant extends Creature{

    public Plant(int posX, int posY){
        this.position = new Vector2D(posX,posY);
        this.width = 5;
        this.height = 5;
        this.graphicsChanged = true;
    }

    @Override
    public GraphicsData getGraphicsData(){
        byte[] image = new byte[width * height *4];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int intValue = 0xFF009600;

                for (int k = 0; k < 4; k++) {
                    image[i * 4 + j * height * 4 + k] =
                            (byte)((intValue >>> (k * 8))  & (0x000000FF));
                }
            }
        }

        return new GraphicsData(getPixelPosX(),getPixelPosY(), width, height,image);
    }
}
