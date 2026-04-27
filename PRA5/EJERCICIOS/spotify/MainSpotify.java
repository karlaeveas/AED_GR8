package spotify;

public class MainSpotify {
    public static void main(String[] args) {

        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();

        cola.agregarCancion(new Cancion("It's star", "Benny Bellson", 219));
        cola.agregarCancion(new Cancion("Black Hole Sun", "Soundgarden", 328));
        cola.agregarCancion(new Cancion("Jeremy", "Pearl Jam", 319));
        cola.agregarCancion(new Cancion("Alive", "Pearl Jam", 341));
        cola.agregarCancion(new Cancion("Man in the Box", "Alice in Chains", 286));
        cola.agregarCancion(new Cancion("Would?", "Alice in Chains", 208));
        cola.agregarCancion(new Cancion("Creep", "Radiohead", 238));
        cola.agregarCancion(new Cancion("Everlong", "Foo Fighters", 250));
        cola.agregarCancion(new Cancion("The Pretender", "Foo Fighters", 269));

        System.out.println("=== Cola Inicial ===");
        cola.mostrarCola();

        System.out.println("\nAvanzando 3 canciones:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Reproduciendo: " + cola.reproducirSiguiente());
        }

        System.out.println("\nRetrocediendo:");
        System.out.println("Anterior: " + cola.reproducirAnterior());

        System.out.println("\n=== Mezclando ===");
        cola.mezclar();
        cola.mostrarCola();

        int total = cola.duracionTotal();
        System.out.printf("\nDuración total: %02d:%02d",
                total / 60, total % 60);
    }
}
