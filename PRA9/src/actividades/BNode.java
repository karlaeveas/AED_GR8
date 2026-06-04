package actividades;

import java.util.ArrayList;

/**
 * Clase BNode - Representa un nodo del Árbol B.
 * Cada nodo contiene un arreglo de claves y un arreglo de hijos.
 */
public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    // Identificador único y autoincremental para cada nodo
    private static int nodeCounter = 0;
    private int idNode;

    /**
     * Constructor: inicializa un nodo con capacidad para 'n' claves y 'n+1' hijos.
     * @param n orden del árbol (número máximo de hijos)
     */
    public BNode(int n) {
        this.idNode = ++nodeCounter;
        this.keys   = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n + 1);
        this.count  = 0;
        // Rellenar con nulls para poder usar set() directamente
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
        }
        for (int i = 0; i <= n; i++) {
            this.childs.add(null);
        }
    }

    /** Reinicia el contador global (útil para pruebas). */
    public static void resetCounter() {
        nodeCounter = 0;
    }

    /** Devuelve el identificador del nodo. */
    public int getIdNode() {
        return idNode;
    }

    /**
     * Verifica si el nodo está lleno.
     * @param maxKeys número máximo de claves (orden - 1)
     * @return true si count == maxKeys
     */
    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;
    }

    /**
     * Verifica si el nodo está vacío.
     * @return true si count == 0
     */
    public boolean nodeEmpty() {
        return this.count == 0;
    }

    /**
     * Busca una clave en el nodo actual.
     * Si la encuentra devuelve true y pos[0] contiene el índice de la clave.
     * Si no la encuentra devuelve false y pos[0] contiene el índice del hijo
     * por el que se debe descender.
     *
     * @param cl  clave a buscar
     * @param pos arreglo de un elemento que recibe la posición resultante
     * @return true si la clave se encontró en este nodo
     */
    @SuppressWarnings("unchecked")
    public boolean searchNode(E cl, int[] pos) {
        Comparable<E> key = (Comparable<E>) cl;
        int i = 0;
        // Avanzar mientras la clave actual sea menor que cl
        while (i < this.count && key.compareTo(this.keys.get(i)) > 0) {
            i++;
        }
        if (i < this.count && key.compareTo(this.keys.get(i)) == 0) {
            // Clave encontrada en la posición i
            pos[0] = i;
            return true;
        }
        // No encontrada: pos[0] es el índice del hijo por el que descender
        pos[0] = i;
        return false;
    }

    /**
     * Retorna una representación del nodo con su id y sus claves.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodo[").append(idNode).append("](");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}