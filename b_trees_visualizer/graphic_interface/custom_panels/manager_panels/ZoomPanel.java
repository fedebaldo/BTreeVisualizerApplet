package b_trees_visualizer.graphic_interface.custom_panels.manager_panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.listeners.ZoomListener;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class ZoomPanel extends JPanel {
	public ZoomPanel(BTree<?> tree, BTreeScrollPanel treePanel, ArrayList<Pair<Boolean, ?>> actions) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gbl);

		// Initialisation of the panel components with their listener
		JLabel l = new JLabel("Zoom", SwingConstants.CENTER);
		l.setFont(l.getFont().deriveFont(Applet.defaultFontSize));
		JButton bP = new JButton("+");
		bP.setFont(bP.getFont().deriveFont(Applet.defaultFontSize));
		bP.addActionListener(new ZoomListener(treePanel, 0));
		JButton bM = new JButton("-");
		bM.setFont(bM.getFont().deriveFont(Applet.defaultFontSize));
		bM.addActionListener(new ZoomListener(treePanel, 1));
		JButton bD = new JButton("Default");
		bD.setFont(bD.getFont().deriveFont(Applet.defaultFontSize));
		bD.addActionListener(new ZoomListener(treePanel, 2));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;

		// THe panel label is placed at the top 2 cells wide
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(l, c);

		// The two zoom buttons have width 1 and are placed in the second line
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.add(bP, c);

		c.gridx = 1;
		c.gridy = 1;
		this.add(bM, c);

		// The default button is placed at the bottom 2 cells wide
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		this.add(bD, c);
	}
}
