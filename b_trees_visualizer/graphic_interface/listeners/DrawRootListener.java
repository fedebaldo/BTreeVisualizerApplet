package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;
import b_trees_visualizer.graphic_interface.utility.Tools;
import b_trees_visualizer.graphic_interface.listeners.CheckTreeListener;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

/** this class provide a listener to draw root button **/

public class DrawRootListener implements ActionListener {

  private CheckTreeListener checkL;
  private JTextField keys;
  private ArrayList<Integer> data;
  private BTreeScrollPanel treePanel;
  private BTree<Integer> drawedTree;
  private boolean flag;

  /* constructor*/
  public DrawRootListener (JTextField keys, BTreeScrollPanel treePanel, CheckTreeListener checkL) {

    super();
    this.treePanel = treePanel;
    this.keys = keys;
    this.flag = false;
    this.checkL = checkL;

  }

  @Override
  public void actionPerformed (ActionEvent e) {
    try {

      this.data = Tools.parse(keys.getText());
      keys.setText("");
      this.drawedTree = new BTree<Integer> (data);
      this.treePanel.changeTree(this.drawedTree, false, false);
      this.flag = true;
      checkL.update (this.treePanel);
    } catch (NumberFormatException m) {}
  }

  public boolean getFlag () {return this.flag;}
  public BTreeScrollPanel getScrollPanel () {return this.treePanel;}
}
