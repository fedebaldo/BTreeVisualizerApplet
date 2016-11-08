package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class DrawerListener implements ActionListener {

  private JPanel drawPan;
  private JTextField keys;
  private ArrayList<Integer> data;
  private BTreeScrollPanel drawTree;
  private BTree<Integer> solTree;
  private BTree<Integer> drawedTree;

  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

  public DrawerListener (JTextField keys, BTree<Integer> tree, JPanel drawPan) {

    super();
    this.drawPan = drawPan;
    this.keys = keys;
    this.solTree = tree;

  }

  @Override
  public void actionPerformed (ActionEvent e) {

      System.out.println(keys.getText());

      this.data = parse(keys.getText());
      keys.setText("");
      this.drawedTree = new BTree<Integer> (data);
      this.drawTree = new BTreeScrollPanel (this.drawedTree, new Dimension (screenWidth*8/20, screenHeight));
      drawPan.add(this.drawTree, BorderLayout.EAST);
      this.drawTree.updateTreePanel(false,false);
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

}
