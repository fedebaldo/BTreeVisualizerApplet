package b_trees_visualizer.graphic_interface.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class GradeListener implements ActionListener{
	private BTree<?> tree;
	private BTreeScrollPanel treePanel;
	private JTextField gradeTF;
	private ArrayList<Pair<Boolean, ?>> actions;
	private JLabel gradeL;

	public GradeListener(BTree<?> tree, BTreeScrollPanel treePanel, JTextField gradeTF, ArrayList<Pair<Boolean, ?>> actions, JLabel gradeL) {
		super();
		this.tree = tree;
		this.treePanel = treePanel;
		this.gradeTF = gradeTF;
		this.actions = actions;
		this.gradeL = gradeL;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			int temp = Integer.parseInt(gradeTF.getText());
			if (temp > 1) {
				tree.emptyTree();
				tree.setGrade(temp);
				// Rebuilds the tree with the new grade by adding and removing the keys in the same order
				// they were previously added/removed
				for (Pair<Boolean, ?> p : actions) {
					if (p.getFirst()) {
						((BTree<Integer>)tree).insertKey(((Pair<Boolean, Integer>)p).getSecond(), false);
					} else {
						((BTree<Integer>)tree).removeKey(((Pair<Boolean, Integer>)p).getSecond(), false);
					}
				}
				treePanel.defaultTree();
				treePanel.updateTreePanel(false, false);
				treePanel.repaint();
				treePanel.centreScrollPanel();
				gradeL.setText("Current grade: " + tree.getGrade());
				gradeTF.setText("");
				Applet.stepsP.clearStates();
				Applet.stepsP.repaint();
			} else {
				gradeTF.setText("");
				JOptionPane.showMessageDialog(new JFrame(), "The tree's grade must be higher than 1");
			}
		} catch (NumberFormatException e) {
		}
		gradeTF.grabFocus();
	}
}
