package sample;

import Clases.Grafo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        System.out.println();
    }

    /*MINIMUM CONNECTED DOMINATING SET*/
    public static void main(String[] args) {
        Grafo G = new Grafo(5,0.5);
        launch(args);
    }
}
