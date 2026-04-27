package spotify;

public class NodoDoble<T> {
    T dato;   //cancion 
    NodoDoble<T> next;  //siguiente
    NodoDoble<T> prev;  //anterior 

    public NodoDoble(T dato) {
        this.dato = dato;
        this.next = null;
        this.prev = null;
    }
}

// [Nuevo Nodo]
//dato = Cancion
//next = null
//prev = null