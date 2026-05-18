package com.estructura.tickets;

public class GestorTicketsAVL {
    public static void main(String[] args) {
        System.out.println("1.Operaciones e Inserciones AVL");
        AVLTree arbolAVL = new AVLTree();

        int[] ticketsAInsertar = {30, 10, 20, 40, 50, 25};
        for (int t : ticketsAInsertar) {
            arbolAVL.insertar(t);
        }

        System.out.println("Estructura del Árbol AVL resultante");
        arbolAVL.mostrarArbol();

        System.out.println("Búsquedas");
        System.out.println("¿Buscar Ticket 20?: " + (arbolAVL.buscar(20) ? "Encontrado" : "No encontrado"));
        System.out.println("¿Buscar Ticket 60?: " + (arbolAVL.buscar(60) ? "Encontrado" : "No encontrado"));

        System.out.println("Eliminaciones Secuenciales");
        int[] aEliminar = {10, 40, 30};
        for (int e : aEliminar) {
            arbolAVL.eliminar(e);
            arbolAVL.mostrarArbol();
        }

        System.out.println("3. Tabla Informativa de Eliminaciones");
        System.out.printf("%-12s | %-18s | %-10s | %-18s | %-18s\n", "Clave Elim.", "Caso BST", "Sucesor", "Nodo Desbalanceado", "Rotación");
        for (AVLTree.RegistroEliminacion reg : arbolAVL.historialEliminaciones) {
            System.out.printf("%-12d | %-18s | %-10s | %-18s | %-18s\n",
                    reg.clave, reg.casoBST, reg.sucesor,
                    (reg.nodoDesbalanceado == -1 ? "Ninguno" : reg.nodoDesbalanceado),
                    reg.rotacionAplicada);
        }

        System.out.println("4-5. BFS Recorrido por Amplitud");
        AVLTree arbolBFS = new AVLTree();
        int[] secuenciaBFS = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for(int val : secuenciaBFS) arbolBFS.insertar(val);

        System.out.println("Estructura Árbol en ejercicio 5: ");
        arbolBFS.mostrarArbol();

        System.out.print("Recorrido por Amplitud (BFS Recursivo): ");
        arbolBFS.recorridoAmplitud();

        System.out.print("Recorrido Inorden: ");
        arbolBFS.inOrden();

        System.out.print("Recorrido Preorden en ejercicio 6: ");
        arbolBFS.preOrden();

        System.out.println("2. Experimento BST vs AVL");
        int[] secuenciaPeorCaso = {10, 20, 30, 40, 50};
        BSTTree bstPrueba = new BSTTree();
        AVLTree avlPrueba = new AVLTree();

        for(int v : secuenciaPeorCaso) {
            bstPrueba.insertar(v);
            avlPrueba.insertar(v);
        }

        System.out.println("Insertando secuencia ordenada: [10, 20, 30, 40, 50]");
        System.out.println("-> Altura Árbol BST Tradicional: " + bstPrueba.obtenerAltura() + " (Desbalanceado/Línea Recta)");
        System.out.println("-> Altura Árbol AVL Balanceado:  " + avlPrueba.raiz.altura + " (Balanceado óptimo gracias a rotaciones)");
    }
}
