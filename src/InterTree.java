import java.util.ArrayList;
import java.util.List;

/**
 * Simple Tree structure. Consist of no limited number of nodes - <tt>InterTree.Node</tt> in width and depth. Nodes
 * may have <tt>List</tt> of other nodes and may have singly linked leaves. Each Node have its weight property.
 * <tt>InterTree.Leaf</tt> also have its weight property. Leaf cann't have references to other nodes but may have
 * reference to other leaf.
 *
 * @author Maks9
 */
public class InterTree {

    /**
     * The main node of the Tree
     */
    private Node head;

    /**
     * Constructs a tree from the previously built nodes structure.
     *
     * @param head main parent node of the nodes structure.
     */
    public InterTree(Node head) {
        this.head = head;
    }

    /**
     * Returns the head of the Tree.
     *
     * @return the head of the Tree.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Set the other head to the Tree.
     *
     * @param head main parent node of the nodes structure.
     */
    public void setHead(Node head) {
        this.head = head;
    }


    /**
     * The main building block for the <tt>InterTree</tt>. Nodes may have <tt>List</tt> of no limited number of other
     * nodes and may have singly linked leaves. Each Node have its weight property.
     */
    public static class Node {

        /**
         * Integer non-zero number.
         */
        private int totalWeight;

        /**
         * <tt>List</tt> of child nodes.
         */
        private List<Node> nodes;

        /**
         * Parent node.
         */
        private Node parent;

        /**
         * The head of sinly linked list of child leaves
         */
        private Leaf leaf;

        /**
         * Constructs a new node with the given weight.
         * <tt>nodes</tt>, <tt>leaf</tt>, <tt>parent</tt> fields are initialized with <tt>null</tt>.
         *
         * @param totalWeight weight parameter of the node.
         */
        Node(int totalWeight) {
            this.totalWeight = totalWeight;
            nodes = null;
            parent = null;
            leaf = null;
        }

        /**
         * Returns the head of the sinly linked list of child leaves.
         *
         * @return the head of the sinly linked list of child leaves.
         */
        public Leaf getLeaf() {
            return leaf;
        }

        /**
         * Returns the <tt>List</tt> of child nodes.
         *
         * @return the <tt>List</tt> of child nodes.
         */
        public List<Node> getNodes() {
            return nodes;
        }

        /**
         * Adds the child node to this node. Sets parent node to the child node.
         *
         * @param node the child node supposed to be added to this node.
         */
        public void addNode(Node node) {
            if (nodes == null) nodes = new ArrayList<>();
            this.nodes.add(node);
            node.parent = this;
        }

        /**
         * Adds the child leaf to this node in the end of the singly linked list.
         *
         * @param leaf the child leaf to this node in the end of the singly linked list.
         */
        public void addLeaf(Leaf leaf) {

            if (leaf == null) return;

            if (this.leaf == null) {
                this.leaf = leaf;
                return;
            }

            Leaf iter = this.leaf;
            while (iter.next != null) {
                iter = iter.next;
            }
            iter.next = leaf;
        }

    }

    /**
     * The Leaf of the <tt>InterTree</tt>. Leaf may have link to the other leaf. Each leaf has its weight property.
     */
    public static class Leaf {

        /**
         * Integer non-zero number.
         */
        private int weight;

        /**
         * Link to an other leaf.
         */
        private Leaf next;

        /**
         * Constructs a new leaf with the given weight.
         *
         * @param weight weight parameter of the leaf.
         */
        Leaf(int weight) {
            this.weight = weight;
        }

        /**
         * Returns the weight of the leaf.
         *
         * @return the weight of the leaf.
         */
        public int getWeight() {
            return weight;
        }

        /**
         * Returns the link to the next leaf.
         *
         * @return the link to the next leaf.
         */
        public Leaf getNext() {
            return next;
        }

    }
}
