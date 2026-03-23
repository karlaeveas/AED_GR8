package PROJECTOLAB2;

import java.util.ArrayList;

public class Caja<T> {
    private String color;
    private ArrayList<T> objetos;

    public Caja(String color) {
        this.color = color;
        this.objetos = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public void add(T obj) {
        objetos.add(obj);
    }

    public boolean remove(T obj) {
        return objetos.remove(obj);
    }

    public boolean contains(T obj) {
        return objetos.contains(obj);
    }

    public int count(T obj) {
        int contador = 0;
        for (T item : objetos) {
            if (item.equals(obj)) {
                contador++;
            }
        }
        return contador;
    }

    public ArrayList<T> getObjetos() {
        return objetos;
    }
}
