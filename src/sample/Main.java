/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;D */

package sample;

import Clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dominating Set");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println();
    }

    public static void main(String[] args) {
        //ProgramacionEntera PE = new ProgramacionEntera();
        //PE.EjecutarPython();
        launch(args);
    }
}
