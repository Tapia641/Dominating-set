package sample;

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

    /*MINIMUM CONNECTED DOMINATING SET*/
    public static void main(String[] args) {
        Grafo G = new Grafo(100,0.5);
        Greedy Gre = new Greedy(G.getPesos(),G.getGrafo());
        Gre.Iniciar();
        G.ExportarArchivo(Gre.getNodosSolucion(),Gre.getTotal());
        ProgramacionLineal PL = new ProgramacionLineal();
        PL.EjecutarPython();
        launch(args);
    }
}
