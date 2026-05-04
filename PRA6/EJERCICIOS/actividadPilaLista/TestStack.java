package actividadPilaLista;

import actividad1.ExceptionIsEmpty;

public class TestStack {
    public static void main(String[] args) {
        Stack<Integer> pila = new StackLink<>();

        try {
            // PUSH
            pila.push(10);
            pila.push(20);
            pila.push(30);

            System.out.println("Pila actual: " + pila);

            // TOP
            System.out.println("Elemento en la cima: " + pila.top()); // 30

            // POP (LIFO)
            System.out.println("Desapilando: " + pila.pop()); // 30
            System.out.println("Desapilando: " + pila.pop()); // 20

            System.out.println("Pila actual: " + pila);

            // MÁS POP
            System.out.println("Desapilando: " + pila.pop()); // 10

            // ERROR CONTROLADO
            System.out.println("Intentando desapilar...");
            pila.pop(); // debe lanzar excepción

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
