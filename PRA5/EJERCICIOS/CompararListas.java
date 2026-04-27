import java.util.LinkedList;

public class CompararListas {

    public static void main(String[] args) {
        LinkedList<Integer> lista1 = new LinkedList<>();
        LinkedList<Integer> lista2 = new LinkedList<>();

        lista1.add(10);
        lista1.add(20);
        lista1.add(30);

        lista2.add(10);
        lista2.add(20);
        lista2.add(30);

        boolean resultado = sonIguales(lista1, lista2);

        if (resultado) {
            System.out.println("Las listas son iguales");
        } else {
            System.out.println("Las listas son diferentes");
        }
    }
    public static <T> boolean sonIguales(LinkedList<T> lista1, LinkedList<T> lista2) {
        if (lista1.size() != lista2.size()) {
            return false;
        }
        for (int i = 0; i < lista1.size(); i++) {
            if (!lista1.get(i).equals(lista2.get(i))) {
                return false;
            }
        }
        return true;
    }
}