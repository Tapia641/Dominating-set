package Clases;

/*CLASE PARA GENERAR UN GRAFO MEDIANTE EL MODELO DE GILERT ;)*/

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

public class Grafo {
    private Double Probabilidad;
    private Integer NumeroVertices;
    private Vector<Vector<Integer>> Grafo;
    private Vector<Pair<Integer, Double>> GrafoPesos;

    /*CONSTRUCTOR*/
    public Grafo() {
    }

    /*CONSTRUCTOR SOBRECARGADO*/
    public Grafo(Integer NumeroVertices, Double Probabilidad) {
        this.NumeroVertices = NumeroVertices;
        this.Probabilidad = Probabilidad;
        Grafo = new Vector<>();
        GrafoPesos = new Vector<>();
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
                    if (!Grafo.get(i).contains(j)) {

                        /*LOS CONECTAMOS*/
                        this.Grafo.get(i).add(j);

                    }
                }
            }
        }

        /*AGREGAMOS PESOS A LOS NODOS DE FORMA ALEATORIA*/
        for (Vector<Integer> V : this.Grafo) {
            GrafoPesos.add(new Pair(V.get(0),Math.round(new Random().nextDouble() * 100d) / 100d));
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
        System.out.println("\nGrafo con pesos:");
        int i = 0;
        for (Pair<Integer,Double> P: this.GrafoPesos) {
            System.out.println(GrafoPesos.get(i++));
        }


    }

    public void Greedy() {
        /*CONJUNTO DOMINANTE DE PESO MÍNIMO*/
        //CREAMOS UN GRAFO BIPARTITO
        HashSet<Integer> A = new HashSet<>(), B = new HashSet<>();
        
    }


    public void ResolverLinealRedondeo() {

    }


    public void ResolverEntera() {

    }

}
