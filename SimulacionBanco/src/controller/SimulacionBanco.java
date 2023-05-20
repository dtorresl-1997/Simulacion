package controller;

import java.util.Random;

import model.Cajero;
import model.Cliente;
import model.Cola;
import view.Vista;

class SimulacionBanco {
    private static final String[] TIPOS_TRANSACCIONES = { "Retiros", "Consignaciones", "Pagos", "Expedición de cheques de gerencia" };

    private int numCajeros;
    private int horaInicio;
    private int horaFin;
    private int numIteraciones;
    private int[] clientesPorHora = { 5, 10, 3 };
    private int[][] tiemposAtencionPorTransaccion = { { 3, 5, 8, 12 }, { 5, 8, 12, 3 }, { 8, 12, 3, 5 }, { 12, 3, 5, 8 } };

    private int[] clientesAtendidosPorFranja;
    private int[][] transaccionesAtendidasTotales;
    private int[][] tiempoAtencionPorTransaccionYCajero;

    public SimulacionBanco(int numCajeros, int horaInicio, int horaFin, int numIteraciones) {
        this.numCajeros = numCajeros;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.numIteraciones = numIteraciones;
        this.clientesAtendidosPorFranja = new int[clientesPorHora.length];
        this.transaccionesAtendidasTotales = new int[TIPOS_TRANSACCIONES.length][numCajeros];
        this.tiempoAtencionPorTransaccionYCajero = new int[TIPOS_TRANSACCIONES.length][numCajeros];
    }

    private Cliente generarCliente() {
        Random random = new Random();
        int id = random.nextInt(1000) + 1;
        int numTransacciones = random.nextInt(3) + 1;
        int[] transacciones = new int[numTransacciones];
        for (int i = 0; i < numTransacciones; i++) {
            transacciones[i] = random.nextInt(4);
        }
        return new Cliente(id, transacciones);
    }

    private int seleccionarCajero() {
        Random random = new Random();
        return random.nextInt(numCajeros) + 1;
    }

    private void procesarTransacciones(Cliente cliente, Cajero cajero) {
        int idCajero = cajero.getId();
        int[] tiemposAtencion = cajero.getTiemposAtencion();
        int[] transacciones = cliente.getTransacciones();

        for (int transaccion : transacciones) {
            int tiempoAtencion = tiemposAtencion[transaccion];
            transaccionesAtendidasTotales[transaccion][idCajero - 1]++;
            tiempoAtencionPorTransaccionYCajero[transaccion][idCajero - 1] += tiempoAtencion;
        }
        clientesAtendidosPorFranja[idCajero - 1]++;
    }

    public void simular() {
        for (int iteracion = 0; iteracion < numIteraciones; iteracion++) {
            System.out.println("Iteración: " + (iteracion + 1));
            System.out.println("----------------------------");

            for (int hora = horaInicio; hora <= horaFin; hora++) {
                int franjaHoraria = (hora >= 8 && hora <= 12) ? 0 : ((hora > 12 && hora <= 14) ? 1 : 2);
                int numClientes = clientesPorHora[franjaHoraria];

                System.out.println("Hora: " + hora + ":00 - " + (hora + 1) + ":00");
                System.out.println("Número de clientes: " + numClientes);
                System.out.println("----------------------------");

                Cola[] colasCajeros = new Cola[numCajeros];
                for (int i = 0; i < numCajeros; i++) {
                    colasCajeros[i] = new Cola();
                }

                for (int i = 0; i < numClientes; i++) {
                    Cliente cliente = generarCliente();
                    int cajero = seleccionarCajero();
                    colasCajeros[cajero - 1].encolar(cliente);
                }

                for (int i = 0; i < numCajeros; i++) {
                    Cajero cajero = new Cajero(i + 1, tiemposAtencionPorTransaccion[i]);
                    Cola colaClientes = colasCajeros[i];

                    while (!colaClientes.estaVacia()) {
                        Cliente cliente = colaClientes.desencolar();
                        procesarTransacciones(cliente, cajero);
                    }
                }

               // System.out.println("Clientes atendidos: " + clientesAtendidosPorFranja[franjaHoraria]);
                System.out.println();
            }

            System.out.println("----------------------------");
            System.out.println();
        }
        Vista.mostrarEstadisticasFinales(clientesAtendidosPorFranja, TIPOS_TRANSACCIONES, transaccionesAtendidasTotales, tiempoAtencionPorTransaccionYCajero);


       
    }
}