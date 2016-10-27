package b_trees_visualizer.graphic_interface.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class AddListener implements ActionListener {

	private BTree<?> tree;
	private BTreeScrollPanel treePanel;
	private JTextField addTF;
	private ArrayList<Pair<Boolean, ?>> actions;

	public AddListener(BTree<?> tree, BTreeScrollPanel treePanel, JTextField addTF, ArrayList<Pair<Boolean, ?>> actions) {
		super();
		this.tree = tree;
		this.treePanel = treePanel;
		this.addTF = addTF;
		this.actions = actions;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			// Tries to parse and Integer from the text field
			Integer temp = Integer.parseInt(addTF.getText());
			// Inserts the key in the tree. The result is 0 if successful, 1 if the key was already in the tree
			int result = ((BTree<Integer>)tree).insertKey(temp, true);
			if (result == 0) {
				// Set the visualized tree to the default one
				treePanel.defaultTree();
				// Updates the tree showing the differences but removing previous modifications
				treePanel.updateTreePanel(true, false);
				treePanel.repaint();
				// Adds a new pair to the actions with true for an insertion and temp as key
				actions.add(new Pair<Boolean, Integer>(true, temp));
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "The key is already in the tree.");
			}
			addTF.setText("");
		} catch (NumberFormatException e) {
		}
		addTF.grabFocus();
	}
}
