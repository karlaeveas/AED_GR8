package bstreeInterface;

public interface BinarySearchTree<E extends Comparable<E>> {

    void insert(E data) throws Exception;
    E search(E data) throws Exception;
    void delete(E data) throws Exception;
    boolean isEmpty();
}
