package Clases;

/*CLASE PARA GENERAR UN GRAFO MEDIANTE EL MODELO DE GILERT ;)*/

import javafx.util.Pair;

import java.util.*;

public class Grafo {
    private Double Probabilidad;
    private Integer NumeroVertices;
    private Vector<Vector<Integer>> Grafo;
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
                        this.Grafo.get(j).add(i);

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
}
