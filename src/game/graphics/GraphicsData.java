package game.graphics;

import javafx.scene.paint.Color;

public class GraphicsData {
    public int posX, posY;
    public int width, height;
    public byte[] image;

    public GraphicsData(int posX, int posY, int width, int height, byte[]  image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
    }
}
