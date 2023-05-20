package model;


public class Cliente {
    private int id;
    private int[] transacciones;

    public Cliente(int id, int[] transacciones) {
        this.id = id;
        this.transacciones = transacciones;
    }

    public int getId() {
        return id;
    }

    public int[] getTransacciones() {
        return transacciones;
    }
}
