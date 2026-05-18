package com.estructura.tickets;

public class Nodo {
    public int id; // ID del ticket o clave
    public int altura;
    public Nodo izquierdo;
    public Nodo derecho;

    public Nodo(int id) {
        this.id = id;
        this.altura = 1; // Todos los nodos nuevos inician con altura 1
    }
}
