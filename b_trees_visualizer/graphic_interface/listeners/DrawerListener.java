package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;
import b_trees_visualizer.graphic_interface.utility.Tools;
import b_trees_visualizer.graphic_interface.listeners.CheckTreeListener;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class DrawerListener implements ActionListener {

  private JPanel drawPan;
  private CheckTreeListener c;
  private JTextField keys;
  private ArrayList<Integer> data;
  private BTreeScrollPanel bl;
  private BTree<Integer> drawedTree;
  private boolean flag;


  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


  public DrawerListener (JTextField keys, BTreeScrollPanel bl, CheckTreeListener c) {

    super();
    this.bl = bl;
    this.keys = keys;
    this.flag = false;
    this.c = c;

  }

  @Override
  public void actionPerformed (ActionEvent e) {

      this.data = Tools.parse(keys.getText());
      keys.setText("");
      this.drawedTree = new BTree<Integer> (data);
      this.drawedTree.getRoot().setLeaf(false);
      this.bl.changeTree(this.drawedTree, false, false);
      this.bl.updateTreePanel(false,false);
      this.flag = true;
      c.update (this.bl);

  }

  public boolean getFlag () {return this.flag;}
  public BTreeScrollPanel getScrollPanel () {return this.bl;}
}
