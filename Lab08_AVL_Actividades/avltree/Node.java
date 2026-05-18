package avltree;
public class Node<E> {
    public E data;
    public Node<E> left;
    public Node<E> right;
    public Node(E data) { this.data = data; }
    @Override public String toString() { return data.toString(); }
}
