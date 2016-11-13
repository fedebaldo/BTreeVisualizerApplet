package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;
<<<<<<< HEAD
import b_trees_visualizer.graphic_interface.utility.Tools;
=======
>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class DrawerListener implements ActionListener {

  private JPanel drawPan;
  private JTextField keys;
  private ArrayList<Integer> data;
  private BTreeScrollPanel bl;
<<<<<<< HEAD
  private BTree<Integer> drawedTree;
  private boolean flag;
=======
  private BTree<Integer> solTree;
  private BTree<Integer> drawedTree;
>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7

  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

<<<<<<< HEAD
  public DrawerListener (JTextField keys, BTreeScrollPanel bl) {
=======
  public DrawerListener (JTextField keys, BTree<Integer> tree, BTreeScrollPanel bl) {
>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7

    super();
    this.bl = bl;
    this.keys = keys;
<<<<<<< HEAD
    this.flag = false;
=======
    this.solTree = tree;

>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7

  }

  @Override
  public void actionPerformed (ActionEvent e) {

<<<<<<< HEAD
      this.data = Tools.parse(keys.getText());
      keys.setText("");
      this.drawedTree = new BTree<Integer> (data);
      this.drawedTree.getRoot().setLeaf(false);
      this.bl.changeTree(this.drawedTree, false, false);
      this.bl.updateTreePanel(false,false);
      this.flag = true;

  }

  public boolean getFlag () {return this.flag;}
=======
      this.data = parse(keys.getText());
      keys.setText("");
      this.drawedTree = new BTree<Integer> (data);
      this.bl.changeTree(this.drawedTree, false, false);
      this.bl.updateTreePanel(false,false);

  }


  private ArrayList<Integer> parse (String keys) {

   ArrayList<Integer> k = new ArrayList<Integer> ();
   String el ="";
   while (!keys.equals("")) {

     char ch = keys.charAt(0);

     if (ch >='0' && ch <= '9') {

       el += ch;

     } else if (ch == ',') {

       k.add(Integer.parseInt(el));
       el = "";

     }

     keys = keys.substring(1);

   }

   k.add(Integer.parseInt(el));
   return k;

  }
>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7

}
