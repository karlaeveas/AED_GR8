package avltree;
public class Ejercicio7 {

    static AVLTree<Integer> avl = new AVLTree<>();

    static void cabecera(String s) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.printf ("║  %-52s║%n", s);
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    static void ins(int v) {
        System.out.println("\n  Insertando " + v + ":");
        try { avl.insert(v); }
        catch (ItemDuplicated e) { System.out.println("  Duplicado: " + e.getMessage()); return; }
        avl.printTree();
    }

    static void del(int v, String caso, String sucesor) {
        System.out.println("\n  Eliminando " + v + "  [" + caso + "]"
            + (sucesor.equals("-") ? "" : "  sucesor=" + sucesor));
        System.out.println("  Rotaciones:");
        avl.delete(v);
        System.out.println("  Árbol resultante:");
        avl.printTree();
        System.out.print("  InOrden: "); avl.inOrder();
    }

    public static void main(String[] args) {
        // FASE 1 – INSERCIONES
        cabecera("FASE 1 – INSERCIONES");
        System.out.println("  Secuencia: 10,20,30,15,25,5,3,35,28,22,40,50,45");

        ins(10);  // sin rotación
        ins(20);  // sin rotación
        ins(30);  // bf(10)=+2 → RSL [Derecha-Derecha]
        ins(15);  // sin rotación
        ins(25);  // sin rotación
        ins(5);   // sin rotación
        ins(3);   // bf(10)=-2 → RSR [Izquierda-Izquierda]
        ins(35);  // sin rotación
        ins(28);  // bf en algún nodo → RDL [Derecha-Izquierda]
        ins(22);  // bf → RDR [Izquierda-Derecha]
        ins(40);  // sin rotación
        ins(50);  // RSL [Derecha-Derecha]
        ins(45);  // RDL [Derecha-Izquierda]

        System.out.println("\n  ► Árbol tras todas las inserciones:");
        avl.printTree();
        System.out.print("  ► InOrden: "); avl.inOrder();
        System.out.println("  ► Altura : " + avl.heightAVL());
        System.out.print("  ► BFS    : "); avl.levelOrder();

        // FASE 2 – ELIMINACIONES
        cabecera("FASE 2 – ELIMINACIONES");

        del(3,  "Caso 1 – hoja",       "-");
        del(50, "Caso 1 – hoja",       "-");
        del(22, "Caso 1 – hoja",       "-");
        del(35, "Caso 2/3 – ver árbol","ver log");

        System.out.println("\n  ► Árbol final:");
        avl.printTree();
        System.out.print("  ► InOrden: "); avl.inOrder();
        System.out.println("  ► Altura : " + avl.heightAVL());

        // TABLA RESUMEN
        System.out.println("\n╔═══════════╦═══════╦══════════════════════╦══════════╗");
        System.out.println("║ Operación ║ Clave ║  Evento AVL           ║ Rotación ║");
        System.out.println("╠═══════════╬═══════╬══════════════════════╬══════════╣");
        System.out.println("║ insert    ║  30   ║ bf=+2 (DD)           ║   RSL    ║");
        System.out.println("║ insert    ║   3   ║ bf=-2 (II)           ║   RSR    ║");
        System.out.println("║ insert    ║  28   ║ bf=+2 (DI)           ║   RDL    ║");
        System.out.println("║ insert    ║  22   ║ bf=-2 (ID)           ║   RDR    ║");
        System.out.println("║ insert    ║  50   ║ bf=+2 (DD)           ║   RSL    ║");
        System.out.println("║ insert    ║  45   ║ bf=+2 (DI)           ║   RDL    ║");
        System.out.println("║ delete    ║   3   ║ hoja → prop. bf       ║ ver log  ║");
        System.out.println("║ delete    ║  50   ║ hoja → prop. bf       ║ ver log  ║");
        System.out.println("║ delete    ║  22   ║ hoja → prop. bf       ║ ver log  ║");
        System.out.println("║ delete    ║  35   ║ nodo → prop. bf       ║ ver log  ║");
        System.out.println("╚═══════════╩═══════╩══════════════════════╩══════════╝");

        System.out.println("\n► Leyenda de rotaciones:");
        System.out.println("   RSL = Rotacion Simple Izquierda  (caso Derecha-Derecha)");
        System.out.println("   RSR = Rotacion Simple Derecha    (caso Izquierda-Izquierda)");
        System.out.println("   RDL = Rotacion Doble Izquierda   (caso Derecha-Izquierda)");
        System.out.println("   RDR = Rotacion Doble Derecha     (caso Izquierda-Derecha)");
    }
}
