package avltree;

public class TestActivity1 {

    static void ins(AVLTree<Integer> avl, int v) {
        System.out.println("\n  Insertando " + v + ":");
        try { avl.insert(v); }
        catch (ItemDuplicated e) { System.out.println("  [!] " + e.getMessage()); return; }
        avl.printTree();
    }

    public static void main(String[] args) {
        int[] claves = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};
        AVLTree<Integer> avl = new AVLTree<>();

        System.out.println("Seq: 30,15,20,50,40,60,70,10,25,45,55,65,75");

        for (int k : claves) ins(avl, k);

        System.out.println("\n► InOrden final (debe ser ascendente):");
        avl.inOrder();
        System.out.println("► Altura final: " + avl.heightAVL());

        System.out.println("\n╔════╦═══════╦════════════╦══════════════════╦══════════╦════════╗");
        System.out.println("║ N° ║  Key  ║  Nodo X    ║  Tipo desbalance  ║ Rotación ║  Y     ║");
        System.out.println("╠════╬═══════╬════════════╬══════════════════╬══════════╬════════╣");
        System.out.println("║  1 ║  20   ║    30      ║ Izquierda-Derecha║   RDR    ║  20    ║");
        System.out.println("║  2 ║  40   ║    50      ║ Izq-Izq (bf=-2) ║   RSR    ║  40    ║");
        System.out.println("║  3 ║  70   ║    50      ║ Derecha-Derecha  ║   RSL    ║  60    ║");
        System.out.println("║  4 ║  25   ║    30      ║ Izquierda-Derecha║   RDR    ║  25    ║");
        System.out.println("╚════╩═══════╩════════════╩══════════════════╩══════════╩════════╝");
    }
}
