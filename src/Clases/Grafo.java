package Clases;

/*CLASE PARA GENERAR UN GRAFO MEDIANTE EL MODELO DE GILERT ;)*/

import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Grafo {
    private Double Probabilidad;
    private Integer NumeroVertices;
    private Vector<Vector<Integer>> Grafo, GrafoTabla;
    private Vector<Pair<Integer, Double>> Pesos;

    /*CONSTRUCTOR*/
    public Grafo() {
    }

    /*CONSTRUCTOR SOBRECARGADO*/
    public Grafo(Integer NumeroVertices, Double Probabilidad) {
        this.NumeroVertices = NumeroVertices;
        this.Probabilidad = Probabilidad;
        Grafo = new Vector<>();
        Pesos = new Vector<>();
        GrafoTabla = new Vector<>();
        InicializarGrafo();
    }

    /*MODELO DE GILBERT*/
    public void InicializarGrafo() {

        /*ESTABLECEMOS EL NUMERO DE VERTICES*/
        for (int i = 0; i < this.NumeroVertices; i++) {
            Grafo.add(new Vector<>());
            Grafo.get(i).add(i);
        }

        /*PROBABILIDAD DE GENERAR UNA ARISTA*/
        Random RN = new Random();

        for (int i = 0; i < this.Grafo.size(); i++) {
            for (int j = 0; j < this.Grafo.size(); j++) {

                /*CONDICIÓN DE PROBABILIDAD*/
                if (i != j && RN.nextDouble() <= this.Probabilidad) {

                    /*SI NO EXISTE CONEXION ENTE I,J*/
                    if (!Grafo.get(i).contains(j) && !Grafo.get(j).contains(i)) {

                        /*LOS CONECTAMOS*/
                        this.Grafo.get(i).add(j);
                        //this.Grafo.get(j).add(i);

                    }
                }
            }
        }


        /*AGREGAMOS PESOS A LOS NODOS DE FORMA ALEATORIA*/
        for (Vector<Integer> V : this.Grafo) {
            Pesos.add(new Pair(V.get(0), Math.round(new Random().nextDouble() * 100d) / 100d));
        }

        /*IMPRIMIMOS PARA VER LOS RESULTADOS*/
        ImprimeGrafo();

    }

    public Vector<Vector<Integer>> getGrafoTabla(){
        Vector<Integer> Aux1 = new Vector<>();
        Aux1.add(0);

        for (int i = 0; i < NumeroVertices; i++) {
            Aux1.add(i);
        }
        GrafoTabla.add(Aux1);

        for (int i = 0; i < NumeroVertices; i++) {
            Vector<Integer> Aux = new Vector<>();
            Aux.add(i);
            for (int j = 0; j < NumeroVertices; j++) {
                if (Grafo.get(i).contains(j) && i!=j){
                    Aux.add(1);
                }else{
                    Aux.add(-1);
                }
            }
            GrafoTabla.add(Aux);
        }

        for (Vector<Integer> i : GrafoTabla){
            System.err.println(i);
        }

        return GrafoTabla;
    }

    /*IMPRIMIMOS*/
    public void ImprimeGrafo() {

        /*GRAFO SIN PESOS*/
        System.out.println("\nGrafo:");
        for (Vector<Integer> V : this.Grafo) {
            System.out.println(V);
        }

        /*GRAFO CON PESOS*/
        System.out.println("\nPesos de cada Vertice:");
        int i = 0;
        for (Pair<Integer, Double> P : this.Pesos) {
            System.out.println(Pesos.get(i++));
        }
        System.out.println();

    }


    public void ResolverLinealRedondeo() {

    }


    public void ResolverEntera() {

    }

    public Double getProbabilidad() {
        return Probabilidad;
    }

    public void setProbabilidad(Double probabilidad) {
        Probabilidad = probabilidad;
    }

    public Integer getNumeroVertices() {
        return NumeroVertices;
    }

    public void setNumeroVertices(Integer numeroVertices) {
        NumeroVertices = numeroVertices;
    }

    public Vector<Vector<Integer>> getGrafo() {
        return Grafo;
    }

    public void setGrafo(Vector<Vector<Integer>> grafo) {
        Grafo = grafo;
    }

    public Vector<Pair<Integer, Double>> getPesos() {
        return Pesos;
    }

    public void setPesos(Vector<Pair<Integer, Double>> pesos) {
        Pesos = pesos;
    }

    public void ExportarArchivo(Vector<Integer> Solucion, double total){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            File archivo = new File("");
            System.out.println(archivo.getAbsolutePath());
            fichero = new FileWriter(archivo.getAbsolutePath() + "\\src\\Python\\PL.txt");
            pw = new PrintWriter(fichero);

            //Guardamos los nodos dominantes
            for (int i: Solucion){
                pw.print(i + " ");
            }
            pw.println(total);

            for (Vector<Integer> V: Grafo){
                for (int i: V){
                    pw.print(i + " ");
                }
                pw.println(Pesos.get(V.get(0)).getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    public String DibujaGrafo() {
        String cadena = "";

        /* FORMATO DEL DIBUJO */
        cadena += "node [shape=circle];\n" +
                "node [style=filled];\n" +
                "node [fillcolor=\"#EEEEEE\"];\n" +
                "node [color=\"#EEEEEE\"];\n" +
                "edge [color=\"#31CEF0\"];\n";

        /* ESTRUCTURA PARA .DOT */
        //"EstadoOrigen -> EstadoDestino [label=\"Transicion\"];\n"

        /* IMPRIMIMOS LOS ESTADOS DE ACEPTACIÓN ACCEDIENDO AL HASHSET Y SACANDO CADA ESTADO */
        for (Vector<Integer> V : Grafo){
            for (int i = 1 ; i < V.size(); i++){
                cadena += (V.get(0) + " -> " + V.get(i) +";\n");
            }
        }

        /* FIN DE FORMATO*/
        cadena += "rankdir=LR;\n";

        return cadena;
    }
}
