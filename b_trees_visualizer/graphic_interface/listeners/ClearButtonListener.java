package b_trees_visualizer.graphic_interface.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class ClearButtonListener implements ActionListener{

	private BTree<?> tree;
	private BTreeScrollPanel treePanel;
	private ArrayList<Pair<Boolean, ?>> actions;

	public ClearButtonListener(BTree<?> tree, BTreeScrollPanel treePanel, ArrayList<Pair<Boolean, ?>> actions) {
		super();
		this.tree = tree;
		this.treePanel = treePanel;
		this.actions = actions;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int clear = JOptionPane.showConfirmDialog(
					new JFrame()
					, "Delete all keys?"
					, "Clear B-Tree"
					, JOptionPane.YES_NO_OPTION);
		if (clear == 0) {
			tree.emptyTree();
			actions.clear();
			treePanel.defaultTree();
			treePanel.updateTreePanel(false, false);
			treePanel.repaint();
			Applet.stepsP.clearStates();
			Applet.stepsP.repaint();
		}
	}
}
