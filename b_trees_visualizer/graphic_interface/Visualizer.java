package b_trees_visualizer.graphic_interface;

import java.awt.*;
import javax.swing.*;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.ManagerPanel;
import b_trees_visualizer.graphic_interface.custom_panels.StepsPanel;

public class Visualizer extends JApplet {

	public void init () {

		BTree<Integer> tree = new BTree<Integer>(2);

		JFrame f = new JFrame();
		f.setSize(1300,1000);
		f.setLocationByPlatform(true);

		JPanel bigOne = new JPanel(new BorderLayout());

		BTreeScrollPanel treePanel = new BTreeScrollPanel(tree);
	 	ManagerPanel man = new ManagerPanel(tree,treePanel,new StepsPanel(tree, treePanel));

		bigOne.add(man, BorderLayout.NORTH);
		bigOne.add(treePanel, BorderLayout.CENTER);

		f.add(bigOne);
		f.setVisible(true);

	}
}
