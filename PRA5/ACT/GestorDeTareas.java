package actividad;

public class GestorDeTareas<T> {
    private ListLinked<T> lista;

    public GestorDeTareas() { lista = new ListLinked<>(); }

    public void agregarTarea(T tarea) { lista.insertLast(tarea); }
    public boolean eliminarTarea(T tarea) { return lista.removeNode(tarea); }
    public boolean contieneTarea(T tarea) { return lista.search(tarea); }

    public void imprimirTareas() {
        lista.print();
    }
    public int contarTareas() {
        return lista.length();
    }
    public void invertirTareas() {
        lista.reverse();
    }
    public T obtenerTareaMasPrioritaria() {
        Nodo<T> current = lista.getHead();
        if (current == null) return null;

        T min = current.dato;

        while (current != null) {
            if (current.dato instanceof Tarea) {
                Tarea actual = (Tarea) current.dato;
                Tarea minimo = (Tarea) min;

                if (actual.compareTo(minimo) < 0) {
                    min = current.dato;
                }
            }
            current = current.enlace;
        }
        return min;
    }
    public Nodo<T> getHead() {
        return lista.getHead();
    }
}
