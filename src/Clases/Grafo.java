package Clases;

/*CLASE PARA GENERAR UN GRAFO MEDIANTE EL MODELO DE GILERT ;)*/

import java.util.Random;
import java.util.Vector;

public class Grafo {
    private Double Probabilidad;
    private Integer NumeroVertices;
    private Vector<Vector<Integer>> Grafo;

    /*CONSTRUCTOR*/
    public Grafo() {
    }

    /*CONSTRUCTOR SOBRECARGADO*/
    public Grafo(Integer NumeroVertices, Double Probabilidad) {
        this.NumeroVertices = NumeroVertices;
        this.Probabilidad = Probabilidad;
        Grafo = new Vector<>();
        InicializarGrafo();
    }

    /*MODELO DE GILBERT*/
    public void InicializarGrafo(){

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
                if (i!=j && RN.nextDouble() <= this.Probabilidad){

                    /*SI NO EXISTE CONEXION ENTE I,J*/
                    if (!Grafo.get(i).contains(j)){

                        /*LOS CONECTAMOS*/
                        this.Grafo.get(i).add(j);

                    }
                }
            }
        }

        /*IMPRIMIMOS PARA VER LOS RESULTADOS*/
        ImprimeGrafo();

    }

    /*IMPRIMIMOS*/
    public void ImprimeGrafo(){
        for (Vector<Integer> V: this.Grafo){
            System.out.println(V);
        }
    }

}
