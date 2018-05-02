package sample;

import com.sun.glass.ui.Screen;
import events.ExplosionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, new ExplosionHandler());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, javafx.stage.Screen.getPrimary().getVisualBounds().getMaxX(), javafx.stage.Screen.getPrimary().getVisualBounds().getMaxY()));
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
