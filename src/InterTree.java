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

        Node(int totalWeight) {
            this.totalWeight = totalWeight;
            nodes = new ArrayList<>();
            parent = null;
        }

    }

    public static class Leaf {

        private int weight;

        Leaf(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

    }
}
