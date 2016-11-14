package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.listeners.ExerciseListener;
import b_trees_visualizer.graphic_interface.custom_panels.InsRem;
//import b_trees_visualizer.graphic_interface.custom_panels.Grade;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.data_structure.BTree;


import java.awt.*;
import javax.swing.*;

import java.util.Random;

public class NewExercisePanel extends JPanel {

  private BTree<Integer> tree;
  private int grade;
  private BTreeScrollPanel treePanel;
  private InsRem ins_rem;
  //private Grade grad;
  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


  public NewExercisePanel () {

    super ();
    Random rand = new Random(System.currentTimeMillis());
    this.grade = rand.nextInt(2) + 2;
    this.tree = new BTree<Integer> (this.grade);
    this.setPreferredSize(new Dimension (this.screenWidth*21/43, this.screenHeight));
    /* panel with BTree exercise*/

    this.treePanel = new BTreeScrollPanel(this.tree, new Dimension(this.screenWidth*161/430, this.screenHeight));
    /* new exercise manager*/
    JPanel manager = new JPanel (new GridLayout(3,1,0,1));
    manager.setPreferredSize(new Dimension(this.screenWidth*1/10, this.screenHeight));
    /* insertion or cancellation*/
    this.ins_rem = new InsRem ((BTree<Integer>) this.treePanel.getTree());
    //this.grad = new Grade((BTree<Integer>) this.treePanel.getTree());

    JButton newEx = new JButton ("New Exercise");
    newEx.addActionListener (new ExerciseListener(this.tree, this.treePanel, this.ins_rem));
    manager.add(newEx);
    manager.add(this.ins_rem);
    //manager.add(this.grad);

    this.add(manager, BorderLayout.WEST);
    this.add(treePanel, BorderLayout.EAST);

  }

  public void update () {
    this.treePanel.updateTreePanel(false, false);
    this.ins_rem.clear();
    this.ins_rem.next();
  }

  public BTree<Integer> getSolTree () {
    return this.ins_rem.getTree();
  }

  public BTree<Integer> getTree() {
    return this.tree;
  }

  public void setTree (BTree<Integer> tree) {
    this.tree = tree;
    this.treePanel.changeTree(this.tree, false, false);
  }
}
