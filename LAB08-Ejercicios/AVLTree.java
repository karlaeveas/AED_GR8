package com.estructura.tickets;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    public Nodo raiz;

    // Clase interna para registrar el historial de bajas (Punto 3)
    public static class RegistroEliminacion {
        public int clave, nodoDesbalanceado;
        public String casoBST, sucesor, rotacionAplicada;

        public RegistroEliminacion(int clave, String casoBST, String sucesor, int nodoDesbalanceado, String rotacionAplicada) {
            this.clave = clave;
            this.casoBST = casoBST;
            this.sucesor = sucesor;
            this.nodoDesbalanceado = nodoDesbalanceado;
            this.rotacionAplicada = rotacionAplicada;
        }
    }

    public List<RegistroEliminacion> historialEliminaciones = new ArrayList<>();

    private int obtenerAltura(Nodo nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int obtenerFactorEquilibrio(Nodo nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    private void actualizarAltura(Nodo nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    // Rotaciones:
    private Nodo rotarDerecha(Nodo y) {
        System.out.println("   [Rotación] Simple a la Derecha en nodo: " + y.id);
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private Nodo rotarIzquierda(Nodo x) {
        System.out.println("   [Rotación] Simple a la Izquierda en nodo: " + x.id);
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;
        y.derecho = x;
        x.derecho = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    // Inserciones:
    public void insertar(int id) {
        System.out.println("Insertando ticket: " + id);
        raiz = insertarRecursivo(raiz, id);
    }

    private Nodo insertarRecursivo(Nodo nodo, int id) {
        if (nodo == null) return new Nodo(id);

        if (id < nodo.id) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, id);
        } else if (id > nodo.id) {
            nodo.derecho = insertarRecursivo(nodo.derecho, id);
        } else {
            return nodo; // No duplicados
        }

        actualizarAltura(nodo);
        int fe = obtenerFactorEquilibrio(nodo);

        if (fe > 1 && id < nodo.izquierdo.id) return rotarDerecha(nodo);
        if (fe < -1 && id > nodo.derecho.id) return rotarIzquierda(nodo);

        if (fe > 1 && id > nodo.izquierdo.id) {
            System.out.println("   [Balanceo] Doble Izq-Der en: " + nodo.id);
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (fe < -1 && id < nodo.derecho.id) {
            System.out.println("   [Balanceo] Doble Der-Izq en: " + nodo.id);
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    // Eliminacion
    public void eliminar(int id) {
        System.out.println("Eliminando ticket: " + id);
        String[] datosCaso = {"Hoja", "Ninguno", "Ninguna"};
        int[] desbalanceado = {-1};
        raiz = eliminarRecursivo(raiz, id, datosCaso, desbalanceado);
        historialEliminaciones.add(new RegistroEliminacion(id, datosCaso[0], datosCaso[1], desbalanceado[0], datosCaso[2]));
    }

    private Nodo eliminarRecursivo(Nodo nodo, int id, String[] datosCaso, int[] desbalanceado) {
        if (nodo == null) return null;

        if (id < nodo.id) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, id, datosCaso, desbalanceado);
        } else if (id > nodo.id) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, id, datosCaso, desbalanceado);
        } else {
            if (nodo.izquierdo == null && nodo.derecho == null) {
                datosCaso[0] = "Nodo Hoja";
                return null;
            } else if (nodo.izquierdo == null) {
                datosCaso[0] = "Un hijo (Der)";
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                datosCaso[0] = "Un hijo (Izq)";
                return nodo.izquierdo;
            } else {
                datosCaso[0] = "Dos hijos";
                Nodo sucesor = obtenerMenorNodo(nodo.derecho);
                datosCaso[1] = String.valueOf(sucesor.id);
                nodo.id = sucesor.id;
                nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.id, datosCaso, desbalanceado);
            }
        }

        if (nodo == null) return null;

        actualizarAltura(nodo);
        int fe = obtenerFactorEquilibrio(nodo);

        if (fe > 1 && obtenerFactorEquilibrio(nodo.izquierdo) >= 0) {
            desbalanceado[0] = nodo.id; datosCaso[2] = "Simple Derecha";
            return rotarDerecha(nodo);
        }
        if (fe > 1 && obtenerFactorEquilibrio(nodo.izquierdo) < 0) {
            desbalanceado[0] = nodo.id; datosCaso[2] = "Doble Izq-Der";
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (fe < -1 && obtenerFactorEquilibrio(nodo.derecho) <= 0) {
            desbalanceado[0] = nodo.id; datosCaso[2] = "Simple Izquierda";
            return rotarIzquierda(nodo);
        }
        if (fe < -1 && obtenerFactorEquilibrio(nodo.derecho) > 0) {
            desbalanceado[0] = nodo.id; datosCaso[2] = "Doble Der-Izq";
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private Nodo obtenerMenorNodo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) actual = actual.izquierdo;
        return actual;
    }

    //Busqueda
    public boolean buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    private boolean buscarRecursivo(Nodo nodo, int id) {
        if (nodo == null) return false;
        if (id == nodo.id) return true;
        return id < nodo.id ? buscarRecursivo(nodo.izquierdo, id) : buscarRecursivo(nodo.derecho, id);
    }

    //Recorridos:
    public void preOrden() { preOrdenRecursivo(raiz); System.out.println(); }
    private void preOrdenRecursivo(Nodo n) {
        if (n != null) { System.out.print(n.id + " "); preOrdenRecursivo(n.izquierdo); preOrdenRecursivo(n.derecho); }
    }

    public void inOrden() { inOrdenRecursivo(raiz); System.out.println(); }
    private void inOrdenRecursivo(Nodo n) {
        if (n != null) { inOrdenRecursivo(n.izquierdo); System.out.print(n.id + " "); inOrdenRecursivo(n.derecho); }
    }

    // Amplitud recursiva (Puntos 4 y 5)
    public void recorridoAmplitud() {
        int h = obtenerAltura(raiz);
        for (int i = 0; i < h; i++) {
            imprimirNivelEspecifico(raiz, i);
        }
        System.out.println();
    }

    private void imprimirNivelEspecifico(Nodo nodo, int nivelActual) {
        if (nodo == null) return;
        if (nivelActual == 0) {
            System.out.print(nodo.id + " ");
        } else if (nivelActual > 0) {
            imprimirNivelEspecifico(nodo.izquierdo, nivelActual - 1);
            imprimirNivelEspecifico(nodo.derecho, nivelActual - 1);
        }
    }

    public void mostrarArbol() { mostrarArbolRecursivo(raiz, 0); }
    private void mostrarArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) return;
        mostrarArbolRecursivo(nodo.derecho, nivel + 1);
        if (nivel != 0) {
            for (int i = 0; i < nivel - 1; i++) System.out.print("|\t");
            System.out.println("|-------" + nodo.id + " (h:" + nodo.altura + ")");
        } else {
            System.out.println(nodo.id + " (h:" + nodo.altura + ")");
        }
        mostrarArbolRecursivo(nodo.izquierdo, nivel + 1);
    }
}
