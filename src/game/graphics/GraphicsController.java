package game.graphics;

import game.Menu;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private byte[] imageInArray;
    private boolean menuActive;

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private PixelWriter pixelWriter;
    private Timeline timeline;
    private World world;
    private ArrayList<GraphicsData> allGraphicsData;
    private Menu menu;

    public GraphicsController(Stage primaryStage, Group root, World world) throws IOException {

        this.frameRate = 30;
        unitSize = 1;
        this.width = Main.WINDOW_WIDTH;
        this.height = Main.WINDOW_HEIGHT;
        imageInArray = new byte[width * height * 4];

        canvas = new Canvas(width,height);
        graphicsContext = canvas.getGraphicsContext2D();
        pixelWriter = graphicsContext.getPixelWriter();
        this.world = world;

        this.menu = new Menu(world);

        root.getChildren().add(canvas);
        primaryStage.setTitle("Life Simulation");

        Scene scene = new Scene(root, width, height);
        setInputHandler(primaryStage, scene);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initTimeline();

        primaryStage.show();
    }

    private void setInputHandler(Stage primaryStage, Scene scene) {
        KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);
        Stage infoStage = new Stage();
        infoStage.initModality(Modality.NONE);
        infoStage.initOwner(primaryStage);

        scene.setOnKeyPressed(event -> {
            if(esc.match(event)){
                if(menuActive){
                    menuActive = false;
                    infoStage.hide();
                }
                else{
                    VBox infoVBox = new VBox(20);
                    infoVBox.getChildren().add(new Text("Test"));
                    Scene infoScene = new Scene(infoVBox, 300, 200);
                    infoScene.setOnKeyPressed(keyEvent -> {
                        if(esc.match(keyEvent)){
                            menuActive = false;
                            infoStage.hide();
                        }
                    });
                    infoStage.setScene(infoScene);
                    infoStage.show();
                    menuActive = true;
                }
            }
        });
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

        if(menuActive){
            menu.draw();
        }
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
