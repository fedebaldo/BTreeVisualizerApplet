package b_trees_visualizer.data_structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BTreeNodeTest {

	@Test
	public void testAddKeySorted() {
		BTreeNode<Integer> g = new BTreeNode<Integer>();
		g.addKey(9, false);
		for(int i = 0; i < 9; i++) {
			g.addKey(i+10, false);
		}
		assertEquals(g.getNumberOfElements(), 10);
		
		boolean sorted = true;
		for (int i = 0; i < g.getData().size() - 1; i++) {
			sorted &= g.getData().get(i).compareTo(g.getData().get(i + 1)) <= 0;
		}
		assertTrue(sorted);
	}
	
	@Test
	public void testSplit() {
		BTreeNode<Integer> g = new BTreeNode<Integer>();
		BTreeNode<Integer> h = new BTreeNode<Integer>();
		BTreeNode<Integer> i = new BTreeNode<Integer>();
		g.addKey(1, false);
		g.addKey(2, false);		
		g.addKey(3, false);
		g.addKey(4, false);
		h.addKey(5, false);
		i.addKey(6, false);
		i.addKey(7, false);
		i.addKey(8, false);
		i.addKey(9, false);
		h.setLeaf(false);
		h.addChild(g);
		h.addChild(i);
		
		BTreeNode<Integer> a = new BTreeNode<Integer>();
		BTreeNode<Integer> b = new BTreeNode<Integer>();
		for (int j = 1; j <= 9; j++) {
			b.addKey(j, false);
		}
		a.addChild(b);
		a.setLeaf(false);
		b.nodeSplit(a, 0, 5, false);
		assertTrue(h.equals(a));
	}
}
