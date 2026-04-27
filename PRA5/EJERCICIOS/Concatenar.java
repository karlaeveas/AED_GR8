import java.util.LinkedList;
public class Concatenar {

    public static <T> LinkedList<T> concatenarListas(LinkedList<T> lista1, LinkedList<T> lista2) {
        LinkedList<T> nuevaLista = new LinkedList<>();
        for (T elemento : lista1) {
            nuevaLista.add(elemento);
        }

        for (T elemento : lista2) {
            nuevaLista.add(elemento);
        }

        return nuevaLista;
    }
    public static void main(String[] args) {
        LinkedList<String> lista1 = new LinkedList<>();
        LinkedList<String> lista2 = new LinkedList<>();

        lista1.add("1");
        lista1.add("b");
        lista1.add("3");

        lista2.add("a");
        lista2.add("2");
        lista2.add("c");

        LinkedList<String> resultado = concatenarListas(lista1, lista2);

        System.out.println("Lista 1: " + lista1);
        System.out.println("Lista 2: " + lista2);
        System.out.println("Lista concatenada: " + resultado);
    }
}