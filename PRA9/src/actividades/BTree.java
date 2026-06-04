package actividades;

/**
 * Clase BTree - Árbol B genérico de orden n.
 * Implementa: insert, search, searchRange, remove y toString.
 */
public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;          // número máximo de hijos por nodo
    private boolean up;         // señal: hubo división que "sube" mediana
    private BNode<E> nDes;      // nuevo nodo creado en la división

    // ─────────────────────────────────────────────────────────────
    // Constructor
    // ─────────────────────────────────────────────────────────────
    public BTree(int orden) {
        this.orden = orden;
        this.root  = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // ═════════════════════════════════════════════════════════════
    // 1. INSERT
    // ═════════════════════════════════════════════════════════════

    /**
     * Inserta la clave cl en el árbol B.
     * Si ya existe, ignora la operación (sin duplicados).
     */
    public void insert(E cl) {
        up = false;
        E mediana = push(this.root, cl);
        if (up) {
            // La raíz se dividió: crear nueva raíz
            BNode<E> pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    /**
     * Inserción recursiva (top-down).
     * Retorna la mediana si hay división; null en otro caso.
     */
    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;

        if (current == null) {
            // Caso base: llegamos a null → insertar aquí
            up   = true;
            nDes = null;
            return cl;
        }

        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println("Item duplicado: " + cl);
            up = false;
            return null;
        }

        mediana = push(current.childs.get(pos[0]), cl);

        if (up) {
            if (current.nodeFull(this.orden - 1)) {
                // Nodo lleno → dividir
                mediana = dividedNode(current, mediana, pos[0]);
            } else {
                // Nodo con espacio → insertar normalmente
                putNode(current, mediana, nDes, pos[0]);
                up = false;
            }
        }
        return mediana;
    }

    /**
     * Inserta la clave cl (y su hijo derecho rd) en current en la posición k,
     * desplazando los elementos existentes.
     */
    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    /**
     * Divide el nodo current cuando está lleno.
     * La mediana sube al padre; nDes apunta al nuevo nodo derecho.
     */
    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;

        nDes = new BNode<>(this.orden);

        for (int i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // ═════════════════════════════════════════════════════════════
    // 2. SEARCH  (Ejercicio 01)
    // ═════════════════════════════════════════════════════════════

    /**
     * Busca la clave cl en el árbol.
     * @return true si la encuentra (e imprime nodo e índice); false si no.
     */
    public boolean search(E cl) {
        return searchHelper(root, cl);
    }

    private boolean searchHelper(BNode<E> current, E cl) {
        if (current == null) return false;
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.getIdNode() + " en la posición " + pos[0]);
            return true;
        }
        return searchHelper(current.childs.get(pos[0]), cl);
    }

    // ═════════════════════════════════════════════════════════════
    // 3. SEARCH RANGE  (Ejercicio 02)
    // ═════════════════════════════════════════════════════════════

    /**
     * Muestra en orden ascendente todas las claves entre min y max (inclusive).
     * Descarta ramas que no pueden contener claves en el rango.
     */
    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) {
            System.out.println("Rango inválido: min > max");
            return;
        }
        StringBuilder sb = new StringBuilder();
        searchRangeHelper(root, min, max, sb);
        String result = sb.toString().trim();
        if (result.isEmpty())
            System.out.println("No se encontraron claves en el rango [" + min + ", " + max + "]");
        else
            System.out.println(result);
    }

    private void searchRangeHelper(BNode<E> current, E min, E max, StringBuilder sb) {
        if (current == null) return;

        for (int i = 0; i < current.count; i++) {
            E key = current.keys.get(i);

            // Si key > max, todas las claves siguientes y sus hijos derechos también lo son → parar
            if (key.compareTo(max) > 0) {
                // Antes de parar, explorar el hijo izquierdo de esta posición
                // (puede contener claves ≤ max)
                searchRangeHelper(current.childs.get(i), min, max, sb);
                return;
            }

            // Explorar hijo izquierdo solo si puede contener claves >= min
            // (es decir, key >= min implica que el hijo izquierdo podría tenerlas)
            searchRangeHelper(current.childs.get(i), min, max, sb);

            // Agregar la clave si está en rango
            if (key.compareTo(min) >= 0) {
                sb.append(key).append(" ");
            }
        }
        // Explorar el último hijo derecho
        searchRangeHelper(current.childs.get(current.count), min, max, sb);
    }

    // ═════════════════════════════════════════════════════════════
    // 4. REMOVE  (Ejercicio 03)
    // ═════════════════════════════════════════════════════════════

    /**
     * Elimina la clave cl del árbol B.
     * Maneja: nodo hoja con espacio, redistribución y fusión.
     */
    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        removeHelper(root, cl);
        // Si la raíz quedó vacía luego de una fusión, el árbol baja de altura
        if (root.count == 0) {
            root = root.childs.get(0);
        }
    }

    /**
     * Recursión de eliminación.
     * Devuelve true si el nodo actual quedó con menos del mínimo de claves.
     */
    private boolean removeHelper(BNode<E> current, E cl) {
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada.");
            return false;
        }

        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);

        if (found) {
            // ── Caso 1: la clave está en un nodo hoja ──────────────────
            if (isLeaf(current)) {
                deleteFromNode(current, pos[0]);
            } else {
                // ── Caso 2: no es hoja → reemplazar por sucesor ─────────
                BNode<E> leafSuccessor = getLeafSuccessor(current.childs.get(pos[0] + 1));
                E successor = leafSuccessor.keys.get(0);
                current.keys.set(pos[0], successor);
                // Eliminar el sucesor del hijo derecho
                removeHelper(current.childs.get(pos[0] + 1), successor);
            }
        } else {
            // Descender al hijo correspondiente
            BNode<E> child = current.childs.get(pos[0]);
            boolean deficient = removeHelper(child, cl);
            if (deficient) {
                fixDeficiency(current, pos[0]);
            }
            return false; // el nivel superior comprobará más arriba
        }

        // Verificar mínimo (aplica solo a nodos que no son raíz)
        int minKeys = (this.orden / 2) - (this.orden % 2 == 0 ? 1 : 0);
        // Para orden 4: min = 1; para orden 5: min = 2
        minKeys = (int) Math.ceil(this.orden / 2.0) - 1;
        return current != root && current.count < minKeys;
    }

    /** Elimina la clave en la posición idx del nodo (desplaza hacia la izquierda). */
    private void deleteFromNode(BNode<E> node, int idx) {
        for (int i = idx; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
            node.childs.set(i + 1, node.childs.get(i + 2));
        }
        node.keys.set(node.count - 1, null);
        node.childs.set(node.count, null);
        node.count--;
    }

    /** Devuelve el nodo hoja más a la izquierda del subárbol. */
    private BNode<E> getLeafSuccessor(BNode<E> node) {
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node;
    }

    private boolean isLeaf(BNode<E> node) {
        return node.childs.get(0) == null;
    }

    /**
     * Arregla la deficiencia en el hijo childIdx del nodo parent.
     * Intenta redistribución; si no es posible, fusiona.
     */
    private void fixDeficiency(BNode<E> parent, int childIdx) {
        BNode<E> child  = parent.childs.get(childIdx);
        int minKeys = (int) Math.ceil(this.orden / 2.0) - 1;

        // ── Redistribución desde hermano izquierdo ──────────────────
        if (childIdx > 0) {
            BNode<E> leftSibling = parent.childs.get(childIdx - 1);
            if (leftSibling.count > minKeys) {
                redistributeFromLeft(parent, childIdx, child, leftSibling);
                return;
            }
        }
        // ── Redistribución desde hermano derecho ─────────────────────
        if (childIdx < parent.count) {
            BNode<E> rightSibling = parent.childs.get(childIdx + 1);
            if (rightSibling.count > minKeys) {
                redistributeFromRight(parent, childIdx, child, rightSibling);
                return;
            }
        }
        // ── Fusión ───────────────────────────────────────────────────
        if (childIdx > 0) {
            mergeNodes(parent, childIdx - 1); // fusionar con hermano izquierdo
        } else {
            mergeNodes(parent, childIdx);     // fusionar con hermano derecho
        }
    }

    /** Redistribuye una clave del hermano izquierdo al hijo deficiente. */
    private void redistributeFromLeft(BNode<E> parent, int childIdx,
                                       BNode<E> child, BNode<E> left) {
        // Hacer espacio al inicio de child
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
            child.childs.set(i + 1, child.childs.get(i));
        }
        child.childs.set(1, child.childs.get(0));
        // Bajar separador del padre al inicio de child
        child.keys.set(0, parent.keys.get(childIdx - 1));
        child.childs.set(0, left.childs.get(left.count));
        child.count++;
        // Subir la clave más derecha del hermano izquierdo al padre
        parent.keys.set(childIdx - 1, left.keys.get(left.count - 1));
        left.childs.set(left.count, null);
        left.keys.set(left.count - 1, null);
        left.count--;
    }

    /** Redistribuye una clave del hermano derecho al hijo deficiente. */
    private void redistributeFromRight(BNode<E> parent, int childIdx,
                                        BNode<E> child, BNode<E> right) {
        // Bajar separador del padre al final de child
        child.keys.set(child.count, parent.keys.get(childIdx));
        child.childs.set(child.count + 1, right.childs.get(0));
        child.count++;
        // Subir la clave más izquierda del hermano derecho al padre
        parent.keys.set(childIdx, right.keys.get(0));
        // Desplazar right hacia la izquierda
        right.childs.set(0, right.childs.get(1));
        for (int i = 0; i < right.count - 1; i++) {
            right.keys.set(i, right.keys.get(i + 1));
            right.childs.set(i + 1, right.childs.get(i + 2));
        }
        right.keys.set(right.count - 1, null);
        right.childs.set(right.count, null);
        right.count--;
    }

    /**
     * Fusiona parent.childs[idx] con parent.childs[idx+1],
     * bajando parent.keys[idx] al nodo fusionado.
     */
    private void mergeNodes(BNode<E> parent, int idx) {
        BNode<E> left  = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        // Bajar la clave separadora del padre al nodo izquierdo
        left.keys.set(left.count, parent.keys.get(idx));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;

        // Copiar todas las claves e hijos del nodo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count + 1, right.childs.get(i + 1));
            left.count++;
        }

        // Eliminar la clave separadora del padre y el puntero al nodo derecho
        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    // ═════════════════════════════════════════════════════════════
    // 5. TO STRING  (Actividad 3.3)
    // ═════════════════════════════════════════════════════════════

    @Override
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root);
    }

    /**
     * Recorre el árbol en BFS (por niveles) e imprime cada nodo con
     * su id, claves, id del padre e ids de sus hijos.
     */
    private String writeTree(BNode<E> current) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-12s %s%n",
                "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
        sb.append("-".repeat(60)).append("\n");

        // BFS usando dos colas paralelas (nodo, id del padre)
        java.util.Queue<BNode<E>> queue    = new java.util.LinkedList<>();
        java.util.Queue<Integer>  parents  = new java.util.LinkedList<>();
        queue.add(current);
        parents.add(-1);

        while (!queue.isEmpty()) {
            BNode<E> node   = queue.poll();
            int      parent = parents.poll();

            // Claves
            StringBuilder keys = new StringBuilder("(");
            for (int i = 0; i < node.count; i++) {
                keys.append(node.keys.get(i));
                if (i < node.count - 1) keys.append(", ");
            }
            keys.append(")");

            // Hijos
            StringBuilder children = new StringBuilder("[");
            boolean hasChildren = false;
            for (int i = 0; i <= node.count; i++) {
                BNode<E> child = node.childs.get(i);
                if (child != null) {
                    if (hasChildren) children.append(", ");
                    children.append(child.getIdNode());
                    hasChildren = true;
                    queue.add(child);
                    parents.add(node.getIdNode());
                }
            }
            children.append("]");

            sb.append(String.format("%-10d %-20s %-12s %s%n",
                    node.getIdNode(),
                    keys.toString(),
                    parent == -1 ? "--" : String.valueOf(parent),
                    hasChildren ? children.toString() : "--"));
        }
        return sb.toString();
    }

    // ═════════════════════════════════════════════════════════════
    // 6. ALTURA del árbol
    // ═════════════════════════════════════════════════════════════
    public int height() {
        if (isEmpty()) return 0;
        int h = 0;
        BNode<E> node = root;
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
            h++;
        }
        return h + 1;
    }

    // ═════════════════════════════════════════════════════════════
    // 7. CANTIDAD TOTAL DE CLAVES
    // ═════════════════════════════════════════════════════════════
    public int totalKeys() {
        return countKeys(root);
    }

    private int countKeys(BNode<E> node) {
        if (node == null) return 0;
        int total = node.count;
        for (int i = 0; i <= node.count; i++) {
            total += countKeys(node.childs.get(i));
        }
        return total;
    }
}
