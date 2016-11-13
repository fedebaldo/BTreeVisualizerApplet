package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTreeNode;
import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.utility.Tools;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.util.ArrayList;

public class ChildDrawerListener implements ActionListener {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private ArrayList<Integer> data;
  private ArrayList<Integer> path;
  private int level, node, child;

  public ChildDrawerListener (ArrayList<Integer> path, BTreeScrollPanel treePanel) {

    this.tree = (BTree<Integer>) treePanel.getTree();
    this.treePanel = treePanel;
    this.path = path;
    this.node = node;
    this.level = level;
    this.child = child;

  }

  @Override
  public void actionPerformed (ActionEvent e) {

    String keys;
    keys = JOptionPane.showInputDialog("Please insert the keys for the selected child");
    this.data = Tools.parse(keys);

    for (int i = 0; i < this.path.size(); i++) {
      System.out.println(this.path.get(i));
    }

    findAndAdd ();

    this.treePanel.updateTreePanel(false,false);

  }

  private void findAndAdd () {

    BTreeNode<Integer> temp = this.tree.getRoot();

    while (this.path.size() != 1) {

      temp = temp.getChild(this.path.remove(0));

    }
    temp.addChild(this.path.remove(0),this.data);
  }

}
