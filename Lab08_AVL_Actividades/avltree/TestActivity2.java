package avltree;

public class TestActivity2 {

    public static void main(String[] args) throws ItemDuplicated {
        AVLTree<Integer> avl = new AVLTree<>();
        int[] init = {33,20,45,12,26,41,56,6,15,24,35,44,48,59,17,38,46,53,65,50};
        for (int v : init) avl.insert(v);

        System.out.println("Árbol inicial:");
        avl.printTree();
        System.out.print("InOrden: "); avl.inOrder();

        Object[][] ops = {
            {12, "Caso 1 – hoja",        "-"},
            {33, "Caso 3 – dos hijos",   "35 (sucesor inorden)"},
            {46, "Caso 1 – hoja",        "-"},
            {59, "Caso 2 – un hijo",     "-"},
            {45, "Caso 3 – dos hijos",   "48 (sucesor inorden)"},
            {56, "Caso 1 – hoja",        "-"},
        };

        for (Object[] op : ops) {
            int    k  = (int)    op[0];
            String cs = (String) op[1];
            String sc = (String) op[2];
            System.out.println("\n══════════════════════════════════════════════════");
            System.out.println("▶ Eliminar " + k + "  [" + cs + "]" + (!sc.equals("-") ? "  sucesor=" + sc : ""));
            System.out.println("  Rotaciones:");
            avl.delete(k);
            System.out.println("  Árbol resultante:");
            avl.printTree();
            System.out.print("  InOrden: "); avl.inOrder();
        }

        System.out.println("\n╔════════════╦══════════════════╦════════╦══════════════╦══════════╦══════════╗");
        System.out.println("║ Eliminación║    Caso BST      ║Sucesor ║ ¿Desbalance? ║  Nodo X  ║ Rotación ║");
        System.out.println("╠════════════╬══════════════════╬════════╬══════════════╬══════════╬══════════╣");
        System.out.println("║     12     ║  Caso 1 (hoja)   ║   -    ║      Si      ║    20    ║   RSL    ║");
        System.out.println("║     33     ║ Caso 3 (2 hijos) ║   35   ║      No      ║    -     ║    -     ║");
        System.out.println("║     46     ║  Caso 1 (hoja)   ║   -    ║      No      ║    -     ║    -     ║");
        System.out.println("║     59     ║ Caso 2 (1 hijo)  ║   -    ║      No      ║    -     ║    -     ║");
        System.out.println("║     45     ║ Caso 3 (2 hijos) ║   48   ║      No      ║    -     ║    -     ║");
        System.out.println("║     56     ║  Caso 1 (hoja)   ║   -    ║      No      ║    -     ║    -     ║");
        System.out.println("╚════════════╩══════════════════╩════════╩══════════════╩══════════╩══════════╝");
    }
}
