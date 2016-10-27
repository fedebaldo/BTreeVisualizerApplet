package b_trees_visualizer.graphic_interface.custom_panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.listeners.StateListener;
import b_trees_visualizer.graphic_interface.utility.Pair;


public class StepsPanel extends JScrollPane {

	private BTree<?> visualizedTree;
	public static ArrayList<Pair<String, BTree<?>>> states;
	private BTreeScrollPanel treePanel;

	public StepsPanel(BTree<?> visualizedTree, BTreeScrollPanel treePanel) {
		super();
		this.visualizedTree = visualizedTree;
		this.treePanel = treePanel;
		states = new ArrayList<Pair<String, BTree<?>>>();

		// Set scrollbars behaviour
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

	public void saveTreeState(String string) {
		// A state is composed by a string that describes the tree state and
		// the tree itself
		BTree<?> newTree = visualizedTree.cloneTree();
		newTree.checkAndRemoveNullRoot();
		states.add(new Pair<String, BTree<?>>(string, newTree));
	}

	public void clearStates() {
		states.clear();
	}

	public String getStateLabel(int index) {
		return states.get(index).getFirst();
	}

	public BTree<?> getTree(int index) {
		return states.get(index).getSecond();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GridBagConstraints c = new GridBagConstraints();
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		// For each state a label is added to the panel. The label will have as text
		// the description of the state that will be painted if the label is clicked
		// with the mouse.
		int i = 0;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = Applet.defaultFontSize;
		c.ipady = Applet.defaultFontSize;
		for (i = 0; i < states.size(); i++) {
			JLabel label = new JLabel(Applet.stepsP.getStateLabel(i), SwingConstants.CENTER);
			label.setOpaque(true);
			label.setBackground(Color.white);
			label.setBorder(BorderFactory.createLineBorder(Color.black));
			label.setFont(label.getFont().deriveFont(Applet.fontSize));
			label.addMouseListener(new StateListener(treePanel, this, i));

			// Place the labels one over the other
			c.gridx = 0;
			c.gridy = i;
			p.add(label, c);
		}
		JLabel label = new JLabel("Final state", SwingConstants.CENTER);
		label.setOpaque(true);
		label.setBackground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setFont(label.getFont().deriveFont(Applet.fontSize));
		label.addMouseListener(new StateListener(treePanel, this, -1));
		c.gridx = 0;
		c.gridy = i;
		p.add(label, c);

		this.setViewportView(p);
	}
}
