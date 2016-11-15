package b_trees_visualizer.graphic_interface.listeners;

import b_trees_visualizer.graphic_interface.custom_panels.NewExercisePanel;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.utility.Tools;
import b_trees_visualizer.data_structure.BTree;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** this class provide a listener for the button check.
** check if the exercise tree is the same of the draw panel
**/

public class CheckTreeListener implements ActionListener {

  private NewExercisePanel exercisePanel;
  private BTreeScrollPanel treePanel;
  private BTree<Integer> tree;
  private BTree<Integer> solTree;

  @SuppressWarnings("unchecked")
  /* listener constructor*/
  public CheckTreeListener (NewExercisePanel exercisePanel, BTreeScrollPanel treePanel) {

    this.exercisePanel = exercisePanel;
    this.treePanel = treePanel;
    this.tree = (BTree<Integer>) treePanel.getTree();
    this.solTree = this.exercisePanel.getSolTree();

  }
  /*
  * @param treePanel : BTreeScrollPanel
  * EFFECT change set the new treePanel ang change actual tree with the
  * one in treePanel
  */
  @SuppressWarnings("unchecked")
  public void update (BTreeScrollPanel treePanel) {
    this.treePanel = treePanel;
    this.tree = (BTree<Integer>) this.treePanel.getTree();
  }

  @Override
  public void actionPerformed (ActionEvent e) {


    if (!this.solTree.isEmpty() || !this.tree.isEmpty()) {

      /* if the two trees are the equal we ha to update the exercise panel
      * with the correct tree and create a new insertion or deletion */
      if (Tools.checkBTrees(this.solTree, this.tree)) {

        exercisePanel.update();
        JOptionPane.showMessageDialog(new JFrame(), "Tre drawed tree is correct !");

      } else {
        /* the two trees are different */
        JOptionPane.showMessageDialog(new JFrame(), "The drawed tree is uncorrect: \n try again !");
      }

    } else {
        /* the two BTrees are empty so there's nothing to be checked */
        JOptionPane.showMessageDialog(new JFrame(), "The B-Trees in the exercise must be non empty");

    }
  }
}
