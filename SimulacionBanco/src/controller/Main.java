package controller;


public class Main {
    public static void main(String[] args) {
        int numCajeros = 2;
        int horaInicio = 8;
        int horaFin = 16;
        int numIteraciones = 1;

        SimulacionBanco simulacion = new SimulacionBanco(numCajeros, horaInicio, horaFin, numIteraciones);
        simulacion.simular();
    }
}
