public class ListaEnlazada {
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    public static <T> int contarNodos(Node<T> head) {
        int contador = 0;
        Node<T> actual = head;

        while (actual != null) {
            contador++;
            actual = actual.next;
        }

        return contador;
    }
    public static void main(String[] args) {
        Node<Integer> head = new Node<>(10);
        head.next = new Node<>(20);
        head.next.next = new Node<>(30);
        head.next.next.next = new Node<>(40);
        System.out.println("Número total de nodos: " + contarNodos(head));
    }
}