/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;D */


package sample;

import Clases.Draw;
import Clases.Grafo;
import Clases.Greedy;
import Clases.ProgramacionLineal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private TableView<ObservableList<String>> MiTabla = new TableView<>();

    @FXML
    private JFXTextField CadenaNumeroNodos;

    @FXML
    private JFXTextField CadenaProbabilidad;

    @FXML
    private ImageView ImageViewGrafo;

    private Vector<Vector<Integer>> GrafoGenerado, GrafoTabla;

    private Grafo G;

    @FXML
    private StackPane mySP;

    @FXML
    private void onMouseclickedGenerargrafo(MouseEvent event) {
        if (CadenaNumeroNodos.getText().length() >= 1 && CadenaProbabilidad.getText().length() >= 1) {

            /*LA LIMPIAMOS*/
            MiTabla.getColumns().clear();
            MiTabla.getItems().clear();

            /*LE PASAMOS LOS PARAMETROS AL CONSTRUCTOR*/
            G = new Grafo(Integer.parseInt(CadenaNumeroNodos.getText()), Double.parseDouble(CadenaProbabilidad.getText()));
            GrafoGenerado = G.getGrafo();

            GrafoTabla = G.getGrafoTabla();


            String colString = "";
            for (int i = 0; i < GrafoTabla.get(0).size(); i++) {
                colString += GrafoTabla.get(0).get(i) + "\t";
            }
            String[] aux = colString.split("\t");

            TestDataGenerator dataGenerator = new TestDataGenerator();
            TestDataGenerator.setLOREM(aux);

            List<String> columnNames = dataGenerator.getNext(aux.length);

            for (int i = 0; i < columnNames.size(); i++) {
                final int finalIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(aux[i]);
                column.setMinWidth(100);
                column.setSortable(false);
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
                );
                MiTabla.getColumns().add(column);
            }

            Vector<String> Tabla = new Vector<>();
            String cad1 = "";

            for (int j = 0; j < GrafoTabla.get(1).size(); j++) {
                cad1 += GrafoTabla.get(1).get(j) + "\t";
            }
            Tabla.add(cad1);

            for (int i = 1; i < GrafoTabla.size(); i++) {
                String cad = "";
                for (int j = 0; j < GrafoTabla.get(i).size(); j++) {
                    cad += GrafoTabla.get(i).get(j) + "\t";
                }
                Tabla.add(cad);
            }

            CargarDatos(Tabla, MiTabla);


        } else {
            showAlert("Error", "La cadena de texto está vacia", "", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onMouseclickedDibujarGrafo(MouseEvent event) {
        if (Integer.parseInt(CadenaNumeroNodos.getText()) <= 100) {

            Draw D = new Draw();
            D.Dibuja(G.DibujaGrafo());

            /* SOLUCIÓN A LA RUTA DEL AUTÓMATA */
            Image image = new Image("file:///" + new File("src/Clases/Grafo.png").getAbsolutePath());

            /* CONFIGURACIÓN DE LA IMAGEN A MOSTRAR */
            ImageViewGrafo.setImage(image);
            ImageViewGrafo.setFitWidth(500);
            ImageViewGrafo.setPreserveRatio(true);
            ImageViewGrafo.setSmooth(true);
            ImageViewGrafo.setCache(true);

        } else {
            showAlert("Error", "El grafo es demasiado grande para ser dibujado", "", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onMouseclickedGreedy(MouseEvent event) {
        if (G != null) {
            Greedy Gre = new Greedy(G.getPesos(), GrafoGenerado);
            Gre.Iniciar();
            Vector<Integer> V = Gre.getNodosSolucion();
            String solucion = "";
            for (Integer i : V) {
                solucion += i + "\n";
            }

            alertCreator("Solución de forma Greedy:", String.valueOf(Gre.getTotal()), solucion);
            G.ExportarArchivo(Gre.getNodosSolucion(), Gre.getTotal());

            //G.ExportarArchivo(Gre.getNodosSolucion(), Gre.getTotal());
        } else {
            showAlert("Error", "Primero crea un grafo", "", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onMouseclickedProgramacionLineal(MouseEvent event) {
        if (G != null) {
            ProgramacionLineal PL = new ProgramacionLineal();
            PL.EjecutarPython();
            String solucion = PL.getCadena();

            if (solucion.equals("NO")) {
                alertCreator("Solución en Programación Lineal", "Simplex perdió factibilidad", "");
            } else {

                String[] res = solucion.split("(?=\\])");

                String cad = "";
                res[1] = res[1].replaceAll(Pattern.quote("]"),"");
                solucion = res[1];


                res[0] = res[0].replaceAll("(?=\\.|[\\]]|[\\[])","");
                String[] aux = res[0].split("(?=\\s)");

                for (int j = 0; j < aux.length; j++) {
                    if (!aux[j].equals("0.") && !aux[j].equals("[0.") && !aux[j].equals("0.]") && !aux[j].equals(" 0.")) {
                        cad += j + "\n";
                    }
                }

                alertCreator("Solución en Programacion Lineal:", solucion, cad);
            }


        } else {
            showAlert("Error", "Primero crea un grafo", "", Alert.AlertType.ERROR);
        }
    }


    public void showAlert(String titulo, String middle, String fin, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(middle);
        alert.setContentText(fin);
        alert.showAndWait();
    }

    private static class TestDataGenerator {
        private static String[] LOREM;

        public static void setLOREM(String[] LOREM) {
            TestDataGenerator.LOREM = LOREM;
        }

        public static String[] getLOREM() {
            return LOREM;
        }

        private int curWord = 0;

        List<String> getNext(int nWords) {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < nWords; i++) {
                if (curWord == Integer.MAX_VALUE) {
                    curWord = 0;
                }
                words.add(LOREM[curWord % LOREM.length]);
                curWord++;
            }
            return words;
        }
    }

    public void CargarDatos(Vector<String> T, TableView Tipo) {
        for (int i = 1; i < T.size(); i++) {
            TestDataGenerator dataGenerator = new TestDataGenerator();
            TestDataGenerator.setLOREM(T.get(i).split(Pattern.quote("\t")));
            Tipo.getItems().add(
                    FXCollections.observableArrayList(
                            dataGenerator.getNext(T.size())
                    )
            );
        }
    }

    private void alertCreator(String title, String header, String content) {
        //mySP.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(new Text(header == null ? title : title + "\n" + header));
        dialogContent.setBody(new Text(content));
        JFXButton close = new JFXButton("Aceptar");
        close.getStyleClass().add("JFXButton");
        dialogContent.setActions(close);
        JFXDialog dialog = new JFXDialog(mySP, dialogContent, JFXDialog.DialogTransition.CENTER);
        close.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        dialog.show();
    }
}
