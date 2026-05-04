package actividadEjercicio4;

import actividad1.ExceptionIsEmpty;

public class TestPriorityQueueHybrid {
    public static void main(String[] args) {
        PriorityQueueHybrid<String> pq = new PriorityQueueHybrid<>(3);

        try {
            // (dato, prioridad, valor secundario)
            pq.enqueue("A", 2, 5);
            pq.enqueue("B", 2, 1);
            pq.enqueue("C", 1, 3);
            pq.enqueue("D", 2, 3);

            // PROCESAR
            System.out.println("Atendiendo: " + pq.dequeue()); // B
            System.out.println("Atendiendo: " + pq.dequeue()); // D
            System.out.println("Atendiendo: " + pq.dequeue()); // A
            System.out.println("Atendiendo: " + pq.dequeue()); // C

            // ERROR CONTROLADO
            pq.dequeue();

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
