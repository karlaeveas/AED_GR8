package ejercicionueve;

public class Smartphone implements Cargable {
    private String modelo;
    private double consumoVatios;
    private int nivelBateria;

    public Smartphone(String modelo, double consumoVatios) {
        this.modelo = modelo;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 50; // valor por defecto
    }

    // Getters y setters
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

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
        Smartphone other = (Smartphone) obj;
        return modelo.equals(other.modelo);
    }
}