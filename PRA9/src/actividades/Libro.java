package actividades;

/**
 * Clase Libro - Ejercicio 04.
 * Se compara por ISBN para ser usada en el BTree.
 */
public class Libro implements Comparable<Libro> {
    private String isbn;
    private String titulo;
    private String autor;
    private int    anio;

    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn   = isbn;
        this.titulo = titulo;
        this.autor  = autor;
        this.anio   = anio;
    }

    @Override
    public int compareTo(Libro otro) {
        return this.isbn.compareTo(otro.isbn);
    }

    public String getIsbn()   { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor()  { return autor; }
    public int    getAnio()   { return anio; }

    @Override
    public String toString() {
        return "ISBN:" + isbn + " | " + titulo + " | " + autor + " | " + anio;
    }
}