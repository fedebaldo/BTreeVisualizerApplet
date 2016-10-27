package b_trees_visualizer.graphic_interface.custom_panels.manager_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.listeners.NextListener;
import b_trees_visualizer.graphic_interface.listeners.PreviousListener;

import javax.swing.*;
import java.awt.*;

public class NextPreviousPanel extends JPanel {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;

  public NextPreviousPanel (BTree<Integer> tree, BTreeScrollPanel treePanel) {

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    this.setLayout(gbl);
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JButton next = new JButton("Next");
    JButton previous = new JButton("Previous");

    next.addActionListener(new NextListener(tree,treePanel));
    previous.addActionListener(new PreviousListener(tree, treePanel));

    this.add(next,c);
    this.add(previous,c);

  }
}
