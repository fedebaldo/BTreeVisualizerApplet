package b_trees_visualizer.graphic_interface.utility;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;

import java.util.ArrayList;

public class Tools {

  /**
  ** @param tree1, tree2 : BTree<Integer>
  ** @return true if tree1 == tree2
  **/
  public static boolean checkBTrees (BTree<Integer> tree1, BTree<Integer> tree2) {

    return checkBTreesFromRoot (tree1.getRoot(), tree2.getRoot());

  }

  /**
  ** @param root1, root2 : BTreeNode<Integer>
  ** @return true if root1 == root2
  **/
  private static boolean checkBTreesFromRoot (BTreeNode<Integer> root1, BTreeNode<Integer> root2) {

    boolean correct = true;

    /* if the second root2 is null then false
    * because root1 can't be null */

    if (root2 == null) {return false;}

    if (root1.isLeaf()) {

      /* BTreeNode drawed in the exercise don't use the tag leaf,
      so the node is a leaf iff its children are null */

      if (root2.allNullChildren()) {
        /* root1 and root2 are leaf */
        return root1.equals(root2);

      } else {
        /* root2 isn't a leaf */
        return false;

      }

    } else {

      /* root1 isn't a leaf */

      if (root2.allNullChildren()) {
        /* root2 is a leaf */
        return false;

      }

      /* The two nodes aren't leaf, then check if the keys in two nodes
      are the same anche check al their children*/

      if (root1.getData().size() != root2.getData().size()) {
        /* one node has more key than the other*/
        return false;
      }

      for (int i = 0; i < root1.getData().size(); i++) {

        if (root1.getKey(i) == root2.getKey(i))
          return false;
      }
    
      for (int i = 0; i < root1.getChildren().size(); i++) {

        if (!(correct = correct && checkBTreesFromRoot(root1.getChild(i), root2.getChild(i)))) {
          return false;
        }

      }
    }
    return correct;
  }

  /*
  * @param keys : a String
  * @thorws NumberFormatException if the string is empty it can't be parsed into a number
  * @return an ArrayList of integer containing the number in keys divided by commas. every
  * char that isn't a number is ignored
  */
  public static ArrayList<Integer> parse (String keys) throws NumberFormatException {

    if (keys.equals("")) {

      throw new NumberFormatException ();

    }
   /*result*/
   ArrayList<Integer> k = new ArrayList<Integer> ();
   /*one number detected*/
   String el ="";

   while (!keys.equals("")) {

     char ch = keys.charAt(0);
     /* if the char is a number is added to el*/
     if (ch >='0' && ch <= '9') {

       el += ch;
     /* if is a comma, then el contain the next number add in k*/
     } else if (ch == ',') {

       k.add(Integer.parseInt(el));
       el = "";

     }
     /* every time we considere the string without the last char analysed */
     keys = keys.substring(1);

   }

   k.add(Integer.parseInt(el));
   return k;

  }

}
