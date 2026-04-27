
class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListLinked<T> {
    protected Nodo<T> cabeza;

    public ListLinked() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void mostrar() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}

class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {

    public void insertOrden(T x) {
        Nodo<T> nuevo = new Nodo<>(x);

        if (cabeza == null || x.compareTo(cabeza.dato) < 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            return;
        }

        Nodo<T> actual = cabeza;

        while (actual.siguiente != null &&
                x.compareTo(actual.siguiente.dato) > 0) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }
}

// Clase Tarea para probar prioridad
class Tarea implements Comparable<Tarea> {
    String nombre;
    int prioridad;

    public Tarea(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Tarea otra) {
        return this.prioridad - otra.prioridad;
    }

    @Override
    public String toString() {
        return nombre + "(Prioridad: " + prioridad + ")";
    }
}

// Clase principal
public class Ejercicio7 {
    public static void main(String[] args) {

        SortedListLinked<Tarea> lista = new SortedListLinked<>();

        lista.insertOrden(new Tarea("Comer", 3));
        lista.insertOrden(new Tarea("Hacer tarea", 1));
        lista.insertOrden(new Tarea("Jugar", 5));
        lista.insertOrden(new Tarea("Estudiar", 2));

        System.out.println("Tareas ordenadas por prioridad:");
        lista.mostrar();
    }
}