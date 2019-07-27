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
    private boolean flag;

    public GraphicsController(Stage primaryStage, Group root, World world) throws IOException {

        this.frameRate = 1;
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
        loadGraphicsData();

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.clearRect(0,0, canvas.getWidth(),canvas.getHeight());

        WritableImage image = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
        image.getPixelWriter().setPixels(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight(), PixelFormat.getByteBgraInstance(), imageInArray, 0, (int)canvas.getHeight() * 4);
        graphicsContext.drawImage(image, 0, 0);
    }

    private void loadGraphicsData(){

        allGraphicsData = world.getAllGraphicsData();
        for(GraphicsData graphicsData: allGraphicsData) {
            for (int i = 0; i < graphicsData.width; i++) {
                for (int j = 0; j < graphicsData.height; j++) {
                    for (int k = 0; k < 4; k++) {
                        imageInArray[(i+graphicsData.posX) * 4 + (j+graphicsData.posY) * graphicsData.height * 4 + k] = (byte) ((graphicsData.image[i * 4 + j * graphicsData.height * 4 + k] >>> (k * 8))  & (0x000000FF));
                    }

                    /*if(graphicsData.image[i][j] != null){
                        pixelWriter.setColor(graphicsData.posX + i, graphicsData.posY + j, graphicsData.image[i][j]);
                    }*/
                }
            }
        }
    }




}
