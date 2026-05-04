package actividadPrioridadColas;

import java.util.LinkedList;
import actividad1.ExceptionIsEmpty;

class PriorityQueueMulti<E> {
    private LinkedList<E>[] queues;
    private int levels;

    public PriorityQueueMulti(int levels) {
        this.levels = levels;
        queues = new LinkedList[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new LinkedList<>();
        }
    }

    public void enqueue(E x, int priority) {
        queues[priority].add(x);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].removeFirst();
            }
        }
        throw new ExceptionIsEmpty("Cola vacía");
    }
}
