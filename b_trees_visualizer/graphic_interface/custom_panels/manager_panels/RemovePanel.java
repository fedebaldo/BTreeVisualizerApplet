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
import b_trees_visualizer.graphic_interface.listeners.RemoveListener;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class RemovePanel extends JPanel {
	public RemovePanel(BTree<?> tree, BTreeScrollPanel treePanel, ArrayList<Pair<Boolean, ?>> actions) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gbl);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel remL = new JLabel("Remove a key", SwingConstants.CENTER);
		remL.setFont(remL.getFont().deriveFont(Applet.defaultFontSize));

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		this.add(remL, c);

		c.weightx = 1;

		JTextField remTF = new JTextField();
		remTF.setHorizontalAlignment(JTextField.CENTER);
		remTF.addActionListener(new RemoveListener(tree, treePanel, remTF, actions));
		c.gridx = 0;
		c.gridy = 1;
		this.add(remTF, c);

		JButton remB = new JButton("Remove");
		remB.setFont(remB.getFont().deriveFont(Applet.defaultFontSize));
		remB.addActionListener(new RemoveListener(tree, treePanel, remTF, actions));
		c.gridx = 0;
		c.gridy = 2;
		this.add(remB, c);
	}
}
