package b_trees_visualizer.graphic_interface.utility;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.util.ArrayList;

public class Tools {

  public static boolean checkBTrees (BTree<Integer> tree1, BTree<Integer> tree2) {

    return checkBTreesFromRoot (tree1.getRoot(), tree2.getRoot());

  }

  private static boolean checkBTreesFromRoot (BTreeNode<Integer> root1, BTreeNode<Integer> root2) {

    boolean correct = true;

    if (root2 == null) {return false;}

    if (root1.isLeaf()) {

      if (root2.allNullChildren()) {
        return root1.equals(root2);
      } else {
        return false;
      }

    } else {

      for (int i = 0; i < root1.getChildren().size(); i++) {

        if (!(correct = correct && checkBTreesFromRoot(root1.getChild(i), root2.getChild(i)))) {
          return false;
        }

      }
    }
    System.out.println("fine " + correct);
    return correct;
  }

  public static ArrayList<Integer> parse (String keys) throws NumberFormatException {

    if (keys.equals("")) {

      throw new NumberFormatException ();

    }


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
