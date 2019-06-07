/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;D */

package sample;

import Clases.Draw;
import Clases.Grafo;
import Clases.Greedy;
import Clases.ProgramacionLineal;
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
        launch(args);
    }
}
