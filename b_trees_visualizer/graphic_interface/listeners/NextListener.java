package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.data_structure.BTree;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextListener implements ActionListener {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;

  public NextListener (BTree<Integer> tree, BTreeScrollPanel treePanel) {

    super();
    this.tree = tree;
    this.treePanel = treePanel;

  }

  @Override
  public void actionPerformed(ActionEvent event) {

    tree.next();
    treePanel.updateTreePanel(true, false);
    treePanel.repaint();

  }
}
