package test;

import bstreelinklistinterfgeneric.LinkedBST;

public class Prueba {

    public static void main(String[] args)
    throws Exception {

        LinkedBST<Integer> bst =
                new LinkedBST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println(
                "IN ORDER:");
        System.out.println(
                bst.inOrder());

        System.out.println(
                "PRE ORDER:");
        System.out.println(
                bst.preOrder());

        System.out.println(
                "POST ORDER:");
        System.out.println(
                bst.postOrder());

        System.out.println(
                "BUSCAR 40:");
        System.out.println(
                bst.search(40));

        bst.delete(30);

        System.out.println(
                "ARBOL DESPUES DE ELIMINAR:");
        System.out.println(
                bst.inOrder());
    }
}
