package game.graphics;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import game.World;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;

public class GraphicsController {

    private int frameRate;
    private double unitSize;
    private int width;
    private int height;

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private PixelWriter pixelWriter;
    private Timeline timeline;
    private World world;
    private ArrayList<GraphicsData> allGraphicsData;

    public GraphicsController(Stage primaryStage, Group root, World world) throws IOException {

        this.frameRate = 10;
        unitSize = 1;
        this.width = Main.WINDOW_WIDTH;
        this.height = Main.WINDOW_HEIGHT;

        canvas = new Canvas(width,height);
        graphicsContext = canvas.getGraphicsContext2D();
        pixelWriter = graphicsContext.getPixelWriter();
        this.world = world;

        root.getChildren().add(canvas);
        primaryStage.setTitle("Life Simulation");
        primaryStage.setScene(new Scene(root, width, height));

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
        allGraphicsData = world.getAllGraphicsData();
        pixelWriter = graphicsContext.getPixelWriter();
        for(GraphicsData graphicsData: allGraphicsData) {
            for (int i = 0; i < graphicsData.image.length; i++) {
                for (int j = 0; j < graphicsData.image[i].length; j++) {
                    if(graphicsData.image[i][j] != null){
                        pixelWriter.setColor(graphicsData.posX + i, graphicsData.posY + j, graphicsData.image[i][j]);
                    }
                }
            }
        }
        pixelWriter = null;
    }




}
