package sample;

import game.graphics.GraphicsController;
import game.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Group root = new Group();
        World world = new World(600,600);
        GraphicsController graphicsController = new GraphicsController(primaryStage, root, world);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
