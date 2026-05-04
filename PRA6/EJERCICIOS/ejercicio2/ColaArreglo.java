package ejercicio2;

import actividad1.ExceptionIsEmpty;

class ColaArreglo<E> {
    private E[] arr;
    private int front, rear, size, capacity;

    public ColaArreglo(int capacity) {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");

        E data = arr[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}