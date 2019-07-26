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
        /*allGraphicsData = world.getAllGraphicsData();
        pixelWriter = graphicsContext.getPixelWriter();
        for(GraphicsData graphicsData: allGraphicsData) {
            for (int i = 0; i < graphicsData.image.length; i++) {
                for (int j = 0; j < graphicsData.image[i].length; j++) {
                    if(graphicsData.image[i][j] != null){
                        pixelWriter.setColor(graphicsData.posX + i, graphicsData.posY + j, graphicsData.image[i][j]);
                    }
                }
            }
        }*/

        WritableImage image = new WritableImage(40, 40);
        byte[] imageInArray = new byte[40*40 * 4];

        //Create byte image array
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                int color = i + j;

                int intValue;
                if(flag){
                    flag = false;
                    intValue = 0xFFAAAAAA;
                }
                else{
                    flag=true;
                    intValue = 0xFF555555;
                }

                for (int k = 0; k < 4; k++) {
                    imageInArray[i * 4 + j * 40 * 4 + k] =
                            (byte)((intValue >>> (k * 8))  & (0x000000FF));
                }
            }
        }

        //Copy bytes to image
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.clearRect(0,0, canvas.getWidth(),canvas.getHeight());
        image.getPixelWriter().setPixels(0, 0, 40, 40, PixelFormat.getByteBgraInstance(), imageInArray, 0, 40 * 4);
        graphicsContext.drawImage(image, 10, 10);
        pixelWriter = null;
    }




}
