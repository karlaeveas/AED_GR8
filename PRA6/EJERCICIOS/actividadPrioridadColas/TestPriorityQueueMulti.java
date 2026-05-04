package actividadPrioridadColas;

import actividad1.ExceptionIsEmpty;

public class TestPriorityQueueMulti {
    public static void main(String[] args) {
        PriorityQueueMulti<String> pq = new PriorityQueueMulti<>(3);

        try {
            // INSERTAR (dato, prioridad)
            pq.enqueue("A", 0);
            pq.enqueue("B", 2);
            pq.enqueue("C", 1);
            pq.enqueue("D", 2);

            // PROCESAR
            System.out.println("Atendiendo: " + pq.dequeue()); // B
            System.out.println("Atendiendo: " + pq.dequeue()); // D
            System.out.println("Atendiendo: " + pq.dequeue()); // C
            System.out.println("Atendiendo: " + pq.dequeue()); // A

            // PROBAR ERROR
            pq.dequeue();

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}