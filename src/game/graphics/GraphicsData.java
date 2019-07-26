package game.graphics;

import javafx.scene.paint.Color;

public class GraphicsData {
    public int posX, posY;
    public Color[][] image;

    public GraphicsData(int posX, int posY, Color[][]  image){
        this.posX = posX;
        this.posY = posY;
        this.image = image;
    }
}
