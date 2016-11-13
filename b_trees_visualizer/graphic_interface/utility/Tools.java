package b_trees_visualizer.graphic_interface.utility;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.util.ArrayList;

public class Tools {

  public static boolean checkBTrees (BTree<Integer> tree1, BTree<Integer> tree2) {

    return checkBTreesFromRoot (tree1.getRoot(), tree2.getRoot(), tree1.getGrade());

  }

  private static boolean checkBTreesFromRoot (BTreeNode<Integer> root1, BTreeNode<Integer> root2, int grade) {

    if (root1.isLeaf()) {

      if (!root2.isLeaf())

        JOptionPane.showMessageDialog(new JFrame(), "The drawed tree is uncorrect");
        return false;

    } else {

      if (root2.isLeaf()) {

        JOptionPane.showMessageDialog(new JFrame(), "The drawed tree is uncorrect");
        return false;

      }

      if (root2.getData().size() > grade*2+1 ||
          root2.getData().size() < grade-1) {

            JOptionPane.showMessageDialog(new JFrame(), "The grade of the tree is " + grade + ": each node has at least "
                                                        + (grade-1) + " and at most " + (2*grade+1) + " keys" );
            return false;

      }

      if (root1.getData().size() != root2.getData().size()) {

            JOptionPane.showMessageDialog(new JFrame(), "The drawed tree is uncorrect");
            return false;

      }

      for (Integer x : root1.getData()) {
        for (Integer y : root2.getData()) {

          if (x!=y) {
            JOptionPane.showMessageDialog(new JFrame(), "The drawed tree is uncorrect");
            return false;
          }

        }
      }

      int i=0;
      while (i < root1.getChildren().size()) {
        if (!checkBTreesFromRoot(root1.getChild(i), root2.getChild(i), grade)){
          return false;
        }
      }
      return true;
    }
  }

  public static ArrayList<Integer> parse (String keys) {

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
