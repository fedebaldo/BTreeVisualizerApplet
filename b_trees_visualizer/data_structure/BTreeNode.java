package b_trees_visualizer.data_structure;

import java.util.ArrayList;

import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class BTreeNode<E extends Comparable<? super E>> {

	private int numberOfElements;

	private ArrayList<E> data;
	private ArrayList<BTreeNode<E>> children;

	private ArrayList<Pair<Boolean, E>> recentModifications;
	private ArrayList<Pair<Boolean, E>> previousModifications;

	private boolean leaf;
	private boolean hasFocus;

	/**
	** EFFECT produce a Node for BTree
	**/
	public BTreeNode() {
		numberOfElements = 0;

		data = new ArrayList<E>();
		children = new ArrayList<BTreeNode<E>>();

		recentModifications = new ArrayList<Pair<Boolean, E>>();
		previousModifications = new ArrayList<Pair<Boolean, E>>();

		leaf = true;
		hasFocus = false;
	}

	/**
	** @param data : an ArrayList of integer representing the keys
	** EFFECT create a BTree node with the data in the same order
	** they are presented in data
	**/
	public BTreeNode (ArrayList<E> data) {

		this.numberOfElements = data.size();

		this.data = data;
		this.children = new ArrayList<BTreeNode<E>>(this.data.size());

		recentModifications = new ArrayList<Pair<Boolean, E>>();
		previousModifications = new ArrayList<Pair<Boolean, E>>();

		leaf = true;
		hasFocus = false;

	}

	/**
	** @return the copy of this
	**/
	public BTreeNode<E> cloneNode() {
		BTreeNode<E> clonedNode = new BTreeNode<E>();
		clonedNode.setNumberOfElements(numberOfElements);

		clonedNode.data = (ArrayList<E>)data.clone();
		for (int i = 0; i < children.size(); i++) {
			clonedNode.children.add(children.get(i).cloneNode());
		}

		clonedNode.recentModifications = (ArrayList<Pair<Boolean, E>>)recentModifications.clone();
		clonedNode.previousModifications = (ArrayList<Pair<Boolean, E>>)previousModifications.clone();

		clonedNode.setLeaf(leaf);
		clonedNode.setFocus(hasFocus);
		return clonedNode;
	}

	/**
	** @return true if the this is a leaf
	** false otherwise
	**/
	public boolean isLeaf() {
		return leaf;
	}

	/**
	** @param leaf boolean
	** EFFECT set the value this.leaf = leaf
	**/
	protected void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isFocused() {
		return hasFocus;
	}

	protected void setFocus(boolean hasFocus) {
		this.hasFocus = hasFocus;
	}

	/**
	** @return the number of elements in this
	**/
	public int getNumberOfElements() {
		return numberOfElements;
	}

	/**
	** @param numberOfElements an integer
	** EFFECT set this.numberOfElements = numberOfElements
	**/
	protected void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	/**
	** @return true if this was recently modified
	**/
	public boolean isRecentlyModified() {
		return !recentModifications.isEmpty();
	}

	/**
	** @param i : integer
	** @return the Pair containing the recent modification in i
	**/
	public Pair<Boolean, E> getModification(int index) {
		return recentModifications.get(index);
	}

	/**
	** @return all the recentModifications
	**/
	public ArrayList<Pair<Boolean, E>> getModifications() {
		return recentModifications;
	}

	/**
	** @param modifications : ArrayList
	** EFFECT set a new ArrayList of recentModifications
	**/
	public void setRecentModifications(ArrayList<?> modifications) {
		recentModifications.clear();
		recentModifications.addAll((ArrayList<Pair<Boolean, E>>)modifications);
	}

	/**
	** @param modification a Pair representing the modification
	** EFFECT add a modification the recentModifications
	**/
	public void addModification(Pair<Boolean, E> modification) {
		recentModifications.add(modification);
	}

	/**
	** EFFECT remove all the recentModifications
	**/
	public void removeModifications() {
		recentModifications.clear();
	}

	/**
	** @return true if this was previously modified
	**/
	public boolean isPreviouslyModified() {
		return !previousModifications.isEmpty();
	}

	/**
	** @return all the previousModifications
	**/
	public ArrayList<Pair<Boolean, E>> getPreviousModifications() {
		return previousModifications;
	}

	/**
	** @param modifications : ArrayList
	** EFFECT set a new ArrayList of previousModifications
	**/
	public void setPreviouslyModified(ArrayList<?> modifications) {
		previousModifications.clear();
		previousModifications.addAll((ArrayList<Pair<Boolean, E>>)modifications);
	}

	/**
	** EFFECT remove all the previousModifications
	**/
	public void removePreviousModifications() {
		previousModifications.clear();
	}

	/**
	** @return all the keys added previously or recently to this
	**/
	public ArrayList<E> getAddedKeys() {
		ArrayList<E> add = new ArrayList<E>();
		if (!recentModifications.isEmpty()) {
			for (Pair<Boolean, E> p : recentModifications) {
				if (p.getFirst()) {
					add.add(p.getSecond());
				}
			}
		} else if (!previousModifications.isEmpty()) {
			for (Pair<Boolean, E> p : previousModifications) {
				if (p.getFirst()) {
					add.add(p.getSecond());
				}
			}
		}
		return add;
	}

	/**
	** @return true if there's at least one modification that is a remove
	**/
	public boolean hasRemovedKeys() {
		if (!recentModifications.isEmpty()) {
			for (Pair<Boolean, E> p : recentModifications) {
				if (!p.getFirst()) {
					return true;
				}
			}
		} else if (!previousModifications.isEmpty()) {
			for (Pair<Boolean, E> p : previousModifications) {
				if (!p.getFirst()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	** @return the ArrayList containig the keys in this
	**/
	protected ArrayList<E> getData() {
		return data;
	}

	/**
	** @param index : integer
	** @return the element index in data,
	** the key in position index in this.
	**/
	public E getKey(int index) {
		return data.get(index);
	}

	/**
	** @param index an integer
	** @param key, an object E the same of BTreeNode<E>
	** @param trackSteps a boolean
	** EFFECT set the key in position index in this
	**/
	public void setKey(int index, E key, boolean trackSteps) {
		if (trackSteps) {
			addModification(new Pair<Boolean, E>(false, getKey(index)));
			addModification(new Pair<Boolean, E>(true, key));
		}

		data.set(index, key);
	}

	/**
	** @param data2, an ArrayList of element E, the same type
	** of the object in BTreeNode
	** EFFECT appends data2 to this.data
	**/
	public void mergeData(ArrayList<E> data2) {
		data.addAll(data2);
		numberOfElements += data2.size();
	}

	/**
	** @param key, an object of type E the same of the element in
	** BTreeNode
	** @param trackSteps a boolean
	** EFFECT add key in this preserving the order
	**/
	public void addKey(E key, boolean trackSteps) {
		int index = 0;
		while (index < numberOfElements && getKey(index).compareTo(key) < 0) {
			index++;
		}
		data.add(index, key);
		numberOfElements++;

		if (trackSteps) {
			addModification(new Pair<Boolean, E>(true, key));
		}
	}

	/**
	** @param index an integer
	** @param trackSteps a boolean
	** @return the key in position index in this
	**/
	public E extractKey(int index, boolean trackSteps) {
		E e = data.remove(index);
		if (!e.equals(null)) {
			numberOfElements--;
			if (trackSteps) {
				addModification(new Pair<Boolean, E>(false, e));
			}
		}

		return e;
	}

	/**
	** @param trackSteps a boolean
	** @return the last key in this
	**/
	public E extractLastKey(boolean trackSteps) {
		return extractKey(numberOfElements - 1, trackSteps);
	}

//CHILDREN

	public BTreeNode<E> getChild(int index) {
		try {
			return children.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Invalid index on getChild");
			return null;
		}
	}

	public void addChild(BTreeNode<E> child) {
		try {
			E key = child.getData().get(child.getData().size() - 1);
			int i = 0;
			while (i < children.size() && getChild(i).getData().get(0).compareTo(key) < 0) {
				i++;
			}
			children.add(i, child);
		} catch (NullPointerException e) {
			System.err.println("The node given as child is null");
		}
	}
	/**
	** @param i : integer
	** @param data : an ArrayList of integer representing the data of the child
	** EFFECT add the child in position i in this.children
	**/
	public void addChild (int i, ArrayList<E> data) {

		BTreeNode child = new BTreeNode (data);
		child.setLeaf(true);

		this.children.set(i, child);
	}

	public BTreeNode<E> extractChild(int index) {
		return children.remove(index);
	}

	public BTreeNode<E> extractLastChild() {
		return children.remove(children.size() - 1);
	}

	protected ArrayList<BTreeNode<E>> getChildren() {
		return children;
	}

//Utility
	public int getHeightSubtree() {
		int h = 0;
		if (!leaf) {
			h = getChild(0).getHeightSubtree();
		}
		return 1 + h;
	}

	public boolean checkNode(int grade) {
		// if it's a leaf it must not have children and opposite
		boolean temp = !(leaf ^ children.isEmpty());
		// the number of elements must be equal to the data size
		temp &= numberOfElements == data.size();
		// internal nodes must have between grade - 1 and 2*grade - 1 elements
		temp &= numberOfElements >= grade - 1 && numberOfElements <= 2*grade - 1;

		// the number of children must be equal to the number of elements + 1
		if (!leaf) {
			temp &= numberOfElements + 1 == children.size();
		}

		//recursive check on child nodes
		for (int i = 0; i < children.size(); i++) {
			temp &= getChild(i).checkNode(grade);
		}

		return temp;
	}

	public boolean equals(BTreeNode<E> node) {
		boolean result = data.equals(node.getData());
		if (result && !leaf) {
			for (int i = 0; i < children.size(); i++) {
				result &= getChild(i).equals(node.getChild(i));
			}
		}
		return result;
	}

	public String toString() {
		String temp = "(";
		for(BTreeNode<E> n : children) {
			temp += n.toString();
		}
		return data.toString() + temp + ")";
	}

	public String toStringInOrder() {
		String temp = "";
		for (int i = 0; i < data.size(); i++) {
			if (!children.isEmpty()) {
				temp += getChild(i).toStringInOrder();
			}
			temp += getKey(i).toString() + " ";
		}
		if (!children.isEmpty()) {
			temp += getChild(children.size() - 1).toStringInOrder();
		}
		return temp;
	}

	public ArrayList<E> toInOrderArrayList() {
		ArrayList<E> temp = new ArrayList<E>();
		for (int i = 0; i < getNumberOfElements(); i++) {
			if (!children.isEmpty()) {
				temp.addAll(getChild(i).toInOrderArrayList());
			}
			temp.add(getKey(i));
		}
		if (!children.isEmpty()) {
			temp.addAll(getChild(children.size() - 1).toInOrderArrayList());
		}
		return temp;
	}

	/**
	 * @param x
	 * @return node that contains the searched key if found, otherwise null
	 * Search for a value in the node and returns the node if the value is in it,
	 * otherwise it either returns null if the node it's a leaf or recursively call
	 * the search on the child node in position i.
	 */
	public BTreeNode<E> search(E x) {
		int i = 0;
		//Search until the end of the array or the searched key is smaller
		//than the one in position i
		while(i < data.size() && getKey(i).compareTo(x) < 0) {
			i++;
		}

		//If the key in position i is the searched value, return the node,
		if (i < numberOfElements && getKey(i).equals(x)) {
			return this;
		}

		//otherwise if the node is a leaf return null.
		if (leaf) {
			return null;
		}

		//If the node is not a leaf recursively search on the i-th child.
		return getChild(i).search(x);
	}

	/**
	 * @param key
	 * Key to insert in the node's tree.
	 * @param grade
	 * Grade of the tree.
	 *
	 * If the node is a leaf, then it simply adds the key to the node's data array.
	 * If the node is not a leaf, it needs to find the node to put the key in. While doing
	 * so, every time a full node is found (with number of elements equal to 2*grade - 1),
	 * a split is done before continuing with the insertion.
	 */
	public void insertKey(E key, int grade, boolean trackSteps) {
		if (trackSteps) { setFocus(true); }
		if (leaf) {
			if (trackSteps) {
				Applet.stepsP.saveTreeState("Search path");
			}
			addKey(key, trackSteps);
			if (trackSteps) {
				Applet.stepsP.saveTreeState("Insert Key");
				setFocus(false);
			}
		} else {
			int index = 0;
			/* The position is chosen by comparing the elements of the data array with
			 * the key that needs to be added to the tree. If the key at the index
			 * position is greater than the key, the child at the index position will
			 * be the one in which the recursive call will be made. As there is always one
			 * more child than the number of elements in the data array, if every key is
			 * greater than the key it will be added to the last child at position
			 * data.size().
			 */
			while (index < numberOfElements && getKey(index).compareTo(key) < 0) {
				index++;
			}

			if (trackSteps) {
				Applet.stepsP.saveTreeState("Search Path");
				setFocus(false);
			}

			BTreeNode<E> child = getChild(index);
			if (child.getNumberOfElements() < 2*grade - 1) {
				child.insertKey(key, grade, trackSteps);
			} else {
				child.nodeSplit(this, index, grade, trackSteps);
				if (getKey(index).compareTo(key) <= 0) {
					getChild(index + 1).insertKey(key, grade, trackSteps);
				} else {
					getChild(index).insertKey(key, grade, trackSteps);
				}
			}
		}
	}

	/**
	 * @param parent
	 * @param index
	 * @param grade
	 *
	 * Given that there are 2*grade - 1 elements in the node, it splits it
	 * in 2 halves and adds the central key (there's always a central
	 * key as the max number of elements is always odd) to the parent
	 * data array.
	 *
	 * ExtractKey already decreases numberOfElements so there's no need to
	 * change it.
	 */
	public void nodeSplit(BTreeNode<E> parent, int index, int grade, boolean trackSteps) {
		if (numberOfElements == 2*grade - 1) {
			BTreeNode<E> secondHalf = new BTreeNode<E>();
			/* Grade is used as index as it's the central + 1 position when the array
			 * is full. The index doesn't need to change as the key is directly removed
			 * from the array, making the successive key step back to the "grade" position.
			 */
			for (int i = 0; i < grade - 1; i++) {
				secondHalf.addKey(extractKey(grade, trackSteps), trackSteps);
			}
			secondHalf.setLeaf(leaf);
			if (!leaf) {
				for (int i = 0; i < grade; i++) {
					secondHalf.getChildren().add(children.remove(grade));
				}
			}

			parent.setLeaf(false);
			parent.getData().add(index, extractKey(grade - 1, trackSteps));
			parent.getChildren().add(index + 1, secondHalf);
			parent.setNumberOfElements(parent.getNumberOfElements() + 1);

			if (trackSteps) {
				parent.addModification(new Pair<Boolean, E>(true, parent.getData().get(index)));
				Applet.stepsP.saveTreeState("Node Split");
			}
		}
	}

	/**
	 * @param key
	 * @param grade
	 * The method removeKey works recursively and has 2 cases:
	 * 1) the node on which the method is executed contains the key. When that happens:
	 * 		a) if the node is a leaf the key is removed. This can only happen on a
	 * 		   node with at least grade keys.
	 * 		b) the node isn't a leaf, then there are other 3 possibilities based on the children
	 * 		   relative to the position index of the key that needs to be removed:
	 * 			- the left child has at least grade keys, so we replace the key that has to
	 * 			  be removed with the key previous to that.
	 * 			- the left child has grade - 1 keys and the right one has at least grade
	 * 			  keys, so we replace the key with its successor.
	 * 			- neither of both children has at least grade keys so we merge them and put the
	 * 			  key that needs to be removed in the middle; after that, the recursive call is
	 * 			  done on the result of the merging
	 * 2) if the node doesn't contain the key, the method checkAndFixPath is called on the
	 * 	  child that is the root of the subtree that should contain the key followed by the
	 * 	  recursive call of removeKey.
	 */
	public void removeKey(E key, int grade, boolean trackSteps) {
		if (trackSteps) { setFocus(true); }
		int index = 0;
		while (index < numberOfElements && getKey(index).compareTo(key) < 0){
			index++;
		}
		if (index < numberOfElements && getKey(index).equals(key)) {
			if (leaf) {
				if (trackSteps) {
					Applet.stepsP.saveTreeState("Search path");
				}
				this.extractKey(index, trackSteps);
				if (trackSteps) {
					Applet.stepsP.saveTreeState("Remove key");
					setFocus(false);
				}
			} else {
				if (trackSteps) { Applet.stepsP.saveTreeState("Search path"); }
				BTreeNode<E> left = getChild(index);
				BTreeNode<E> right = getChild(index + 1);
				if (left.getNumberOfElements() >= grade) {
					setKey(index, getPrevious(key, grade, trackSteps), trackSteps);
					if (trackSteps) {
						Applet.stepsP.saveTreeState("Switch with previous");
						setFocus(false);
					}
				} else if (right.getNumberOfElements() >= grade) {
					setKey(index, getNext(key, grade, trackSteps), trackSteps);
					if (trackSteps) {
						Applet.stepsP.saveTreeState("Switch with next");
						setFocus(false);
					}
				} else {
					setFocus(false);
					this.nodeMerge(index, left, right, grade, trackSteps);
					left.removeKey(key, grade, trackSteps);
				}
			}
		} else {
			if (!leaf) {
				if (trackSteps) {
					Applet.stepsP.saveTreeState("Search path");
					setFocus(false);
				}
				checkAndFixPath(index, key, grade, trackSteps).removeKey(key, grade, trackSteps);
			}
		}
	}

	/**
	 * @param indexEl
	 * @param childL
	 * @param childR
	 * @param grade
	 * @param trackSteps
	 * nodeMerge takes the key in position indexEl and two nodes and merges them in a single node,
	 * also merging the children.
	 */
	public void nodeMerge(int indexEl, BTreeNode<E> childL, BTreeNode<E> childR, int grade, boolean trackSteps) {
		if (childL.getNumberOfElements() == grade - 1 && childR.getNumberOfElements() == grade - 1) {
			E temp = extractKey(indexEl, trackSteps);
			childL.addKey(temp, trackSteps);
			childL.mergeData(childR.getData());
			childL.getChildren().addAll(childR.getChildren());
			children.remove(childR);

			if (trackSteps) {
				childL.addModification(new Pair<Boolean, E>(true, temp));
				addModification(new Pair<Boolean, E>(false, temp));
				Applet.stepsP.saveTreeState("Node Merge");
			}
		}
	}

	/**
	 * Method that applies merging and exchange of keys between nodes if the
	 * node we have to work in has grade - 1 elements and we need to remove
	 * elements from the tree. There are two branches, of which the second one
	 * has 3 cases:
	 * 1) the child node has at least grade keys, so we leave it like it is
	 * 2) the child node has grade - 1 keys
	 * 		a) if the index is greater than 0 and his left sibling has at least grade
	 * 		   keys, then we move the key on position index - 1 from data to the child node
	 * 		   and replace it with the last key of his left sibling
	 * 		b) if the index is smaller than the number of keys in data, the left sibling also
	 * 		   has grade - 1 keys and the right sibling has at least grade keys, then we take
	 * 		   the key to replace the one in data from it.
	 * 		c) if both the closest siblings have grade - 1 keys, we merge the child with its
	 * 		   left sibling (with the right if the considered child is in position 0).
	 */
	public BTreeNode<E> checkAndFixPath(int index, E key, int grade, boolean trackSteps) {
		BTreeNode<E> child = getChild(index);
		if (child.getNumberOfElements() >= grade) {
			return child;
		} else { //2
			if (index > 0 && getChild(index - 1).getNumberOfElements() >= grade) { //a
				child.addKey(getKey(index - 1), trackSteps);
				setKey(index - 1, getChild(index - 1).extractLastKey(trackSteps), trackSteps);
				if (!getChild(index - 1).isLeaf()) {
					child.addChild(getChild(index - 1).extractLastChild());
				}
				if (trackSteps) { Applet.stepsP.saveTreeState("Rotate right"); }
				return child;
			} else if (index < numberOfElements && getChild(index + 1).getNumberOfElements() >= grade) { //b
				child.addKey(getKey(index), trackSteps);
				setKey(index,  getChild(index + 1).extractKey(0, trackSteps), trackSteps);
				if (!getChild(index + 1).isLeaf()) {
					child.addChild(getChild(index + 1).extractChild(0));
				}
				if (trackSteps) { Applet.stepsP.saveTreeState("Rotate left"); }
				return child;
			} else { //c
				if (index > 0) {
					nodeMerge(index - 1, getChild(index - 1), child, grade, trackSteps);
					return getChild(index - 1);
				} else {
					nodeMerge(index, child, getChild(index + 1), grade, trackSteps);
					return child;
				}
			}
		}
	}

	// Removes the greatest key in the tree which is smaller than key and returns it.
	public E getPrevious(E key, int grade, boolean trackSteps) {
		/* This method will never be called on a leaf directly, so it's not necessary
		 * to find the previous key by searching in the node but it will always be
		 * in the last position of data.
		 */
		if (leaf && !data.contains((E)key)) {
			return extractKey(data.size() - 1, trackSteps);
		} else if (leaf) {
			int index = 0;
			while (index < data.size() && getKey(index).compareTo(key) < 0){
				index++;
			}
			index--;
			return extractKey(index, trackSteps);
		} else {
			// Searches for the index that points to the child in which the method will continue
			int index = 0;
			while (index < data.size() && getKey(index).compareTo(key) < 0){
				index++;
			}

			// If the selected child has less than grade elements, it's fixed so it has at least grade
			// elements
			if (getChild(index).getNumberOfElements() >= grade) {
				return getChild(index).getPrevious(key, grade, trackSteps);
			} else {
				return this.checkAndFixPath(index, key, grade, trackSteps).getPrevious(key, grade, trackSteps);
			}
		}
	}

	// Removes the smallest key in the tree which is greater than the key and returns it
	public E getNext(E key, int grade, boolean trackSteps) {
		/* This method will never be called on a leaf directly, so it's not necessary
		 * to find the next key by searching in the node but it will always be
		 * in the first position of data.
		 */
		if (leaf && !data.contains((E)key)) {
			return extractKey(0, trackSteps);
		} else if (leaf) {
			int index = 0;
			while (index < data.size() && getKey(index).compareTo(key) <= 0){
				index++;
			}
			return extractKey(index, trackSteps);
		} else {
			// Searches for the index that points to the child in which the method will continue
			int index = 0;
			while (index < data.size() && getKey(index).compareTo(key) <= 0){
				index++;
			}

			// If the selected child has less than grade elements, it's fixed so it has at least grade
			// elements
			if (getChild(index).getNumberOfElements() >= grade) {
				return getChild(index).getNext(key, grade, trackSteps);
			} else {
				return this.checkAndFixPath(index, key, grade, trackSteps).getNext(key, grade, trackSteps);
			}
		}
	}
}
