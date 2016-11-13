package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.DrawRoot;
import b_trees_visualizer.graphic_interface.utility.Tools;
import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

public class DrawChildListener implements ActionListener {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private JTextField level, nodeNumber, childNumber, keys;
  private int lv, nodeN, childN;
  private ArrayList<Integer> data;
  private DrawRoot drawRoot;

  public DrawChildListener (JTextField level, JTextField nodeNumber, JTextField childNumber, JTextField keys, DrawRoot drawRoot) {

    this.drawRoot = drawRoot;
    this.level = level;
    this.nodeNumber = nodeNumber;
    this.childNumber = childNumber;
    this.keys = keys;

  }

  @Override
  public void actionPerformed (ActionEvent e) {

    if (drawRoot.getFlag()) {
      this.treePanel = this.drawRoot.getScrollPanel();
      this.tree = (BTree<Integer>) this.treePanel.getTree();
      this.lv = Integer.parseInt(level.getText());
      //System.out.println(this.lv);
      level.setText("");
      this.nodeN = Integer.parseInt(nodeNumber.getText());
      //System.out.println(this.nodeN);
      nodeNumber.setText("");
      this.childN = Integer.parseInt(childNumber.getText());
      //System.out.println(this.childN);
      childNumber.setText("");
      this.data = Tools.parse(keys.getText());
      keys.setText("");

      findAndAddChild(this.lv, this.nodeN, this.childN);
      this.treePanel.updateTreePanel(false, false);
    }

  }

  private int nN;

  private int findAndAddChild (int y, int x, int z) {

    this.nN = 0;

    BTreeNode<Integer> temp = this.tree.getRoot();
    System.out.println(temp.getChildren().isEmpty());

    if (y == 0) {

      if (x == 0) {

        temp.addChild(z, this.data);
        this.tree.setRoot(temp);
        return 0;

      }

    } else {

      doVisitAndAddChild (x, y, z,temp);
      this.tree.setRoot(temp);
      return 0;

      }
      /* insertion failed */
      System.out.println("ERROREEEE");
      return 1;


  }

  private void doVisitAndAddChild (int x, int y, int z, BTreeNode<Integer> root) {

    int l = 0;

    if (root != null) {

      while (l != y-1) {

        for (BTreeNode<Integer> n : root.getChildren()) {

          doVisitAndAddChild(x,y,z,n);

        }
      }

      for (BTreeNode<Integer> n : root.getChildren()) {

        if (n != null) {
          if (x == this.nN) {

            n.addChild(z, this.data);
            return ;

        }

        this.nN += 1;

      }
    }
  }
}

}
