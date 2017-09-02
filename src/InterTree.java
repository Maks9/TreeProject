import java.util.ArrayList;
import java.util.List;

public class InterTree {

    private Node head;

    public InterTree(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }


    public static class Node {

        private int totalWeight;
        private List<Node> nodes;
        private Node parent;

        private Leaf leaf;

        Node(int totalWeight) {
            this.totalWeight = totalWeight;
            nodes = new ArrayList<>();
            parent = null;
            leaf = null;
        }

        public Leaf getLeaf() {
            return leaf;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void addNode(Node node) {
            this.nodes.add(node);
            node.parent = this;
        }

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

    public static class Leaf {

        private int weight;
        private Leaf next;

        Leaf(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public Leaf getNext() {
            return next;
        }

    }
}
