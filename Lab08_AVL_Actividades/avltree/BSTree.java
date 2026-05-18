package avltree;
public class BSTree<E extends Comparable<E>> {

    protected Node<E> root;

    public BSTree() { this.root = null; }

    // ── INSERCIÓN ──────────────────────────────────────────
    public void insert(E x) throws ItemDuplicated {
        root = insert(x, root);
    }

    protected Node<E> insert(E x, Node<E> node) throws ItemDuplicated {
        if (node == null) return new Node<>(x);
        int cmp = x.compareTo(node.data);
        if (cmp == 0) throw new ItemDuplicated(x + " ya existe en el árbol");
        if (cmp < 0) node.left  = insert(x, node.left);
        else         node.right = insert(x, node.right);
        return node;
    }

    // ── BÚSQUEDA ───────────────────────────────────────────
    public E search(E x) {
        Node<E> r = search(x, root);
        return r != null ? r.data : null;
    }

    private Node<E> search(E x, Node<E> node) {
        if (node == null) return null;
        int cmp = x.compareTo(node.data);
        if (cmp == 0) return node;
        return cmp < 0 ? search(x, node.left) : search(x, node.right);
    }

    // ── ELIMINACIÓN ────────────────────────────────────────
    public void delete(E x) {
        root = delete(x, root);
    }

    protected Node<E> delete(E x, Node<E> node) {
        if (node == null) return null;
        int cmp = x.compareTo(node.data);
        if      (cmp < 0) node.left  = delete(x, node.left);
        else if (cmp > 0) node.right = delete(x, node.right);
        else {
            if (node.left == null && node.right == null) return null;
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;
            Node<E> succ = minNode(node.right);
            node.data    = succ.data;
            node.right   = delete(succ.data, node.right);
        }
        return node;
    }

    protected Node<E> minNode(Node<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // ── RECORRIDOS ─────────────────────────────────────────
    public void inOrder()  { inOrder(root); System.out.println(); }
    private void inOrder(Node<E> n) {
        if (n == null) return;
        inOrder(n.left); System.out.print(n.data + " "); inOrder(n.right);
    }

    public void preOrder() { preOrder(root); System.out.println(); }
    private void preOrder(Node<E> n) {
        if (n == null) return;
        System.out.print(n.data + " "); preOrder(n.left); preOrder(n.right);
    }

    // ── ALTURA ─────────────────────────────────────────────
    public int height() { return height(root); }
    protected int height(Node<E> n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    // ── VISUALIZACIÓN ──────────────────────────────────────
    public void printTree() { printTree(root, "", true); }
    protected void printTree(Node<E> n, String pre, boolean isRoot) {
        if (n == null) return;
        System.out.println(pre + (isRoot ? "└── " : "├── ") + n);
        if (n.left != null || n.right != null) {
            printTree(n.left,  pre + "    ", false);
            printTree(n.right, pre + "    ", false);
        }
    }
}
