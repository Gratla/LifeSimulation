package game.graphics;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import game.World;
import javafx.stage.Window;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphicsController {

    private int frameRate;
    private double unitSize;
    private int width;
    private int height;
    private byte[] imageInArray;

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private PixelWriter pixelWriter;
    private Timeline timeline;
    private World world;
    private ArrayList<GraphicsData> allGraphicsData;

    public GraphicsController(Stage primaryStage, Group root, World world) throws IOException {

        this.frameRate = 15;
        unitSize = 1;
        this.width = Main.WINDOW_WIDTH;
        this.height = Main.WINDOW_HEIGHT;
        imageInArray = new byte[width * height * 4];

        canvas = new Canvas(width,height);
        graphicsContext = canvas.getGraphicsContext2D();
        pixelWriter = graphicsContext.getPixelWriter();
        this.world = world;

        root.getChildren().add(canvas);
        primaryStage.setTitle("Life Simulation");

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initTimeline();

        primaryStage.show();
    }

    public void initTimeline(){
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000/frameRate),
                        event -> draw()
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }

    private void draw(){
        loadGraphicsData();

        WritableImage image = new WritableImage(width, height);
        image.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), imageInArray, 0, width * 4);
        graphicsContext.drawImage(image, 0, 0);
    }

    private void loadGraphicsData(){
        boolean drawBackground = false;
        allGraphicsData = world.getAllGraphicsData();
        for(GraphicsData graphicsData: allGraphicsData) {
            for (int i = 0; i < graphicsData.width; i++) {
                for (int j = 0; j < graphicsData.height; j++) {
                    for (int k = 0; k < 4; k++) {
                        if(unitSize*(i+graphicsData.posX) < width && unitSize*(j+graphicsData.posY) < height && unitSize*(i+graphicsData.posX) > 0 && unitSize*(j+graphicsData.posY) > 0){
                            if(k == 0 && graphicsData.image[i * 4 + j * graphicsData.width * 4 + 3] == 0){
                                drawBackground = true;
                            }

                            if(!drawBackground){
                                imageInArray[(int)((unitSize*(i+graphicsData.posX)) * 4 + (unitSize*(j+graphicsData.posY)) * width * 4 + k)] = graphicsData.image[i * 4 + j * graphicsData.width * 4 + k];
                            }
                            else{
                                //imageInArray[(int)((unitSize*(i+graphicsData.posX)) * 4 + (unitSize*(j+graphicsData.posY)) * width * 4 + k)] = (byte)(world.getGroundColor(i,j)<<k*2);
                                imageInArray[(int)((unitSize*(i+graphicsData.posX)) * 4 + (unitSize*(j+graphicsData.posY)) * width * 4 + k)] = (byte)((world.getGroundColor(i,j) >>> (k * 8))  & (0x000000FF));
                            }

                        }
                    }
                    drawBackground = false;
                }
            }
        }
    }




}
