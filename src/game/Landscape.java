package game;

import game.graphics.GraphicsData;

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

    public int getGroundColor(int x, int y){
        if(x > width - 1){
            x = width - 1;
        }
        else if(x < 0 ){
            x = 0;
        }

        if(y > height - 1){
            y = height - 1;
        }
        else if (y < 0){
            y = 0;
        }

        if(ground[x][y] == Biomes.Normal){
            return 0xFF40FF40;
        }
        else{
            return -1;
        }
    }

    public GraphicsData getGraphicsData(){
        byte[] image = new byte[width * height * 4];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int intValue = getGroundColor(i, j);

                for (int k = 0; k < 4; k++) {
                    image[i * 4 + j * width * 4 + k] =
                            (byte)((intValue >>> (k * 8))  & (0x000000FF));
                }
            }
        }

        graphicsData = new GraphicsData(0,0, width, height, image);
        return graphicsData;
    }
}
