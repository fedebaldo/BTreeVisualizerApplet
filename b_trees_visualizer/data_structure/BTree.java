package b_trees_visualizer.data_structure;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import b_trees_visualizer.graphic_interface.Applet;

/** MISSION of this class is to provide ADT for BTree**/

public class BTree<E extends Comparable<? super E>> {

	private int grade;
	private Random rand;
	private Stack<Integer> stack;
	private BTreeNode<E> root;
	private final int MAX_NUMBER_KEYS = 1000;

	/**
	** @param grade: Integer
	** @throws Exception : if the grade of the BTree is < 2
	** EFFECT produce a BTree of this.grade = grade.
	** For each node the number of keys is <= 2*this.grade
	** and > t-1
	**/
	public BTree(int grade) {

		try {
			if (grade < 2) {
				throw new Exception();
			}

			this.grade = grade;
			/* used to produce random insertion*/
			this.rand = new Random(System.currentTimeMillis());
			this.stack = new Stack<Integer>();
			root = new BTreeNode<E>();

		} catch (Exception e) {

			System.err.println("The tree's grade must be higher than or equal to 2");

		}
	}

	/**
	** @param data: an ArrayList of integer reprensenting the keys
	** of the root
	**/
	public BTree (ArrayList<E> data) {

		this.root = new BTreeNode<E> (data);

	}

	/**
	** EFFECT produce this with the insertion
	** of a random integer in [0,999]
	**/
	public void next () {

		int key = rand.nextInt(MAX_NUMBER_KEYS);
		stack.push(key);
		((BTree<Integer>)this).insertKey(key,true);

	}

	/**
	** @throws NullPointerException : if the BTree is empty
	** there aren't previous configuration
	** EFFECT produce this without the last element added
	**/
	public void previous () throws NullPointerException {

		if (stack.isEmpty()) {

			throw new NullPointerException();

		}

		int key = stack.pop();
		((BTree<Integer>)this).removeKey(key,true);

	}

	/**
	** @return clonedBTree : a BTree
	** EFFECT produce a copy of this
	**/
	public BTree<E> cloneTree() {
		BTree<E> clonedTree = new BTree<E>(grade);
		clonedTree.setRoot(root.cloneNode());
		return clonedTree;
	}

	/**
	** @return this.grade, Integer
	**/
	public int getGrade() {
		return grade;
	}

	/**
	** @param grade :  integer
	** EFFECT set this.grade = grade
	**/
	public void setGrade(int grade) {
		if (grade > 1) {
			this.grade = grade;
		}
	}

	/**
	** @return root : BTreeNode
	**/
	public BTreeNode<E> getRoot() {
		return root;
	}

	/**
	** @param root  a BTreeNode<E>
	** EFFECT change this.root in root
	**/
	public void setRoot(BTreeNode<E> root) {
		this.root = root;
	}

	/**
	** @param element : an object E
	** @return the BTreeNode containig E
	**/
	public BTreeNode<E> search(E element) {
		return root.search(element);
	}

	/**
	** @param element : an object E,
	** the same of the element in this
	** @param trackSteps : boolean
	** @return 1 if the insertion is competed succesfully
	** 0 otherwise
	** EFFECT insert element in this, if a node is full it's splitted
	**/
	public int insertKey(E element, boolean trackSteps) {
		/* The element is added to the tree only if there isn't already a key with the same value
		 * in the tree. The search method is used in order to not change the tree structure with
		 * the changes that are done while looking for the position to insert the key in.
		 */
		if (root.search(element) == null) {
			//If the root is full, a new root is made by doing a split on the old one.
			if (root.getNumberOfElements() == 2*grade - 1) {
				if (trackSteps) {
					Applet.stepsP.clearStates();
					root.setFocus(true);
					Applet.stepsP.saveTreeState("Initial state");
					root.setFocus(false);
				}
				BTreeNode<E> newRoot = new BTreeNode<E>();
				newRoot.addChild(root);
				newRoot.setLeaf(false);
				BTreeNode<E> oldRoot = root;
				root = newRoot;
				if (trackSteps) { oldRoot.setFocus(true); }
				oldRoot.nodeSplit(newRoot, 0, grade, trackSteps);
				if (trackSteps) { oldRoot.setFocus(false); }
			} else {
				if (trackSteps) {
					Applet.stepsP.clearStates();
					Applet.stepsP.saveTreeState("Initial state");
				}
			}
			root.insertKey(element, grade, trackSteps);
			if (trackSteps) {
				Applet.stepsP.repaint();
			}
			stack.push((Integer) element);
			return 0;
		} else {
			return 1;
		}
	}

	/**
	** @param element : an object E,
	** the same of the element in this
	** @param trackSteps : boolean
	** @return 1 if the elimination of element is completed succesfully
	** 0 otherwise
	** EFFECT remove element from this
	**/
	public int removeKey(E element, boolean trackSteps) {
		/* If there isn't the element in the tree, the removeKey method is not
		 * called to avoid the modifications on the structure that are done
		 * while searching for the key to remove.
		 */
		if (!(root.search(element) == null)) {
			if (trackSteps) {
				Applet.stepsP.clearStates();
				Applet.stepsP.saveTreeState("Initial state");
			}

			root.removeKey(element, grade, trackSteps);
			checkAndRemoveNullRoot();

			if (trackSteps) {
				Applet.stepsP.repaint();
			}
			stack.remove((Integer) element);
			return 0;
		} else {
			return 1;
		}
	}

	/**
	** EFFECT check if the root is null, in this case remove it
	**/
	public void checkAndRemoveNullRoot() {
		if (!root.isLeaf() && root.getNumberOfElements() == 0) {
			root = root.extractChild(0);
		}
	}

	/**
	** @return the String containig the grade and
	** the elements in this.
	**/
	public String toString() {
		return "Grade: " + grade + "\n" + root.toString();
	}

	/**
	** @return the String containig the grade and
	** the elements in this ordered.
	**/
	public String toStringInOrder() {
		return "Grade: " + grade + "\n" + root.toStringInOrder();
	}

	/**
	** @return a boolean. true if the property of BTree are respected in this
	** false otherwise
	**/
	public boolean checkBTree() {
		// The root is checked separately due to the different properties on the
		// data boundaries
		boolean ordered = true;
		ArrayList<E> l = root.toInOrderArrayList();

		boolean temp = !(root.isLeaf() ^ root.getChildren().isEmpty());
		temp &= root.getNumberOfElements() == root.getData().size();
		temp &= root.getNumberOfElements() >= 1 && root.getNumberOfElements() <= 2*grade;

		if (!root.isLeaf()) {
			temp &= root.getNumberOfElements() + 1 == root.getChildren().size();
		}

		for (int i = 0; i < root.getChildren().size(); i++) {
			temp &= root.getChild(i).checkNode(grade);
		}

		// A method that goes through the whole tree in-order is used to check
		// whether the keys in the tree are all following a non-decreasing order.
		for (int i = 0; i < l.size() - 1; i++) {
			ordered &= l.get(i).compareTo(l.get(i + 1)) <= 0;
		}

		return temp && ordered;
	}

	/**
	** @return an integer representing the height of this
	**/
	public int getHeight() {
		return root.getHeightSubtree();
	}

	/**
	** @return true if this is empty
	** false otherwise
	**/
	public boolean isEmpty() {
		return root.getNumberOfElements() == 0;
	}

	/**
	** EFFECT create a new empty BTree
	**/
	public void emptyTree() {
		root = new BTreeNode<E>();
	}
}
