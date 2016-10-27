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
import b_trees_visualizer.graphic_interface.listeners.AddListener;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class InsertPanel extends JPanel {

	public InsertPanel(BTree<?> tree, BTreeScrollPanel treePanel, ArrayList<Pair<Boolean, ?>> actions) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gbl);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel addL = new JLabel("Add new key", SwingConstants.CENTER);
		addL.setFont(addL.getFont().deriveFont(Applet.defaultFontSize));

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		this.add(addL, c);

		c.weightx = 1;

		JTextField addTF = new JTextField();
		addTF.setHorizontalAlignment(JTextField.CENTER);
		addTF.addActionListener(new AddListener(tree, treePanel, addTF, actions));
		c.gridx = 0;
		c.gridy = 1;
		this.add(addTF, c);

		JButton addB = new JButton("Add");
		addB.addActionListener(new AddListener(tree, treePanel, addTF, actions));
		c.gridx = 0;
		c.gridy = 2;
		this.add(addB, c);
	}
}
