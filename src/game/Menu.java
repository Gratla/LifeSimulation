package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Menu {
    private World world;
    private Stage infoStage;
    private Scene infoScene;
    private VBox infoVBox;

    private boolean menuActive;

    public Menu(Stage primaryStage, World world){
        this.world = world;
        initInfoStage(primaryStage);
        menuActive = false;
    }

    private void initInfoStage(Stage primaryStage){
        KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);

        infoStage = new Stage();
        infoStage.initModality(Modality.NONE);
        infoStage.initOwner(primaryStage);
        infoVBox = new VBox(20);
        infoVBox.getChildren().add(new Text(world.getWorldInfo()));
        infoScene = new Scene(infoVBox, 300, 200);
        infoScene.setOnKeyPressed(keyEvent -> {
            if(esc.match(keyEvent)){
                menuActive = false;
                infoStage.hide();
            }
        });
        infoStage.setScene(infoScene);
    }

    public void update(){
        KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);
        infoVBox = new VBox(20);
        infoVBox.getChildren().add(new Text(world.getWorldInfo()));
        infoScene = new Scene(infoVBox, 300, 200);
        infoScene.setOnKeyPressed(keyEvent -> {
            if(esc.match(keyEvent)){
                menuActive = false;
                infoStage.hide();
            }
        });
        infoStage.setScene(infoScene);
    }

    public boolean isActive(){
        return menuActive;
    }

    public void hide(){
        menuActive = false;
        infoStage.hide();
    }

    public void show(){
        menuActive = true;
        infoStage.show();
    }
}
