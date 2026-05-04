package actividadEjercicio4;

import java.util.LinkedList;
import actividad1.ExceptionIsEmpty;

class PriorityQueueHybrid<E extends Comparable<E>> {

    class Entry {
        E data;
        int secondary;

        Entry(E data, int secondary) {
            this.data = data;
            this.secondary = secondary;
        }
    }

    private LinkedList<Entry>[] queues;
    private int levels;

    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        queues = new LinkedList[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new LinkedList<>();
        }
    }

    public void enqueue(E x, int priority, int secondary) {
        Entry nuevo = new Entry(x, secondary);
        LinkedList<Entry> lista = queues[priority];

        int i = 0;
        while (i < lista.size() && lista.get(i).secondary <= secondary) {
            i++;
        }
        lista.add(i, nuevo);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].removeFirst().data;
            }
        }
        throw new ExceptionIsEmpty("Cola vacía");
    }
}