package actividades;


import java.io.*;
import java.nio.file.*;

/**
 * Clase Biblioteca - Ejercicio 04.
 * Administra una colección de libros usando un BTree<Libro>.
 */
public class Biblioteca {
    private BTree<Libro> arbol;

    public Biblioteca(int orden) {
        this.arbol = new BTree<>(orden);
    }

    /** Agrega un libro al árbol. */
    public void agregar(Libro libro) {
        arbol.insert(libro);
    }

    /** Busca un libro por ISBN y muestra el camino. */
    public void buscarPorISBN(String isbn) {
        Libro buscado = new Libro(isbn, "", "", 0);
        boolean encontrado = arbol.search(buscado);
        if (!encontrado)
            System.out.println("Libro con ISBN " + isbn + " no encontrado.");
    }

    /** Elimina un libro por ISBN. */
    public void eliminar(String isbn) {
        Libro aEliminar = new Libro(isbn, "", "", 0);
        arbol.remove(aEliminar);
    }

    /** Muestra todos los libros ordenados por ISBN (recorrido in-order). */
    public void mostrarLibros() {
        System.out.println(arbol.toString());
    }

    /** Muestra la altura del árbol. */
    public void mostrarAltura() {
        System.out.println("Altura del árbol B: " + arbol.height());
    }

    /** Muestra la cantidad total de libros. */
    public void mostrarTotal() {
        System.out.println("Total de libros: " + arbol.totalKeys());
    }

    /**
     * Carga libros desde un archivo de texto.
     * Formato: primera línea = orden, siguientes = ISBN,titulo,autor,año
     */
    public static Biblioteca cargarDesdeArchivo(String rutaArchivo) {
        Biblioteca biblioteca = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            int orden = Integer.parseInt(br.readLine().trim());
            biblioteca = new Biblioteca(orden);
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;
                String isbn   = partes[0].trim();
                String titulo = partes[1].trim();
                String autor  = partes[2].trim();
                int    anio   = Integer.parseInt(partes[3].trim());
                biblioteca.agregar(new Libro(isbn, titulo, autor, anio));
            }
            br.close();
            System.out.println("Biblioteca cargada exitosamente desde: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return biblioteca;
    }
}