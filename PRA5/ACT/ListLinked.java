package actividad;

class ListLinked<T> {
    private Nodo<T> head;

    public ListLinked() {
        head = null;
    }

    public void insertFirst(T x) {   // insertar al inicio
        Nodo<T> newNode = new Nodo<>(x);
        newNode.enlace = head;
        head = newNode;
    }

    public void insertLast(T x) {   // insertar al final
        Nodo<T> newNode = new Nodo<>(x);
        if (head == null) {
            head = newNode;
            return;
        }
        Nodo<T> current = head;
        while (current.enlace != null) {
            current = current.enlace;
        }
        current.enlace = newNode;
    }

    public boolean removeNode(T x) {  // eliminar nodo
        if (head == null) return false;
        if (head.dato.equals(x)) {
            head = head.enlace;
            return true;
        }
        Nodo<T> current = head;
        while (current.enlace != null) {
            if (current.enlace.dato.equals(x)) {
                current.enlace = current.enlace.enlace;
                return true;
            }
            current = current.enlace;
        }
        return false;
    }

    public boolean search(T x) { // buscar
        Nodo<T> current = head;
        while (current != null) {
            if (current.dato.equals(x)) return true;
            current = current.enlace;
        }
        return false;
    }

    public int length() {       // contar elementos
        int count = 0;
        Nodo<T> current = head;
        while (current != null) {
            count++;
            current = current.enlace;
        }
        return count;
    }

    public boolean isEmptyList() { // verificar si está vacía
        return head == null;
    }

    public void print() {     // imprimir lista
        Nodo<T> current = head;
        while (current != null) {
            System.out.println(current.dato);
            current = current.enlace;
        }
    }
    
    public void reverse() {  // invertir lista
        Nodo<T> prev = null;
        Nodo<T> current = head;
        Nodo<T> next;
        while (current != null) {
            next = current.enlace;
            current.enlace = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public Nodo<T> getHead() { // getter para uso interno
        return head;
    }
}
