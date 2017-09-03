import org.junit.*;

public class InterTreeTest {

    InterTree tree;
    InterTree.Leaf leaf1 = new InterTree.Leaf(1);
    InterTree.Leaf leaf2 = new InterTree.Leaf(3);
    InterTree.Leaf leaf3 = new InterTree.Leaf(5);
    InterTree.Leaf leaf4 = new InterTree.Leaf(6);
    InterTree.Leaf leaf5 = new InterTree.Leaf(2);
    InterTree.Leaf leaf6 = new InterTree.Leaf(1);
    InterTree.Leaf leaf7 = new InterTree.Leaf(4);
    InterTree.Leaf leaf8 = new InterTree.Leaf(7);
    InterTree.Leaf leaf9 = new InterTree.Leaf(4);
    InterTree.Leaf leaf10 = new InterTree.Leaf(12);
    InterTree.Leaf leaf11 = new InterTree.Leaf(2);
    InterTree.Leaf leaf12 = new InterTree.Leaf(5);
    InterTree.Leaf leaf13 = new InterTree.Leaf(3);
    InterTree.Leaf leaf14 = new InterTree.Leaf(1);
    InterTree.Leaf leaf15 = new InterTree.Leaf(6);
    InterTree.Leaf leaf16 = new InterTree.Leaf(3);
    InterTree.Leaf leaf17 = new InterTree.Leaf(2);
    InterTree.Leaf leaf18 = new InterTree.Leaf(8);
    InterTree.Leaf leaf19 = new InterTree.Leaf(2);
    InterTree.Leaf leaf20 = new InterTree.Leaf(5);
    InterTree.Leaf leaf21 = new InterTree.Leaf(2);
    InterTree.Leaf leaf22 = new InterTree.Leaf(1);
    InterTree.Leaf leaf23 = new InterTree.Leaf(9);

    InterTree.Node node1 = new InterTree.Node(10);
    InterTree.Node node2 = new InterTree.Node(2);
    InterTree.Node node3 = new InterTree.Node(5);
    InterTree.Node node4 = new InterTree.Node(15);
    InterTree.Node node5 = new InterTree.Node(6);
    InterTree.Node node6 = new InterTree.Node(4);
    InterTree.Node node7 = new InterTree.Node(12);
    InterTree.Node node8 = new InterTree.Node(9);
    InterTree.Node node9 = new InterTree.Node(20);
    InterTree.Node node10 = new InterTree.Node(2);
    InterTree.Node node11 = new InterTree.Node(5);
    InterTree.Node node12 = new InterTree.Node(10);


    @Before
    public void beforeTest() {
        node1.addLeaf(leaf1);
        node1.addLeaf(leaf2);
        node1.addLeaf(leaf3);
        node1.addLeaf(leaf4);
        node1.addLeaf(leaf5);
        node1.addLeaf(leaf6);
        node1.addLeaf(leaf7);
        node1.addLeaf(leaf8);

        node2.addLeaf(leaf9);
        node2.addLeaf(leaf10);
        node2.addLeaf(leaf11);
        node2.addLeaf(leaf12);

        node5.addLeaf(leaf13);
        node5.addLeaf(leaf14);

        node6.addLeaf(leaf15);
        node6.addLeaf(leaf16);
        node6.addLeaf(leaf17);

        node7.addLeaf(leaf18);

        node8.addLeaf(leaf19);

        node11.addLeaf(leaf20);
        node11.addLeaf(leaf21);

        node12.addLeaf(leaf22);
        node12.addLeaf(leaf23);

        node1.addNode(node2);
        node1.addNode(node3);
        node1.addNode(node4);
        node2.addNode(node5);
        node5.addNode(node6);
        node6.addNode(node7);
        node6.addNode(node8);
        node4.addNode(node9);
        node4.addNode(node10);
        node10.addNode(node11);
        node10.addNode(node12);

        tree = new InterTree(node1);
    }

    @Test
    public void testAddLeaf() {

        InterTree.Node testNode1 = new InterTree.Node(10);
        InterTree.Node testNode2 = new InterTree.Node(1);

        InterTree.Leaf testLeaf1 = new InterTree.Leaf(2);
        InterTree.Leaf testLeaf2 = new InterTree.Leaf(1);
        InterTree.Leaf testLeaf3 = new InterTree.Leaf(3);
        InterTree.Leaf testLeaf4 = null;

        testNode1.addLeaf(testLeaf1);
        testNode1.addLeaf(testLeaf2);
        testNode1.addLeaf(testLeaf3);
        testNode1.addLeaf(testLeaf4);
        testNode2.addLeaf(testLeaf4);

        Assert.assertEquals(testLeaf1, testNode1.getLeaf());
        Assert.assertEquals(testLeaf2, testNode1.getLeaf().getNext());
        Assert.assertEquals(testLeaf3, testNode1.getLeaf().getNext().getNext());
        Assert.assertNull(testNode1.getLeaf().getNext().getNext().getNext());
        Assert.assertNull(testNode2.getLeaf());

    }

    @Test
    public void testAddNode() {

        InterTree.Node testNode1 = new InterTree.Node(10);
        InterTree.Node testNode2 = new InterTree.Node(1);
        InterTree.Node testNode3 = new InterTree.Node(45);
        InterTree.Node testNode4 = new InterTree.Node(5);

        testNode1.addNode(testNode2);
        testNode1.addNode(testNode3);
        testNode1.addNode(testNode4);

        Assert.assertEquals(testNode2, testNode1.getNodes().get(0));
        Assert.assertEquals(testNode3, testNode1.getNodes().get(1));
        Assert.assertEquals(testNode4, testNode1.getNodes().get(2));

    }

    @Test
    public void testSortLeaves() {

        node3.sortLeaves();
        node7.sortLeaves();
        node5.sortLeaves();
        node6.sortLeaves();
        node2.sortLeaves();
        node1.sortLeaves();

        Assert.assertNull(node3.getLeaf());

        Assert.assertEquals(8, node7.getLeaf().getWeight());

        Assert.assertEquals(1, node5.getLeaf().getWeight());
        Assert.assertEquals(3, node5.getLeaf().getNext().getWeight());

        Assert.assertEquals(2, node6.getLeaf().getWeight());
        Assert.assertEquals(3, node6.getLeaf().getNext().getWeight());
        Assert.assertEquals(6, node6.getLeaf().getNext().getNext().getWeight());

        Assert.assertEquals(2, node2.getLeaf().getWeight());
        Assert.assertEquals(4, node2.getLeaf().getNext().getWeight());
        Assert.assertEquals(5, node2.getLeaf().getNext().getNext().getWeight());
        Assert.assertEquals(12, node2.getLeaf().getNext().getNext().getNext().getWeight());

        Assert.assertEquals(1, node1.getLeaf().getWeight());
        Assert.assertEquals(1, node1.getLeaf().getNext().getWeight());
        Assert.assertEquals(2, node1.getLeaf().getNext().getNext().getWeight());
        Assert.assertEquals(3, node1.getLeaf().getNext().getNext().getNext().getWeight());
        Assert.assertEquals(4, node1.getLeaf().getNext().getNext().getNext().getNext().getWeight());
        Assert.assertEquals(5, node1.getLeaf().getNext().getNext().getNext().getNext().getNext().getWeight());
        Assert.assertEquals(6, node1.getLeaf().getNext().getNext().getNext().getNext().getNext().getNext().getWeight());
        Assert.assertEquals(7, node1.getLeaf().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getWeight());
    }

    @Test
    public void testCheckLeaves() {

        node3.sortLeaves();
        node7.sortLeaves();
        node5.sortLeaves();
        node6.sortLeaves();
        node1.sortLeaves();

        Assert.assertNull(node3.checkLeaves());

        Assert.assertNull(node7.checkLeaves());

        Assert.assertNull(node5.checkLeaves());

        InterTree.Leaf excessLeaves = node6.checkLeaves();
        Assert.assertEquals(3, excessLeaves.getWeight());
        Assert.assertEquals(6, excessLeaves.getNext().getWeight());
        Assert.assertNull(excessLeaves.getNext().getNext());

        excessLeaves = node1.checkLeaves();
        Assert.assertEquals(4, excessLeaves.getWeight());
        Assert.assertEquals(5, excessLeaves.getNext().getWeight());
        Assert.assertEquals(6, excessLeaves.getNext().getNext().getWeight());
        Assert.assertEquals(7, excessLeaves.getNext().getNext().getNext().getWeight());
    }

    @Test
    public void testNormalizeTree() {

        System.out.println("\n" + tree);

        InterTree.Leaf excessLeaves = tree.normalizeTree();

        Assert.assertEquals(9, excessLeaves.getWeight());
        Assert.assertEquals(12, excessLeaves.getNext().getWeight());
        Assert.assertNull(excessLeaves.getNext().getNext());

        System.out.println("\n" + tree);

    }

    @Test
    @Ignore //Optional. lasts 2 minutes.
    public void testWidthAndDepth() {

        InterTree.Node head = new InterTree.Node(4);

        // 3*10^6 * 100 leaves
        head = addNodes(head, 3, 6, 100);

        InterTree.Leaf excessLeaves = head.normalizeNode(null);

    }

    private InterTree.Node addNodes(InterTree.Node node, int width, int depth, int leaves) {

        node = addLeaves(node,leaves);
        for (int i = 0; i < width; i++) {
            InterTree.Node nodeAdded = new InterTree.Node(286);
            if (depth > 0) {
                nodeAdded = addNodes(nodeAdded, width, depth - 1, leaves);
            }
            nodeAdded = addLeaves(nodeAdded,leaves);
            node.addNode(nodeAdded);
        }

        return node;
    }

    private InterTree.Node addLeaves(InterTree.Node node, int leaves) {

        for (int j = 0; j < leaves; j++) {
            InterTree.Leaf leafAdded = new InterTree.Leaf(3);
            node.addLeaf(leafAdded);
        }

        return node;
    }
}
