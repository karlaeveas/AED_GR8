package avltree;

/**
 * ═══════════════════════════════════════════════════════════════
 *  AVLTree<E> – Árbol AVL genérico (hereda de BSTree<E>)
 * ═══════════════════════════════════════════════════════════════
 *
 *  ESTRUCTURA INTERNA
 *  ──────────────────
 *  NodeAVL  extiende Node<E> agregando el campo `bf`
 *  (balance factor = altura(der) − altura(izq)).
 *  El árbol mantiene bf ∈ {−1, 0, 1} en todo nodo.
 *
 *  OPERACIONES REDEFINIDAS (respecto a BSTree)
 *  ───────────────────────
 *  insert(x)  →  inserta como BST, luego rebalancea hacia raíz.
 *  delete(x)  →  elimina como BST, luego rebalancea hacia raíz.
 *
 *  ROTACIONES
 *  ──────────
 *  rotateSL(node)   – Rotación Simple Izquierda (RSL)
 *  rotateSR(node)   – Rotación Simple Derecha   (RSR)
 *  balanceToLeft()  – corrige bf = +2 (sobrecarga a la derecha)
 *                     → RSL (caso DD) o RDL (caso DI)
 *  balanceToRight() – corrige bf = −2 (sobrecarga a la izquierda)
 *                     → RSR (caso II) o RDR (caso ID)
 *
 *  FLAG `height`
 *  ─────────────
 *  Booleano que sube por la recursión indicando si el subárbol
 *  cambió de altura. Si height==false la propagación se detiene,
 *  lo que optimiza el recorrido hacia la raíz.
 * ═══════════════════════════════════════════════════════════════
 */
public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    // ── Nodo AVL ──────────────────────────────────────────────────
    class NodeAVL extends Node<E> {
        int bf;   // factor de equilibrio: altura(der) - altura(izq)

        NodeAVL(E data) { super(data); this.bf = 0; }

        @Override
        public String toString() { return data + "(bf=" + bf + ")"; }
    }

    /** Flag de cambio de altura compartido durante la recursión */
    private boolean height;

    // ═══════════════════════════════════════════════════════════════
    //  INSERCIÓN
    // ═══════════════════════════════════════════════════════════════

    /**
     * Punto de entrada público.
     * Reinicia el flag y delega en la versión recursiva AVL.
     */
    @Override
    public void insert(E x) throws ItemDuplicated {
        height = false;
        root   = insertAVL(x, (NodeAVL) root);
    }

    /**
     * Inserta x como BST y actualiza bf en el camino de retorno.
     *
     * Regla de actualización al retornar:
     *   Inserción en subárbol derecho  → bf++ en el padre
     *   Inserción en subárbol izquierdo→ bf-- en el padre
     *
     *   bf pasa de  0 a ±1 → altura creció, continuar propagación
     *   bf pasa de ±1 a  0 → altura se estabilizó, detener
     *   bf llega a  ±2     → desbalance, aplicar rotación
     */
    @SuppressWarnings("unchecked")
    private NodeAVL insertAVL(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            // Nuevo nodo: la altura del subárbol creció
            height = true;
            fat    = new NodeAVL(x);

        } else {
            int resC = node.data.compareTo(x);

            if (resC == 0)
                throw new ItemDuplicated(x + " ya se encuentra en el árbol...");

            // ── Insertar en el subárbol DERECHO ──────────────────
            if (resC < 0) {
                fat.right = insertAVL(x, (NodeAVL) node.right);
                if (height) {
                    switch (fat.bf) {
                        case -1: fat.bf = 0;  height = false; break;
                        case  0: fat.bf = 1;  height = true;  break;
                        case  1:                              // bf → +2
                            fat    = balanceToLeft(fat);
                            height = false;
                            break;
                    }
                }
            }
            // ── Insertar en el subárbol IZQUIERDO ────────────────
            else {
                fat.left = insertAVL(x, (NodeAVL) node.left);
                if (height) {
                    switch (fat.bf) {
                        case  1: fat.bf = 0;  height = false; break;
                        case  0: fat.bf = -1; height = true;  break;
                        case -1:                              // bf → -2
                            fat    = balanceToRight(fat);
                            height = false;
                            break;
                    }
                }
            }
        }
        return fat;
    }

    // ═══════════════════════════════════════════════════════════════
    //  ELIMINACIÓN
    // ═══════════════════════════════════════════════════════════════

    /**
     * Punto de entrada público.
     * Reinicia el flag y delega en deleteAVL().
     */
    @Override
    public void delete(E x) {
        height = false;
        root   = deleteAVL(x, (NodeAVL) root);
    }

    /**
     * Elimina x con lógica BST (3 casos) y rebalancea en retorno.
     *
     * Diferencia clave vs inserción:
     *   bf cambia de ±1 a 0  → altura SÍ disminuyó, continuar
     *   bf cambia de  0 a ±1 → altura no cambió, detener
     *   bf llega a ±2        → aplicar rotación
     *
     * Puede necesitar MÁS DE UNA rotación al subir hacia la raíz.
     */
    @SuppressWarnings("unchecked")
    private NodeAVL deleteAVL(E x, NodeAVL node) {
        if (node == null) return null;

        int cmp = x.compareTo(node.data);

        if (cmp < 0) {
            // Eliminar en la izquierda → si baja altura, bf++
            node.left = deleteAVL(x, (NodeAVL) node.left);
            if (height) {
                switch (node.bf) {
                    case -1: node.bf = 0;  height = true;  break;
                    case  0: node.bf = 1;  height = false; break;
                    case  1: node = balanceToLeft(node);   break;
                }
            }
        } else if (cmp > 0) {
            // Eliminar en la derecha → si baja altura, bf--
            node.right = deleteAVL(x, (NodeAVL) node.right);
            if (height) {
                switch (node.bf) {
                    case  1: node.bf = 0;  height = true;  break;
                    case  0: node.bf = -1; height = false; break;
                    case -1: node = balanceToRight(node);  break;
                }
            }
        } else {
            // Nodo encontrado → aplicar caso BST
            height = true;
            if (node.left == null && node.right == null) {
                return null;                                  // Caso 1: hoja
            } else if (node.left == null) {
                return (NodeAVL) node.right;                  // Caso 2: 1 hijo der
            } else if (node.right == null) {
                return (NodeAVL) node.left;                   // Caso 2: 1 hijo izq
            } else {
                // Caso 3: 2 hijos → reemplazar con sucesor inorden
                NodeAVL succ = (NodeAVL) minNode(node.right);
                node.data    = succ.data;
                node.right   = deleteAVL(succ.data, (NodeAVL) node.right);
                if (height) {
                    switch (node.bf) {
                        case  1: node.bf = 0;  height = true;  break;
                        case  0: node.bf = -1; height = false; break;
                        case -1: node = balanceToRight(node);  break;
                    }
                }
            }
        }
        return node;
    }

    // ═══════════════════════════════════════════════════════════════
    //  MÉTODOS DE BALANCEO
    // ═══════════════════════════════════════════════════════════════

    /**
     * balanceToLeft – corrige bf = +2 (árbol cargado a la derecha).
     *
     * ┌─ hijo.bf = +1 ─► Caso DD: RSL simple
     * ├─ hijo.bf = -1 ─► Caso DI: RSR en hijo + RSL en nodo (RDL)
     * └─ hijo.bf =  0 ─► RSL simple (solo en eliminación)
     */
    @SuppressWarnings("unchecked")
    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) {
            case 1:   // ── Derecha-Derecha ──────────────────────
                System.out.println("    [RSL] Rotacion Simple Izquierda en nodo " + node.data);
                node.bf = 0;
                hijo.bf = 0;
                node    = rotateSL(node);
                break;

            case -1:  // ── Derecha-Izquierda ────────────────────
                System.out.println("    [RDL] Rotacion Doble Der-Izq en nodo " + node.data);
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case -1: node.bf = 0; hijo.bf =  1; break;
                    case  0: node.bf = 0; hijo.bf =  0; break;
                    case  1: node.bf =-1; hijo.bf =  0; break;
                }
                nieto.bf   = 0;
                node.right = rotateSR(hijo);
                node       = rotateSL(node);
                break;

            default:  // bf = 0, solo en eliminación
                System.out.println("    [RSL] Rotacion Simple Izquierda (eliminacion) en nodo " + node.data);
                node.bf = 1;
                hijo.bf = -1;
                node    = rotateSL(node);
                height  = false;
                break;
        }
        return node;
    }

    /**
     * balanceToRight – corrige bf = −2 (árbol cargado a la izquierda).
     *
     * ┌─ hijo.bf = -1 ─► Caso II: RSR simple
     * ├─ hijo.bf = +1 ─► Caso ID: RSL en hijo + RSR en nodo (RDR)
     * └─ hijo.bf =  0 ─► RSR simple (solo en eliminación)
     */
    @SuppressWarnings("unchecked")
    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        switch (hijo.bf) {
            case -1:  // ── Izquierda-Izquierda ──────────────────
                System.out.println("    [RSR] Rotacion Simple Derecha en nodo " + node.data);
                node.bf = 0;
                hijo.bf = 0;
                node    = rotateSR(node);
                break;

            case 1:   // ── Izquierda-Derecha ────────────────────
                System.out.println("    [RDR] Rotacion Doble Izq-Der en nodo " + node.data);
                NodeAVL nieto = (NodeAVL) hijo.right;
                switch (nieto.bf) {
                    case  1: node.bf = 0; hijo.bf = -1; break;
                    case  0: node.bf = 0; hijo.bf =  0; break;
                    case -1: node.bf = 1; hijo.bf =  0; break;
                }
                nieto.bf  = 0;
                node.left = rotateSL(hijo);
                node      = rotateSR(node);
                break;

            default:  // bf = 0, solo en eliminación
                System.out.println("    [RSR] Rotacion Simple Derecha (eliminacion) en nodo " + node.data);
                node.bf = -1;
                hijo.bf =  1;
                node    = rotateSR(node);
                height  = false;
                break;
        }
        return node;
    }

    // ═══════════════════════════════════════════════════════════════
    //  ROTACIONES SIMPLES
    // ═══════════════════════════════════════════════════════════════

    /**
     * rotateSL – Rotación Simple Izquierda (RSL).
     *
     *      node               p
     *     /    \            /   \
     *   T1      p    →   node   T3
     *          / \       /  \
     *        T2  T3     T1  T2
     *
     * El hijo derecho (p) sube como nueva raíz del subárbol.
     * Su hijo izquierdo (T2) pasa a ser hijo derecho de node.
     */
    @SuppressWarnings("unchecked")
    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p  = (NodeAVL) node.right;
        node.right = p.left;
        p.left     = node;
        node       = p;
        return node;
    }

    /**
     * rotateSR – Rotación Simple Derecha (RSR), simétrica a RSL.
     *
     *       node              p
     *      /    \           /   \
     *     p     T3   →    T1   node
     *    / \                   /  \
     *   T1  T2               T2   T3
     *
     * El hijo izquierdo (p) sube como nueva raíz del subárbol.
     * Su hijo derecho (T2) pasa a ser hijo izquierdo de node.
     */
    @SuppressWarnings("unchecked")
    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p  = (NodeAVL) node.left;
        node.left  = p.right;
        p.right    = node;
        node       = p;
        return node;
    }

    // ═══════════════════════════════════════════════════════════════
    //  RECORRIDO POR AMPLITUD RECURSIVO (BFS)
    // ═══════════════════════════════════════════════════════════════

    /**
     * Recorrido por niveles (BFS) implementado de forma recursiva.
     * Usa altura del árbol para controlar los niveles y un método
     * auxiliar que imprime los nodos de un nivel dado.
     */
    public void levelOrder() {
        int h = height();
        System.out.print("BFS por niveles: ");
        for (int level = 0; level < h; level++) {
            printLevel((NodeAVL) root, level);
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    private void printLevel(NodeAVL node, int level) {
        if (node == null) return;
        if (level == 0) {
            System.out.print(node.data + " ");
        } else {
            printLevel((NodeAVL) node.left,  level - 1);
            printLevel((NodeAVL) node.right, level - 1);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  VISUALIZACIÓN ESPECIALIZADA
    // ═══════════════════════════════════════════════════════════════

    @Override
    public void printTree() { printAVL((NodeAVL) root, "", true); }

    @SuppressWarnings("unchecked")
    private void printAVL(NodeAVL node, String prefix, boolean isRoot) {
        if (node == null) { System.out.println(prefix + (isRoot?"└── ":"├── ") + "(null)"); return; }
        System.out.println(prefix + (isRoot ? "└── " : "├── ") + node);
        if (node.left != null || node.right != null) {
            printAVL((NodeAVL) node.left,  prefix + "    ", false);
            printAVL((NodeAVL) node.right, prefix + "    ", false);
        }
    }

    public int heightAVL() { return height(root); }
}
