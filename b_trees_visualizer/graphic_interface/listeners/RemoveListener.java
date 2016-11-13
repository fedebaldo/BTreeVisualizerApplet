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

public class RemoveListener implements ActionListener {

	private BTree<?> tree;
	private BTreeScrollPanel treePanel;
	private JTextField remTF;
	private ArrayList<Pair<Boolean, ?>> actions;

	public RemoveListener(BTree<?> tree, BTreeScrollPanel treePanel, JTextField remTF, ArrayList<Pair<Boolean, ?>> actions) {
		super();
		this.tree = tree;
		this.treePanel = treePanel;
		this.remTF = remTF;
		this.actions = actions;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			Integer temp = Integer.parseInt(remTF.getText());
			int result = ((BTree<Integer>)tree).removeKey(temp, true);
			if (result == 0) {
				treePanel.defaultTree();
				treePanel.updateTreePanel(true, false);
				treePanel.repaint();
				actions.add(new Pair<Boolean, Integer>(false, temp));
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "The key was not found in the tree.");
			}
			remTF.setText("");
		} catch (NumberFormatException e) {
		}
		remTF.grabFocus();
	}
}
