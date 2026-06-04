package actividades;

public class Main {

    static void sep(String titulo) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  " + titulo);
        System.out.println("═".repeat(60));
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════
        // ACTIVIDAD 1.1 – Inserción en árbol B de orden 4
        // Secuencia: 50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5
        // ══════════════════════════════════════════════════════
        sep("ACTIVIDAD 1.1 – Inserción orden 4");

        BNode.resetCounter();
        BTree<Integer> arbol = new BTree<>(4);

        int[] claves = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
        System.out.println("Insertando claves: 50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5\n");

        for (int clave : claves) {
            System.out.println(">> Insertando: " + clave);
            arbol.insert(clave);
            System.out.println(arbol);
            System.out.println("-".repeat(50));
        }

        sep("ÁRBOL FINAL ACTIVIDAD 1.1");
        System.out.println(arbol);

        // ══════════════════════════════════════════════════════
        // ACTIVIDAD 1.2 – Eliminación de claves: 25, 10, 50, 70, 27, 5, 75
        // ══════════════════════════════════════════════════════
        sep("ACTIVIDAD 1.2 – Eliminación de claves");

        int[] aEliminar = {25, 10, 50, 70, 27, 5, 75};
        for (int clave : aEliminar) {
            System.out.println(">> Eliminando: " + clave);
            arbol.remove(clave);
            System.out.println(arbol);
            System.out.println("-".repeat(50));
        }

        // ══════════════════════════════════════════════════════
        // EJERCICIO 01 – search()
        // ══════════════════════════════════════════════════════
        sep("EJERCICIO 01 – search()");

        // Árbol fresco para pruebas de búsqueda
        BNode.resetCounter();
        BTree<Integer> arbol2 = new BTree<>(4);
        for (int v : new int[]{31, 12, 19, 3, 10, 13, 16, 22, 25, 28, 41, 57, 63,
                                33, 35, 40, 49, 52, 55, 60, 62, 67, 70, 72}) {
            arbol2.insert(v);
        }
        System.out.println(arbol2);

        System.out.println("Buscar 52 (existe, en hoja):"); arbol2.search(52);
        System.out.println("Buscar 31 (existe, raíz):"); arbol2.search(31);
        System.out.println("Buscar 3 (existe, hoja extremo izquierdo):"); arbol2.search(3);
        System.out.println("Buscar 72 (existe, hoja extremo derecho):"); arbol2.search(72);
        System.out.println("Buscar 99 (no existe):"); arbol2.search(99);

        // ══════════════════════════════════════════════════════
        // EJERCICIO 02 – searchRange()
        // ══════════════════════════════════════════════════════
        sep("EJERCICIO 02 – searchRange()");

        System.out.println("Rango [20, 60]:");
        arbol2.searchRange(20, 60);

        System.out.println("\nRango [1, 100] (todo el árbol):");
        arbol2.searchRange(1, 100);

        System.out.println("\nRango [90, 100] (inexistente):");
        arbol2.searchRange(90, 100);

        System.out.println("\nRango inválido [60, 20]:");
        arbol2.searchRange(60, 20);

        // ══════════════════════════════════════════════════════
        // EJERCICIO 03 – remove() (pruebas adicionales)
        // ══════════════════════════════════════════════════════
        sep("EJERCICIO 03 – remove() prueba adicional");
        BNode.resetCounter();
        BTree<Integer> arbol3 = new BTree<>(5);
        for (int v : new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}) {
            arbol3.insert(v);
        }
        System.out.println("Árbol antes de eliminar:");
        System.out.println(arbol3);

        System.out.println(">> Eliminando 30 (redistribución):");
        arbol3.remove(30);
        System.out.println(arbol3);

        System.out.println(">> Eliminando 10 (fusión):");
        arbol3.remove(10);
        System.out.println(arbol3);

        System.out.println(">> Eliminando clave inexistente 99:");
        arbol3.remove(99);

        // ══════════════════════════════════════════════════════
        // EJERCICIO 04 – Biblioteca digital
        // ══════════════════════════════════════════════════════
        sep("EJERCICIO 04 – Biblioteca digital");

        BNode.resetCounter();
        Biblioteca bib = new Biblioteca(4);
        bib.agregar(new Libro("9780132350884", "Clean Code",          "Robert Martin", 2008));
        bib.agregar(new Libro("9780134494166", "Clean Architecture",  "Robert Martin", 2017));
        bib.agregar(new Libro("9780201633610", "Design Patterns",     "GoF",           1994));
        bib.agregar(new Libro("9780596009205", "Head First Java",     "Kathy Sierra",  2005));
        bib.agregar(new Libro("9780137081073", "The Pragmatic Programmer", "Hunt & Thomas", 2019));

        System.out.println("\n--- Estructura del árbol ---");
        bib.mostrarLibros();

        System.out.println("--- Buscar por ISBN existente ---");
        bib.buscarPorISBN("9780201633610");

        System.out.println("\n--- Buscar por ISBN inexistente ---");
        bib.buscarPorISBN("0000000000000");

        System.out.println("\n--- Eliminar un libro ---");
        bib.eliminar("9780596009205");
        bib.mostrarTotal();
        bib.mostrarAltura();
        bib.mostrarLibros();

        System.out.println("\n¿Por qué un Árbol B es adecuado para bases de datos / bibliotecas?");
        System.out.println("""
            - Acceso O(log n) incluso con millones de registros.
            - Los nodos grandes reducen la cantidad de accesos a disco (I/O).
            - Todas las hojas al mismo nivel garantizan tiempos predecibles.
            - La división y fusión mantienen el árbol balanceado automáticamente.
            - El ISBN ordenado permite búsquedas por rango (ej. todos de un año).
        """);
    }
}
