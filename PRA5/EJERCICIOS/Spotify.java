import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Cancion {
    private String titulo;
    private String artista;
    private int duracionSeg;

    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracionSeg() {
        return duracionSeg;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracionSeg + "s)";
    }
}

class NodoDoble<T> {
    T dato;
    NodoDoble<T> next;
    NodoDoble<T> prev;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.next = null;
        this.prev = null;
    }
}

class ColaReproduccion<T extends Cancion> {
    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;
    private NodoDoble<T> actual;

    public ColaReproduccion() {
        inicio = fin = actual = null;
    }

    public void agregarCancion(T cancion) {
        NodoDoble<T> nuevo = new NodoDoble<>(cancion);

        if (inicio == null) {
            inicio = fin = actual = nuevo;
        } else {
            fin.next = nuevo;
            nuevo.prev = fin;
            fin = nuevo;
        }
    }

    public T reproducirSiguiente() {
        if (actual != null && actual.next != null) {
            actual = actual.next;
        }
        return actual.dato;
    }

    public T reproducirAnterior() {
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
        }
        return actual.dato;
    }

    public void mezclar() {
        List<T> lista = new ArrayList<>();
        NodoDoble<T> aux = inicio;

        while (aux != null) {
            lista.add(aux.dato);
            aux = aux.next;
        }

        Collections.shuffle(lista);

        inicio = fin = actual = null;

        for (T cancion : lista) {
            agregarCancion(cancion);
        }
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

// Clase principal
public class Spotify {
    public static void main(String[] args) {

        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();

        cola.agregarCancion(new Cancion("Rhapsody", "Queen", 354));
        cola.agregarCancion(new Cancion("La empiezo a odiar", "Rio", 200));
        cola.agregarCancion(new Cancion("Lady Hear me tonight", "Modjo", 234));
        cola.agregarCancion(new Cancion("Suave", "Luis Miguel", 203));
        cola.agregarCancion(new Cancion("Bad Guy", "Billie Eilish", 194));
        cola.agregarCancion(new Cancion("Boogie Nights", "Heatwave", 109));

        System.out.println("Cola de Reproducción Inicial");
        cola.mostrarCola();

        System.out.println("Avanzando 3 canciones:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Reproduciendo ahora: " + cola.reproducirSiguiente());
        }

        System.out.println("Retrocediendo 1 canción:");
        System.out.println("Anterior: " + cola.reproducirAnterior());

        System.out.println("Mezclando Cola");
        cola.mezclar();
        cola.mostrarCola();

        int total = cola.duracionTotal();
        int min = total / 60;
        int seg = total % 60;

        System.out.printf("Duración total: %02d:%02d", min, seg);
    }
}