package game;

import game.graphics.GraphicsData;

import java.util.ArrayList;

public class World {

    private int width;
    private int height;
    private Landscape landscape;
    private ArrayList<Plant> plants;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.landscape = new Landscape(width, height);

        plants = new ArrayList<>();

        spawnPlants(width*height/10000);
    }

    private void spawnPlants(int num){
        for (int i = 0; i < num; i++) {
            int Min = 1;
            int MaxWidth = width;
            int MaxHeight = height;
            plants.add(new Plant(Min + (int)(Math.random() * ((MaxWidth - Min) + 1)),Min + (int)(Math.random() * ((MaxHeight - Min) + 1))));
        }
    }

    public ArrayList<GraphicsData> getAllGraphicsData(){
        ArrayList<GraphicsData> result = new ArrayList<>();

        if(landscape.graphicsChanged){
            result.add(landscape.getGraphicsData());
            landscape.graphicsChanged = false;
        }

        for(Plant plant: plants){
            result.add(plant.getGraphicsData());
            plant.graphicsChanged = false;
        }

        return result;
    }
}
