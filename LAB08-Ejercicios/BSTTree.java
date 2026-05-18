package com.estructura.tickets;

public class BSTTree {
    public static class BSTNodo {
        public int id;
        public BSTNodo izquierdo, derecho;
        public BSTNodo(int id) { this.id = id; }
    }

    public BSTNodo raiz;

    public void insertar(int id) {
        raiz = insertarRec(raiz, id);
    }

    private BSTNodo insertarRec(BSTNodo nodo, int id) {
        if (nodo == null) return new BSTNodo(id);
        if (id < nodo.id) nodo.izquierdo = insertarRec(nodo.izquierdo, id);
        else if (id > nodo.id) nodo.derecho = insertarRec(nodo.derecho, id);
        return nodo;
    }

    public int obtenerAltura() { return calcularAltura(raiz); }
    private int calcularAltura(BSTNodo nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(calcularAltura(nodo.izquierdo), calcularAltura(nodo.derecho));
    }

    public void inOrden() { inOrdenRec(raiz); System.out.println(); }
    private void inOrdenRec(BSTNodo nodo) {
        if (nodo != null) { inOrdenRec(nodo.izquierdo); System.out.print(nodo.id + " "); inOrdenRec(nodo.derecho); }
    }
}
