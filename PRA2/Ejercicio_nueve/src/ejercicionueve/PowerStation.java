package ejercicionueve;

import java.util.ArrayList;

public class PowerStation<T extends Cargable> {
    private ArrayList<T> dispositivos = new ArrayList<>();

    // 2. Método conectar
    public void conectar(T dispositivo) {
        dispositivos.add(dispositivo);
    }

    // 3. Método calcularConsumoTotal
    public double calcularConsumoTotal() {
        double total = 0;
        for (T d : dispositivos) {
            total += d.getConsumoVatios();
        }
        return total;
    }

    // 4. Método buscarDispositivo (devuelve posición 0-based o -1)
    public int buscarDispositivo(T prototipo) {
        for (int i = 0; i < dispositivos.size(); i++) {
            if (dispositivos.get(i).equals(prototipo)) {
                return i;   // posición encontrada
            }
        }
        return -1;  // no encontrado
    }

    // 5. Método mostrarReporte (formato de tabla como en la guía)
    public void mostrarReporte() {
        System.out.println("Posición | Consumo (W)   | Dispositivo");
        System.out.println("---------|---------------|-------------");
        for (int i = 0; i < dispositivos.size(); i++) {
            String nombre = (dispositivos.get(i) instanceof Smartphone) 
                            ? ((Smartphone) dispositivos.get(i)).getModelo()
                            : ((Laptop) dispositivos.get(i)).getMarca();
            
            System.out.printf("%-8d | %-13.1f | %s%n", 
                              (i + 1), 
                              dispositivos.get(i).getConsumoVatios(), 
                              nombre);
        }
    }
}
