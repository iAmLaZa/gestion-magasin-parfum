package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BD.c();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/1.jpeg")));
        primaryStage.setScene(new Scene(root, 721, 408));
        primaryStage.setTitle("قصر العطور");
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}
