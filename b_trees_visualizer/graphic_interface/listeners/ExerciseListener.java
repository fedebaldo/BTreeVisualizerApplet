package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.InsRem;
import b_trees_visualizer.graphic_interface.custom_panels.Grade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExerciseListener implements ActionListener {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private InsRem insRem;
  //private Grade grade;

  public ExerciseListener (BTree<Integer> tree, BTreeScrollPanel treePanel, InsRem insRem) {

    super();
    this.tree = tree;
    this.treePanel = treePanel;
    this.insRem = insRem;
    //this.grade = grade;

  }

  @Override
  public void actionPerformed (ActionEvent a) {

    if (!this.treePanel.getTree().isEmpty()) {
      insRem.clear();
    }

    this.tree.emptyTree();

    for (int i = 0; i < 10; i++) {

      this.tree.next();

    }

    this.treePanel.updateTreePanel(false, false);
    this.insRem.next();
    //this.grade.update(this.tree.getGrade());

  }

}
