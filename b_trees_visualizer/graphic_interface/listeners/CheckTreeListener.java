package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.graphic_interface.custom_panels.NewExercisePanel;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.utility.Tools;

import java.awt.event.*;
import javax.swing.*;

public class CheckTreeListener implements ActionListener {

  private NewExercisePanel exercisePanel;
  private BTreeScrollPanel treePanel;
  private BTree<Integer> tree;
  private BTree<Integer> solTree;

  public CheckTreeListener (NewExercisePanel exercisePanel, BTreeScrollPanel treePanel) {


    this.exercisePanel = exercisePanel;
    this.treePanel = treePanel;
    this.tree = (BTree<Integer>) treePanel.getTree();
    this.solTree = this.exercisePanel.getSolTree();

  }

  public void update (BTreeScrollPanel treePanel) {
    this.treePanel = treePanel;
    this.tree = (BTree<Integer>) this.treePanel.getTree();
  }

  @Override
  public void actionPerformed (ActionEvent e) {

    if (Tools.checkBTrees(this.solTree, this.tree)) {
      exercisePanel.update();
      JOptionPane.showMessageDialog(new JFrame(), "Tre drawed tree is correct !");
    } else {
      JOptionPane.showMessageDialog(new JFrame(), "The drawed tree is uncorrect: \n try again !");
    }
  }
}
