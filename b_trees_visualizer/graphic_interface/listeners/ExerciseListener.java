package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExerciseListener implements ActionListener {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;

  public ExerciseListener (BTree<Integer> tree, BTreeScrollPanel treePanel) {

    super();
    this.tree = tree;
    this.treePanel = treePanel;

  }

  @Override
  public void actionPerformed (ActionEvent a) {

    this.tree.emptyTree();

    for (int i = 0; i < 10; i++) {

      this.tree.next();

    }

    this.treePanel.updateTreePanel(false, false);

  }

}
