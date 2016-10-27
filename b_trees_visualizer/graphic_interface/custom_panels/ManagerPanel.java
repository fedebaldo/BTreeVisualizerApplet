package b_trees_visualizer.graphic_interface.custom_panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.manager_panels.GradePanel;
import b_trees_visualizer.graphic_interface.custom_panels.manager_panels.InsertPanel;
import b_trees_visualizer.graphic_interface.custom_panels.manager_panels.RemovePanel;
import b_trees_visualizer.graphic_interface.custom_panels.manager_panels.ZoomPanel;
import b_trees_visualizer.graphic_interface.custom_panels.manager_panels.NextPreviousPanel;
import b_trees_visualizer.graphic_interface.listeners.ClearButtonListener;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class ManagerPanel extends JPanel {
	private int grade;
	private BTree<?> tree;
	private BTreeScrollPanel treePanel;
	protected static ArrayList<Pair<Boolean, ?>> actions;
	public static int fontSize;

	public ManagerPanel(BTree<?> tree, BTreeScrollPanel treePanel, StepsPanel stepsP	) {
		this.grade = tree.getGrade();
		this.tree = tree;
		this.treePanel = treePanel;

		actions = new ArrayList<Pair<Boolean, ?>>();

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gbl);

// Tree grade panel
		JPanel gradePa = new GradePanel(tree, treePanel, actions);
// Insert panel
		JPanel addPa = new InsertPanel(tree, treePanel, actions);
// Remove Panel
		JPanel remPa = new RemovePanel(tree, treePanel, actions);
// Clear button
		c.insets = new Insets(0, 0, 0, 0);
		JButton clearB = new JButton("Clear");
		clearB.setFont(clearB.getFont().deriveFont(Applet.defaultFontSize));
		clearB.addActionListener(new ClearButtonListener(tree, treePanel, actions));
// Zoom Panel
		JPanel zoom = new ZoomPanel(tree, treePanel, actions);
/* start code added from Federico Baldo*/
// NextPrevious Panel
		JPanel npPanel = new NextPreviousPanel((BTree<Integer>)tree,treePanel);

// Add panels to manager
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		this.add(gradePa, c);

		c.gridx = 1;
		c.gridy = 0;
		this.add(addPa, c);

		c.gridx = 2;
		c.gridy = 0;
		this.add(remPa, c);

		c.weightx = 0.5f;
		c.gridx = 3;
		c.gridy = 0;
		this.add(clearB, c);

		c.weightx = 1;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		this.add(zoom, c);

		c.gridx = 5;
		c.gridy = 0;
		this.add(npPanel,c);
	}

}
