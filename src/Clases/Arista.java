package Clases;

public class Arista {

    /*VALORES QUE NECESITAMOS PARA MANEJAR LA ARISTA*/
    private Integer Origen;
    private Integer Destino;
    private Double Peso;
    private boolean Color; //0 negro,1  para Greedy

    /*CONSTRUCTOR*/
    public Arista() {
    }

    /*CONSTRUCTOR SOBRECARGADO*/
    public Arista(Integer origen, Integer destino, Double peso) {
        Origen = origen;
        Destino = destino;
        Peso = peso;
    }

    /*SETTERS AND GETTERS*/
    public Integer getOrigen() {
        return Origen;
    }

    public void setOrigen(Integer origen) {
        Origen = origen;
    }

    public Integer getDestino() {
        return Destino;
    }

    public void setDestino(Integer destino) {
        Destino = destino;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double peso) {
        Peso = peso;
    }

    public boolean isColor() {
        return Color;
    }

    public void setColor(boolean color) {
        Color = color;
    }
}
