package lab02;

public class Principal {
    public static void main(String[] args) {
    	
    	System.out.println("=== BOLSA DE GOLOSINAS ===");
    	Bolsa<Golosina> bolsaGolo = new Bolsa<Golosina>(3);
    	
    	Golosina g  = new Golosina("caramelo", 0.25);
    	Golosina g1  = new Golosina("gomita", 0.15);
    	Golosina g2  = new Golosina("paleta", 0.30);
    	
    	bolsaGolo.add(g);
        bolsaGolo.add(g1);
        bolsaGolo.add(g2);
        
        for (Golosina golosina : bolsaGolo) {
            System.out.println(golosina.getNombre() + " - " + golosina.getPeso() + " kg");
        }
    	
        System.out.println("\n=== BOLSA DE CHOCOLATINAS ===");
        Bolsa<Chocolatina> bolsaCho = new Bolsa<Chocolatina>(3);  

        Chocolatina c  = new Chocolatina("milka");
        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("Ferrero");

        bolsaCho.add(c);
        bolsaCho.add(c1);
        bolsaCho.add(c2);

        for (Chocolatina chocolatina : bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }
    }
}
