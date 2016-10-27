import net.goui.util.MTRandom;
import java.util.Stack;

public class RandomBTreeGenerator {

	private BTree <Integer> tree;
	private final int MAX_KEY_NUMBER = 1000;
	private int grade;
	private Stack<Integer> stack;
	private MTRandom rand;

	public RandomBTreeGenerator (int grade) {

		this.grade = grade;
		this.tree = new BTree<Integer> (this.grade);
		this.rand = new MTRandom(System.currentTimeMillis());

	}

	public void next () {

		int key = rand.next() * MAX_KEY_NUMBER;
		this.tree.insertKey(key, false);
		stack.push(key);

	}

	public void previous () {

		Integer key = stack.pop();
		this.tree.removeKey(key, false);

	}


}