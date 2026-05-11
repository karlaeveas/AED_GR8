package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNoFound;

public class LinkedBST<E extends Comparable<E>>
implements BinarySearchTree<E> {
	
    private Node root;

    // =========================
    // CLASE INTERNA NODE
    // =========================
    private class Node {

        E data;
        Node left;
        Node right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // =========================
    // CONSTRUCTOR
    // =========================
    public LinkedBST() {
        root = null;
    }

    // =========================
    // VALIDAR SI ESTA VACIO
    // =========================
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // =========================
    // INSERTAR
    // =========================
    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data)
    throws ItemDuplicated {

        // SI EL NODO ES NULL
        // SE CREA EL NUEVO NODO
        if(node == null) {
            return new Node(data);
        }

        Comparable comparador =
                (Comparable) data;

        // IZQUIERDA
        if(comparador.compareTo(node.data) < 0) {
            node.left =
                    insertRec(node.left, data);
        }

        // DERECHA
        else if(comparador.compareTo(node.data) > 0) {
            node.right =
                    insertRec(node.right, data);
        }

        // DUPLICADO
        else {
            throw new ItemDuplicated(
                    "Elemento duplicado");
        }

        return node;
    }

    // =========================
    // BUSCAR
    // =========================
    @Override
    public E search(E data)
    throws ItemNoFound {

        Node resultado =
                searchRec(root, data);

        if(resultado == null) {
            throw new ItemNoFound(
                    "Elemento no encontrado");
        }

        return resultado.data;
    }

    private Node searchRec(Node node, E data) {

        if(node == null) {
            return null;
        }

        Comparable comparador =
                (Comparable) data;

        // ENCONTRADO
        if(comparador.compareTo(node.data) == 0) {
            return node;
        }

        // IZQUIERDA
        if(comparador.compareTo(node.data) < 0) {
            return searchRec(node.left, data);
        }

        // DERECHA
        return searchRec(node.right, data);
    }

    // =========================
    // ELIMINAR
    // =========================
    @Override
    public void delete(E data)
    throws ExceptionIsEmpty {

        if(isEmpty()) {
            throw new ExceptionIsEmpty(
                    "Arbol vacio");
        }

        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) {

        if(node == null) {
            return null;
        }

        Comparable comparador =
                (Comparable) data;

        // IZQUIERDA
        if(comparador.compareTo(node.data) < 0) {
            node.left =
                    deleteRec(node.left, data);
        }

        // DERECHA
        else if(comparador.compareTo(node.data) > 0) {
            node.right =
                    deleteRec(node.right, data);
        }

        // ENCONTRADO
        else {

            // SIN HIJO IZQUIERDO
            if(node.left == null) {
                return node.right;
            }

            // SIN HIJO DERECHO
            if(node.right == null) {
                return node.left;
            }

            // DOS HIJOS
            Node min =
                    findMinNode(node.right);

            node.data = min.data;

            node.right =
                    deleteRec(node.right,
                            min.data);
        }

        return node;
    }

    // =========================
    // MINIMO
    // =========================
    private Node findMinNode(Node node) {

        while(node.left != null) {
            node = node.left;
        }

        return node;
    }

    // =========================
    // MAXIMO
    // =========================
    private Node findMaxNode(Node node) {

        while(node.right != null) {
            node = node.right;
        }

        return node;
    }

    // =========================
    // IN ORDER
    // =========================
    private String inOrder(Node node) {

        if(node == null) {
            return "";
        }

        return inOrder(node.left)
                + node.data + " "
                + inOrder(node.right);
    }

    // =========================
    // PRE ORDER
    // =========================
    private String preOrder(Node node) {

        if(node == null) {
            return "";
        }

        return node.data + " "
                + preOrder(node.left)
                + preOrder(node.right);
    }

    // =========================
    // POST ORDER
    // =========================
    private String postOrder(Node node) {

        if(node == null) {
            return "";
        }

        return postOrder(node.left)
                + postOrder(node.right)
                + node.data + " ";
    }

    // =========================
    // METODOS PUBLICOS
    // =========================
    public String inOrder() {
        return inOrder(root);
    }

    public String preOrder() {
        return preOrder(root);
    }

    public String postOrder() {
        return postOrder(root);
    }

    // =========================
    // TO STRING
    // =========================
    @Override
    public String toString() {
        return inOrder();
    }
}