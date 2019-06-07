/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;D */
//INVESTIGADO DE: http://math.mit.edu/~goemans/18434S06/setcover-tamara.pdf

package Clases;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Vector;

public class Greedy {
    private Vector<Pair<Integer, Double>> Pesos;
    private Vector<Vector<Integer>> Grafo;
    private Vector<Integer> NodosSolucion;
    private Double Total;

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public Greedy(Vector<Pair<Integer, Double>> pesos, Vector<Vector<Integer>> grafo) {
        Pesos = pesos;
        Grafo = grafo;
        NodosSolucion = new Vector<>();
    }

    public Greedy() {
    }

    public void Iniciar() {

        /*CONJUNTO DOMINANTE DE PESO MÍNIMO*/
        HashSet<Integer> Original = new HashSet<>(), Solucion = new HashSet<>();
        Vector<Vector<Integer>> Copia = new Vector<>();
        Copia.addAll(this.Grafo);

        for (Vector<Integer> V : Copia) {
            Original.addAll(V);
        }

        double CostoTotal = 0, CostoRestar = 0;

        while (Solucion.size() != Original.size()) {

            double Minimo = 10000;

            /*INDICE PARA MANEJAR EN VEC DE VEC*/
            int P = 0;

            /*ENCUENTRA AL SUBCONJUNTO DE PESO MINIMO*/
            for (int j = 0; j < Copia.size(); j++) {

                /*CALCULAMOS EL COSTO DE MINIMO*/
                double Calcula = Pesos.get(Copia.get(j).get(0)).getValue()/Math.abs((Copia.get(j).size()-CostoRestar));
                //System.out.println("Peso de " + Copia.get(j).get(0) + " es " + Calcula);

                if (Calcula < Minimo && !Solucion.contains(Copia.get(j).get(0))){
                    CostoRestar = Calcula;
                    Minimo = Calcula;
                    P = j;
                }
            }

            Solucion.addAll(Copia.get(P));
            NodosSolucion.add(Copia.get(P).get(0));
            //System.out.println("Se agrega " + Copia.get(P));
            CostoTotal += Pesos.get(Copia.get(P).get(0)).getValue();
            //System.out.println("Solucion con: " + Solucion + " Costo Total: " + CostoTotal);
            Copia.remove(P);


        }

        this.Total = CostoTotal;
        System.err.println("Solucion: " + NodosSolucion + " Total: " + CostoTotal);
    }

    public Vector<Pair<Integer, Double>> getPesos() {
        return Pesos;
    }

    public void setPesos(Vector<Pair<Integer, Double>> pesos) {
        Pesos = pesos;
    }

    public Vector<Vector<Integer>> getGrafo() {
        return Grafo;
    }

    public void setGrafo(Vector<Vector<Integer>> grafo) {
        Grafo = grafo;
    }

    public Vector<Integer> getNodosSolucion() {
        return NodosSolucion;
    }

    public void setNodosSolucion(Vector<Integer> nodosSolucion) {
        NodosSolucion = nodosSolucion;
    }
}
