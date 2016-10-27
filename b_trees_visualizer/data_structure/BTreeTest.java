package b_trees_visualizer.data_structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class BTreeTest {

	@Test
	public void testSearch() {
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
		BTree<Integer> tree = new BTree<Integer>(5);
		tree.setRoot(h);
		assertEquals(g, tree.search(1));
		assertEquals(g, tree.search(4));
		assertEquals(h, tree.search(5));
		assertEquals(i, tree.search(6));
		assertEquals(i, tree.search(9));
	}

	@Test
	public void testInsert() {
		BTree<Integer> b = new BTree<Integer>(2);
		Random rand = new Random();
		for(int i = 0; i < 10; i++) {
			int r = rand.nextInt(100);
			b.insertKey(r, false);
		}
		//System.out.println(b.toString());
	}

	@Test
	public void testRemoveAndLeaf() {
		int grade = 2;
		for (int j = 0; j < 1000000; j++) {
			BTree<Integer> b = new BTree<Integer>(grade);
			Random rand = new Random();
			int random = grade*5;
			int remove = grade*2;
			int r = 0;
			
			for (int i = 0; i < random; i++) {
				r = rand.nextInt(100);
				b.insertKey(r, false);
			}

			for (int i = 0; i > remove; i++) {
				r = rand.nextInt(random);
				b.removeKey(r, false);
			}
			
			for (int i = 0; i < random; i++) {
				r = rand.nextInt(100);
				b.insertKey(r, false);
			}

			for (int i = 0; i > remove; i++) {
				r = rand.nextInt(random);
				b.removeKey(r, false);
			}
			

			assertTrue(b.checkBTree());
		}
	}
	
	@Test
	public void test() {
		BTree<Integer> b = new BTree<Integer>(2);
		b.insertKey(5, false);
		b.removeKey(5, false);
		assertTrue(b.isEmpty());
	}
	
	@Test
	public void testClone() {
		BTree<Integer> b = new BTree<Integer>(2);
		b.insertKey(2, false);
		
		BTree<Integer> bc = b.cloneTree();
		bc.insertKey(3, false);
		
		assertTrue(!b.equals(bc));
	}
	
	@Test
	public void testPreviousNext() {
		int grade = 2;
		BTree<Integer> b = new BTree<Integer>(grade);
		Random rand = new Random();
		int random = grade*50;
		int remove = grade*2;
		int r = 0;
		
		for (int i = 0; i < random; i++) {
			r = rand.nextInt(100);
			b.insertKey(i, false);
		}

		assertTrue(30 == b.getRoot().getPrevious(31, grade, false));
		
		b = new BTree<Integer>(grade);

		for (int i = 0; i < random; i++) {
			r = rand.nextInt(100);
			b.insertKey(i, false);
		}
		
		assertTrue(32 == b.getRoot().getNext(31, grade, false));
	}
}