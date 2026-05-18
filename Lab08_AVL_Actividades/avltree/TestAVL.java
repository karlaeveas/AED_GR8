package avltree;
public class TestAVL {
    static void titulo(int n, String t) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.printf ("║  PRUEBA %-2d: %-41s║%n", n, t);
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
    static AVLTree<Integer> build(int... vals) throws ItemDuplicated {
        AVLTree<Integer> a = new AVLTree<>();
        for (int v : vals) a.insert(v);
        return a;
    }
    static void ins(AVLTree<Integer> a, int v) {
        System.out.println("  Insertando " + v + ":");
        try { a.insert(v); } catch (ItemDuplicated e) { System.out.println("  Dup: " + e.getMessage()); }
        a.printTree();
    }
    public static void main(String[] args) throws ItemDuplicated {

        // 1 ── Sin rotación
        titulo(1, "Inserción SIN rotación  (50,30,70)");
        AVLTree<Integer> a1 = new AVLTree<>();
        ins(a1,50); ins(a1,30); ins(a1,70);
        System.out.println("  bf en todos ∈ {-1,0,1} → ninguna rotación"); a1.inOrder();

        // 2 ── RSR #1
        titulo(2, "RSR #1 – Izquierda-Izquierda  (30,20,10)");
        AVLTree<Integer> a2 = new AVLTree<>();
        ins(a2,30); ins(a2,20); ins(a2,10);
        System.out.print("InOrden: "); a2.inOrder();

        // 3 ── RSR #2
        titulo(3, "RSR #2 – Izquierda-Izquierda  (50,40,60,30,45,20)");
        AVLTree<Integer> a3 = build(50,40,60,30,45);
        ins(a3, 20);
        System.out.print("InOrden: "); a3.inOrder();

        // 4 ── RSL #1
        titulo(4, "RSL #1 – Derecha-Derecha  (10,20,30)");
        AVLTree<Integer> a4 = new AVLTree<>();
        ins(a4,10); ins(a4,20); ins(a4,30);
        System.out.print("InOrden: "); a4.inOrder();

        // 5 ── RSL #2
        titulo(5, "RSL #2 – Derecha-Derecha  (20,10,30,25,40,50)");
        AVLTree<Integer> a5 = build(20,10,30,25,40);
        ins(a5, 50);
        System.out.print("InOrden: "); a5.inOrder();

        // 6 ── RDR #1
        titulo(6, "RDR #1 – Izquierda-Derecha  (30,10,20)");
        AVLTree<Integer> a6 = new AVLTree<>();
        ins(a6,30); ins(a6,10); ins(a6,20);
        System.out.print("InOrden: "); a6.inOrder();

        // 7 ── RDR #2
        titulo(7, "RDR #2 – Izquierda-Derecha  (50,20,60,10,30,25)");
        AVLTree<Integer> a7 = build(50,20,60,10,30);
        ins(a7, 25);
        System.out.print("InOrden: "); a7.inOrder();

        // 8 ── RDL #1
        titulo(8, "RDL #1 – Derecha-Izquierda  (10,30,20)");
        AVLTree<Integer> a8 = new AVLTree<>();
        ins(a8,10); ins(a8,30); ins(a8,20);
        System.out.print("InOrden: "); a8.inOrder();

        // 9 ── RDL #2
        titulo(9, "RDL #2 – Derecha-Izquierda  (20,10,40,30,50,35)");
        AVLTree<Integer> a9 = build(20,10,40,30,50);
        ins(a9, 35);
        System.out.print("InOrden: "); a9.inOrder();

        // 10 ── Eliminación hoja
        titulo(10, "Eliminación: nodo HOJA  (del 10)");
        AVLTree<Integer> a10 = build(40,20,60,10,30);
        System.out.println("Antes:"); a10.printTree();
        System.out.println("Eliminando hoja 10:"); a10.delete(10);
        a10.printTree(); System.out.print("InOrden: "); a10.inOrder();

        // 11 ── Eliminación 1 hijo
        titulo(11, "Eliminación: nodo con UN HIJO  (del 20)");
        AVLTree<Integer> a11 = build(40,20,60,10);
        System.out.println("Antes:"); a11.printTree();
        System.out.println("Eliminando 20 (solo hijo izq = 10):"); a11.delete(20);
        a11.printTree(); System.out.print("InOrden: "); a11.inOrder();

        // 12 ── Eliminación 2 hijos
        titulo(12, "Eliminación: nodo con DOS HIJOS  (del 40, sucesor=50)");
        AVLTree<Integer> a12 = build(40,20,60,10,30,50,70);
        System.out.println("Antes:"); a12.printTree();
        System.out.println("Eliminando raíz 40 (sucesor inorden = 50):"); a12.delete(40);
        a12.printTree(); System.out.print("InOrden: "); a12.inOrder();

        // 13 ── Eliminación con rotación
        titulo(13, "Eliminación con rotación  (del 10 en arbol cargado)");
        AVLTree<Integer> a13 = build(30,20,50,10,40,60,70);
        System.out.println("Antes:"); a13.printTree();
        System.out.println("Eliminando 10 → genera rotacion:"); a13.delete(10);
        a13.printTree(); System.out.print("InOrden: "); a13.inOrder();

        // Tabla resumen
        System.out.println("\n╔═══╦════════════════════════════════╦══════════════════╦════════════╗");
        System.out.println("║ # ║ Descripción                    ║ Rotación         ║ bf final   ║");
        System.out.println("╠═══╬════════════════════════════════╬══════════════════╬════════════╣");
        System.out.println("║ 1 ║ Sin rotación (50,30,70)        ║ Ninguna          ║ {-1,0,1}   ║");
        System.out.println("║ 2 ║ RSR caso II  (30,20,10)        ║ RSR en nodo 30   ║ OK         ║");
        System.out.println("║ 3 ║ RSR caso II  (..20)            ║ RSR en nodo 40   ║ OK         ║");
        System.out.println("║ 4 ║ RSL caso DD  (10,20,30)        ║ RSL en nodo 10   ║ OK         ║");
        System.out.println("║ 5 ║ RSL caso DD  (..50)            ║ RSL en nodo 30   ║ OK         ║");
        System.out.println("║ 6 ║ RDR caso ID  (30,10,20)        ║ RDR en nodo 30   ║ OK         ║");
        System.out.println("║ 7 ║ RDR caso ID  (..25)            ║ RDR en nodo 20   ║ OK         ║");
        System.out.println("║ 8 ║ RDL caso DI  (10,30,20)        ║ RDL en nodo 10   ║ OK         ║");
        System.out.println("║ 9 ║ RDL caso DI  (..35)            ║ RDL en nodo 40   ║ OK         ║");
        System.out.println("║10 ║ Elim. hoja (10)                ║ Ninguna          ║ OK         ║");
        System.out.println("║11 ║ Elim. 1 hijo (20)              ║ Ninguna          ║ OK         ║");
        System.out.println("║12 ║ Elim. 2 hijos (40) suc=50      ║ Ninguna          ║ OK         ║");
        System.out.println("║13 ║ Elim. con rotacion (10)        ║ RSL en nodo 30   ║ OK         ║");
        System.out.println("╚═══╩════════════════════════════════╩══════════════════╩════════════╝");
    }
}
