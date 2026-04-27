package spotify;

public class ColaReproduccion<T extends Cancion> {

    private NodoDoble<T> inicio; //primera canción
    private NodoDoble<T> fin;    //ultima
    private NodoDoble<T> actual; //cancion en reproduccion

    public ColaReproduccion() {
        inicio = fin = actual = null;  //lista vacia
    }

    public void agregarCancion(T cancion) {  //agrega la cancion al final 
        NodoDoble<T> nuevo = new NodoDoble<>(cancion);  

        if (inicio == null) {  
            inicio = fin = actual = nuevo;
        } else {
            fin.next = nuevo;  //apunta al nuevo
            nuevo.prev = fin;  //nuevo apunta hacia atras 
            fin = nuevo; //actualiza
        }
    }

    public T reproducirSiguiente() {
        if (actual != null && actual.next != null) { //lista? siguiente cancion?
            actual = actual.next; //avanza una posicion
        }
        return actual.dato;  //cancion actual 
    }

    public T reproducirAnterior() {
        if (actual != null && actual.prev != null) { //lista? anterior cancion?
            actual = actual.prev;  //retrocede una posicion
        }
        return actual.dato; //cancion actual 
    }
 
    public void mezclar() {  //reorganiza aleatoriamente los nodos de la lista
        int n = size(); //cantidad de nodos

        for (int i = 0; i < n; i++) {  //recorre la lista 
            int j = (int) (Math.random() * n);

            NodoDoble<T> nodoI = getNodo(i);
            NodoDoble<T> nodoJ = getNodo(j);

            T temp = nodoI.dato;
            nodoI.dato = nodoJ.dato;
            nodoJ.dato = temp;
        }

        actual = inicio;
    }

    public int size() {
        int count = 0;
        NodoDoble<T> aux = inicio;

        while (aux != null) {
            count++;
            aux = aux.next;
        }

        return count;
    }

    public NodoDoble<T> getNodo(int index) {
        NodoDoble<T> aux = inicio;
        int i = 0;

        while (aux != null && i < index) {
            aux = aux.next;
            i++;
        }

        return aux;  //Devuelve el nodo encontrado
    }

    public void mostrarCola() {
        NodoDoble<T> aux = inicio;
        int i = 1;

        while (aux != null) {
            String marca = (aux == actual) ? " <-- Actual" : "";
            System.out.println(i + ". " + aux.dato + marca);
            aux = aux.next;
            i++;
        }
    }

    public int duracionTotal() {
        int suma = 0;
        NodoDoble<T> aux = inicio;

        while (aux != null) {
            suma += aux.dato.getDuracionSeg();
            aux = aux.next;
        }

        return suma;
    }
}
