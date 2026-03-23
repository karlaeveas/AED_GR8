package PROJECTOLAB2;

import java.util.ArrayList;

public class Cajoneria<T> {
    private ArrayList<Caja<T>> cajas;
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
        this.cajas = new ArrayList<>();
    }

    public void addCaja(Caja<T> caja) {
        if (cajas.size() < tope) {
            cajas.add(caja);
        } else {
            throw new RuntimeException("No caben más cajas");
        }
    }

    public String search(T elemento) {
        for (int i = 0; i < cajas.size(); i++) {
            Caja<T> caja = cajas.get(i);
            if (caja.contains(elemento)) {
                return "Elemento encontrado en posición: " + i +
                        ", color de caja: " + caja.getColor();
            }
        }
        return "Elemento no encontrado";
    }

    public T delete(T elemento) {
        for (Caja<T> caja : cajas) {
            if (caja.remove(elemento)) {
                return elemento;
            }
        }
        return null;
    }

    public int count(T elemento) {
        int total = 0;
        for (Caja<T> caja : cajas) {
            total += caja.count(elemento);
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cajas.size(); i++) {
            Caja<T> caja = cajas.get(i);

            for (T obj : caja.getObjetos()) {
                sb.append("Posición: ").append(i)
                        .append(" Color: ").append(caja.getColor())
                        .append(" Objeto: ").append(obj.toString())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}
