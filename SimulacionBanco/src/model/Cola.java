package model;


public class Cola {
    private Nodo frente;
    private Nodo finalCola;

    private class Nodo {
        private Cliente cliente;
        private Nodo siguiente;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
            this.siguiente = null;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }

    public void encolar(Cliente cliente) {
        Nodo nuevoNodo = new Nodo(cliente);
        if (finalCola == null) {
            frente = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.setSiguiente(nuevoNodo);
            finalCola = nuevoNodo;
        }
    }

    public Cliente desencolar() {
        if (frente == null) {
            return null;
        }
        Cliente clienteDesencolado = frente.getCliente();
        frente = frente.getSiguiente();
        if (frente == null) {
            finalCola = null;
        }
        return clienteDesencolado;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}