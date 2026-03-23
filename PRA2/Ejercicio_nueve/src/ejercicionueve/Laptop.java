package ejercicionueve;

public class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;
    private int nivelBateria;

    public Laptop(String marca, double consumoVatios) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 40; // valor por defecto
    }

    // Getters y setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    @Override
    public double getConsumoVatios() { return consumoVatios; }
    @Override
    public int getNivelBateria() { return nivelBateria; }
    @Override
    public void cargar(int cantidad) {
        nivelBateria = Math.min(100, nivelBateria + cantidad);
    }

    // equals() obligatorio para buscarDispositivo
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Laptop other = (Laptop) obj;
        return marca.equals(other.marca);
    }
}
