package b_trees_visualizer.graphic_interface.utility;

public class Pair<E, F> {
	
	private E first;
	private F second;
	
	public Pair(E first, F second) {
		this.first = first;
		this.second = second;
	}
	
	public E getFirst() {
		return first;
	}
	
	public F getSecond() {
		return second;
	}
}
