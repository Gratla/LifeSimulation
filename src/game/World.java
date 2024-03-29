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
    private ArrayList<Creature> creatures;
    private DistanceManager distanceManager;

    private Timeline timeline;
    private int stepRate;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.landscape = new Landscape(width, height);

        this.stepRate = 10;
        initTimeline();

        creatures = new ArrayList<>();

        spawnPlants(width*height/10000);
        spawnCreatures(width*height/20000);

        distanceManager = new DistanceManager(creatures);
    }

    private void initTimeline(){
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
        ArrayList<Creature> deadCreatures = new ArrayList<>();
        ArrayList<Creature> children = new ArrayList<>();
        if(!creatures.isEmpty()){
            for(Creature creature: creatures){
                if(creature.isDead()){
                    deadCreatures.add(creature);
                }
                else{
                    creature.think(distanceManager);
                    creature.processDNA();
                    Creature child = creature.reproduce();
                    if(child != null){
                        children.add(child);
                    }
                    preventBorderCrossing(creature);
                }
            }
        }
        if(!deadCreatures.isEmpty()){
            for(Creature creature: deadCreatures){
                creatures.remove(creature);
            }
        }

        if(!children.isEmpty()){
            creatures.addAll(children);
            distanceManager.addCreatures(children);
        }
        distanceManager.recalculateDistances(creatures);
    }

    private void preventBorderCrossing(Creature creature){
        if(creature.getPixelPosX() < 1){
            creature.position.set(1, creature.position.y);
        }
        else if(creature.getPixelPosX() > width - creature.getWidth()){
            creature.position.set(width - creature.getWidth(), creature.position.y);
        }

        if(creature.getPixelPosY() < 1){
            creature.position.set(creature.position.x, 1);
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
            creatures.add(new Plant(Min + (int)(Math.random() * ((MaxWidth - Min) + 1)),Min + (int)(Math.random() * ((MaxHeight - Min) + 1))));
        }
    }

    private void spawnCreatures(int num){
        for (int i = 0; i < num; i++) {
            int Min = 1;
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

        if(creatures.size()>0){
            for(Creature creature: creatures){
                result.add(creature.getGraphicsData());
            }
        }

        return result;
    }

    public ArrayList<GraphicsData> getBackgroundGraphicsData(){
        ArrayList<GraphicsData> result = new ArrayList<>();

        if(creatures.size()>0){
            for(Creature creature: creatures){
                    byte[] image = new byte[creature.width * creature.height *4];

                    for (int i = 0; i < creature.width; i++) {
                        for (int j = 0; j < creature.height; j++) {
                            int intValue = landscape.getGroundColor(creature.getPixelOldPosX() + i,creature.getPixelOldPosY() + j);

                            for (int k = 0; k < 4; k++) {
                                image[j * creature.width * 4 + i * 4 + k] =
                                        (byte)((intValue >>> (k * 8))  & (0x000000FF));
                            }
                        }
                    }
                    result.add(new GraphicsData(creature.getPixelOldPosX(),creature.getPixelOldPosY(), creature.width, creature.height,image));
                    creature.oldPosition = new Vector2D(creature.position);
            }
        }

        return result;
    }

    public int getGroundColor(int x, int y){
        return landscape.getGroundColor(x,y);
    }

    public String getWorldInfo(){
        String output = "";
        output += Creature.getCreaturesInfo();

        if(!creatures.isEmpty()){
            for(Creature creature: creatures){
                output += creature.getCreatureInfo();
            }
        }

        return output;
    }
}
