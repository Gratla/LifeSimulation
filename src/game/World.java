package game;

import datastructures.Vector2D;
import game.graphics.GraphicsData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class World {

    private int width;
    private int height;
    private Landscape landscape;
    private ArrayList<Plant> plants;
    private ArrayList<Creature> creatures;

    private Timeline timeline;
    private int stepRate;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.landscape = new Landscape(width, height);

        this.stepRate = 50;
        initTimeline();

        plants = new ArrayList<>();
        creatures = new ArrayList<>();

        spawnPlants(width*height/10000);
        spawnCreatures(width*height/20000);
    }

    public void initTimeline(){
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(stepRate),
                        event -> processChanges()
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }

    private void processChanges(){
        moveCreatures();
    }

    private void moveCreatures(){
        if(creatures.size() > 0){
            for(Creature creature: creatures){
                creature.move();
                preventBorderCrossing(creature);
            }
        }
    }

    private void preventBorderCrossing(Creature creature){
        if(creature.getPixelPosX() < 0){
            creature.position.set(0, creature.position.y);
        }
        else if(creature.getPixelPosX() > width - creature.getWidth()){
            creature.position.set(width - creature.getWidth(), creature.position.y);
        }

        if(creature.getPixelPosY() < 0){
            creature.position.set(creature.position.x, 0);
        }
        else if(creature.getPixelPosY() > height - creature.getHeight()){
            creature.position.set(creature.position.x, height - creature.getHeight());
        }
    }

    private void spawnPlants(int num){
        for (int i = 0; i < num; i++) {
            int Min = 1;
            int MaxWidth = width;
            int MaxHeight = height;
            plants.add(new Plant(Min + (int)(Math.random() * ((MaxWidth - Min) + 1)),Min + (int)(Math.random() * ((MaxHeight - Min) + 1))));
        }
    }

    private void spawnCreatures(int num){
        for (int i = 0; i < num; i++) {
            int Min = width;
            int MaxWidth = width;
            int MaxHeight = height;
            creatures.add(new Animal(Min + (int)(Math.random() * ((MaxWidth - Min) + 1)),Min + (int)(Math.random() * ((MaxHeight - Min) + 1))));
        }
    }

    public ArrayList<GraphicsData> getAllGraphicsData(){
        ArrayList<GraphicsData> result = new ArrayList<>();

        if(landscape.graphicsChanged){
            result.add(landscape.getGraphicsData());
            landscape.graphicsChanged = false;
        }

        ArrayList<GraphicsData> backgroundData = getBackgroundGraphicsData();

        if(backgroundData.size() > 0){
            result.addAll(backgroundData);
        }

        if(plants.size()>0){
            for(Plant plant: plants){
                if(plant.graphicsChanged){
                    result.add(plant.getGraphicsData());
                    plant.graphicsChanged = false;
                }
            }
        }

        if(creatures.size()>0){
            for(Creature creature: creatures){
                if(creature.graphicsChanged){
                    result.add(creature.getGraphicsData());
                    creature.graphicsChanged = false;
                }
            }
        }

        return result;
    }

    public ArrayList<GraphicsData> getBackgroundGraphicsData(){
        ArrayList<GraphicsData> result = new ArrayList<>();

        if(creatures.size()>0){
            for(Creature creature: creatures){
                if(creature.graphicsChanged){
                    byte[] image = new byte[creature.width * creature.height *4];

                    for (int i = 0; i < creature.width; i++) {
                        for (int j = 0; j < creature.height; j++) {
                            int intValue = landscape.getGroundColor(creature.getPixelOldPosX() + i,creature.getPixelOldPosY() + j);
                            //int intValue = 0xFFFF0000;

                            for (int k = 0; k < 4; k++) {
                                image[i * 4 + j * creature.height * 4 + k] =
                                        (byte)((intValue >>> (k * 8))  & (0x000000FF));
                            }
                        }
                    }
                    result.add(new GraphicsData(creature.getPixelOldPosX(),creature.getPixelOldPosY(), creature.width, creature.height,image));
                    creature.oldPosition = new Vector2D(creature.position);
                }
            }
        }

        return result;
    }
}
