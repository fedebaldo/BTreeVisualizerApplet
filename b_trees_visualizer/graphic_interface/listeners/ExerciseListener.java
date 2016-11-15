package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.InsRem;
import b_trees_visualizer.graphic_interface.custom_panels.Grade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ExerciseListener implements ActionListener {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private InsRem insRem;
  private Grade gradeL;
  private int grade;
  private Random rand;

  public ExerciseListener (BTree<Integer> tree, BTreeScrollPanel treePanel, InsRem insRem, Grade gradeL) {

    super();
    this.tree = tree;
    this.treePanel = treePanel;
    this.insRem = insRem;
    this.gradeL = gradeL;
    this.grade = this.tree.getGrade();
    rand = new Random (System.currentTimeMillis());
  }

  @Override
  public void actionPerformed (ActionEvent a) {

    this.grade = this.rand.nextInt(2) + 2;

    if (!this.treePanel.getTree().isEmpty()) {
      insRem.clear();
    }

    this.tree.emptyTree();
    this.tree.setGrade(this.grade);


    for (int i = 0; i < 10; i++) {

      this.tree.next();

    }

    this.treePanel.updateTreePanel(false, false);
    this.insRem.next();
    this.gradeL.update(this.tree.getGrade());

  }

}
