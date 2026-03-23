package ejercicionueve;

public class TestEstacion {
    public static void main(String[] args) {

        // Estación solo para Smartphones
        PowerStation<Smartphone> zonaCelulares = new PowerStation<>();
        
        Smartphone s1 = new Smartphone("iPhone 15", 20.5);
        Smartphone s2 = new Smartphone("Galaxy S24", 25.0);
        Smartphone s3 = new Smartphone("iPhone 15", 20.5); // igual que s1

        zonaCelulares.conectar(s1);
        zonaCelulares.conectar(s2);

        System.out.println("=== ZONA CELULARES ===");
        zonaCelulares.mostrarReporte();
        System.out.println("Consumo Total: " + zonaCelulares.calcularConsumoTotal() + " W");

        // Prueba de búsqueda
        int pos = zonaCelulares.buscarDispositivo(s3);
        System.out.println("\nBúsqueda de iPhone 15 → posición: " + (pos == -1 ? "No encontrado" : (pos + 1)));

        // Estación solo para Laptops (demuestra que funciona con otro tipo)
        PowerStation<Laptop> zonaPortatiles = new PowerStation<>();
        zonaPortatiles.conectar(new Laptop("Dell XPS", 65.0));
        zonaPortatiles.conectar(new Laptop("MacBook Pro", 45.0));

        System.out.println("\n=== ZONA PORTÁTILES ===");
        zonaPortatiles.mostrarReporte();
        System.out.println("Consumo Total: " + zonaPortatiles.calcularConsumoTotal() + " W");
    }
}
