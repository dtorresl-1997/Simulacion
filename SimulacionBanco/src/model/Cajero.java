package model;

public class Cajero {
    private int id;
    private int[] tiemposAtencion;

    public Cajero(int id, int[] tiemposAtencion) {
        this.id = id;
        this.tiemposAtencion = tiemposAtencion;
    }

    public int getId() {
        return id;
    }

    public int[] getTiemposAtencion() {
        return tiemposAtencion;
    }
}
