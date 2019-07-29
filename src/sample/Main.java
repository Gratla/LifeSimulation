package sample;

import game.graphics.GraphicsController;
import game.World;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 750;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Group root = new Group();
        root.getStyleClass().add("container");
        World world = new World(WINDOW_WIDTH,WINDOW_HEIGHT);
        GraphicsController graphicsController = new GraphicsController(primaryStage, root, world);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
