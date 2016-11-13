package b_trees_visualizer.graphic_interface.custom_panels.manager_panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.listeners.GradeListener;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class GradePanel extends JPanel {

	public GradePanel(BTree<?> tree, BTreeScrollPanel treePanel, ArrayList<Pair<Boolean, ?>> actions) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gbl);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));



		JLabel gradeL = new JLabel("Current grade: " + tree.getGrade(), SwingConstants.CENTER);
		gradeL.setFont(gradeL.getFont().deriveFont(Applet.fontSize));

		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		this.add(gradeL, c);

		c.weightx = 1;

		JTextField gradeTF = new JTextField();
		gradeTF.setHorizontalAlignment(JTextField.CENTER);
		gradeTF.addActionListener(new GradeListener(tree, treePanel, gradeTF, actions, gradeL));
		c.gridx = 0;
		c.gridy = 1;
		this.add(gradeTF, c);

		JButton gradeB = new JButton("Change grade");
		gradeB.addActionListener(new GradeListener(tree, treePanel, gradeTF, actions, gradeL));
		c.gridx = 0;
		c.gridy = 2;
		this.add(gradeB, c);
	}
}
