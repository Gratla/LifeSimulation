package game;

import game.graphics.GraphicsData;
import javafx.scene.paint.Color;

public class Landscape {

    enum Biomes{
        NA,
        Normal,
        Desert,
        Winter
    }

    private int width;
    private int height;
    private Biomes[][] ground;

    private GraphicsData graphicsData;
    public boolean graphicsChanged;

    public Landscape(int width, int height){
        this.width = width;
        this.height = height;
        this.graphicsChanged = true;

        this.ground = new Biomes[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.ground[i][j] = Biomes.Normal;
            }
        }
    }

    public GraphicsData getGraphicsData(){
        Color[][] image = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(ground[i][j] == Biomes.Normal){
                    image[i][j] = Color.LIGHTGREEN;
                }
            }
        }
        graphicsData = new GraphicsData(0,0,image);
        return graphicsData;
    }
}
