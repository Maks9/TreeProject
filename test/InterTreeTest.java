import org.junit.Assert;
import org.junit.Test;

public class InterTreeTest {


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
        Assert.assertEquals(null, testNode1.getLeaf().getNext().getNext().getNext());
        Assert.assertEquals(null, testNode2.getLeaf());

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
}
