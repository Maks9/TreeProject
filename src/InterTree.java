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
     * Sorting leaves and checking for the weight consistency of the head node and its child nodes.
     * Cut leaves are transmitted to the next node. Excess leaves are returned.
     *
     * @return the head of the singly linked list of excess leaves.
     */
    public Leaf normalizeTree() {
        return head.normalizeNode(null);
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
         * @throws IllegalArgumentException if the argument is less or equal to zero
         */
        Node(int totalWeight) {
            if (totalWeight <= 0) {
                throw new IllegalArgumentException("totalWeight should be > 0");
            }
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

        /**
         * Sorts singly linked list of leaves.
         */
        public void sortLeaves() {
            leaf = mergeSort(leaf);
        }

        /**
         * Merge sort algorythm implementation for singly linked list of leaves.
         *
         * @param leaf the head of the list to sort.
         * @return head of the sorted list of leaves.
         */
        private Leaf mergeSort(Leaf leaf) {
            if (leaf == null || leaf.next == null) return leaf;

            Leaf middle = findMiddle(leaf);
            Leaf middleNext = middle.next;
            middle.next = null;

            Leaf leftList = mergeSort(leaf);
            Leaf rightList = mergeSort(middleNext);

            return merge(leftList, rightList);

        }

        /**
         * Helping function for merge sort to find the middle of the singly linked list of leaves.
         *
         * @param head the head of the list.
         * @return middle element of the list of leaves.
         */
        private Leaf findMiddle(Leaf head) {
            if (leaf == null) return leaf;

            Leaf iterSlow = head;
            Leaf iterFast = head.next;
            while (iterFast != null) {
                iterFast = iterFast.next;
                if (iterFast != null) {
                    iterFast = iterFast.next;
                    iterSlow = iterSlow.next;
                }
            }
            return iterSlow;
        }

        /**
         * Merging function for two sorted singly linked lists.
         *
         * @param first the head of the first sorted list.
         * @param second the head of the second sorted list.
         * @return the head of the merged list.
         */
        private Leaf merge(Leaf first, Leaf second) {
            if (first == null) return second;
            if (second == null) return first;

            Leaf merged = null;

            if (first.compareTo(second) < 0) {
                merged = first;
                merged.next = merge(first.next, second);
            } else {
                merged = second;
                merged.next = merge(first, second.next);
            }

            return merged;
        }

        /**
         * Check function that compare <tt>totalWeight</tt> of the node with weights of sorted singly linked list
         * of leaves. If total weight of lives is grater than node <tt>totalWeight</tt> parameter then excess leaves
         * are cut from node and returned by the function.
         *
         * @return the head of singly linked list of cut leaves.
         */
        public Leaf checkLeaves() {

            if (leaf == null) return null;
            if (leaf.getWeight() > totalWeight) {
                Leaf temp = leaf;
                leaf = null;
                return temp;
            }

            int sum = leaf.getWeight();
            Leaf iter = leaf;
            while (iter.next != null) {
                int iterNextWeight = iter.next.getWeight();
                if (sum + iterNextWeight <= totalWeight) {
                    sum += iterNextWeight;
                } else {
                    Leaf temp = iter.next;
                    iter.next = null;
                    return temp;
                }
                iter = iter.next;
            }

            return null;
        }

        /**
         * Sorting leaves and checking for the weight consistency of the tree of the node and its child nodes.
         * Cut leaves are transmitted to the next node. Excess leaves are thrown away.
         *
         * @param leaf the head of the singly linked list of leaves to be added before sort and weight check.
         * @return the head of singly linked list of cut leaves.
         */
        public Leaf normalizeNode(Leaf leaf) {

            if (leaf != null) addLeaf(leaf);

            sortLeaves();
            Leaf cutLeaves = checkLeaves();

            if (nodes != null) {
                for (Node node : nodes) {
                    cutLeaves = node.normalizeNode(cutLeaves);
                }
            }

            return cutLeaves;
        }

    }

    /**
     * The Leaf of the <tt>InterTree</tt>. Leaf may have link to the other leaf. Each leaf has its weight property.
     */
    public static class Leaf implements Comparable<Leaf>{

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
         * @throws IllegalArgumentException if the argument is less or equal to zero
         */
        Leaf(int weight) {
            if (weight <= 0) {
                throw new IllegalArgumentException("Weight should be > 0");
            }
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

        /**
         * Compare two leafs based on their weight.
         *
         * @return <tt>int</tt> number >0 if argument is less than this leaf.
         */
        @Override
        public int compareTo(Leaf leaf) {

            if (this.getWeight() > leaf.getWeight()) {
                return 1;
            } else if (this.getWeight() < leaf.getWeight()) {
                return -1;
            }

            return 0;
        }

    }
}
