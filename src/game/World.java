package game;

import game.graphics.GraphicsData;

import java.util.ArrayList;

public class World {

    private int width;
    private int height;
    private Landscape landscape;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.landscape = new Landscape(width, height);
    }

    public ArrayList<GraphicsData> getAllGraphicsData(){
        ArrayList<GraphicsData> result = new ArrayList<>();

        result.add(landscape.getGraphicsData());
        return result;
    }
}
