package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.listeners.ExerciseListener;
import b_trees_visualizer.graphic_interface.custom_panels.labels.InsRem;
import b_trees_visualizer.graphic_interface.custom_panels.labels.Grade;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.data_structure.BTree;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.Random;

public class NewExercisePanel extends JPanel {

  private BTree<Integer> tree;
  private int grade;
  private BTreeScrollPanel treePanel;
  private InsRem ins_rem;
  private Grade grad;

  /** this class provide a panel for random exercise generation */
  @SuppressWarnings("unchecked")
  /*constructor*/
  public NewExercisePanel () {

    super ();

    Random rand = new Random(System.currentTimeMillis());
    this.grade = rand.nextInt(2) + 2;
    this.tree = new BTree<Integer> (this.grade);
    this.setPreferredSize(new Dimension (Applet.screenWidth*204/500, Applet.screenHeight));
    /* panel with BTree exercise*/
    this.treePanel = new BTreeScrollPanel(this.tree, new Dimension(Applet.screenWidth*204/500, Applet.screenHeight*5/7));
    /* new exercise comands*/
    JPanel manager = new JPanel (new GridLayout(1,5,0,0));
    manager.setPreferredSize(new Dimension(Applet.screenWidth*2/5, Applet.screenHeight*1/10));
    this.ins_rem = new InsRem ((BTree<Integer>) this.treePanel.getTree());
    this.grad = new Grade((BTree<Integer>) this.treePanel.getTree());
    JButton newEx = new JButton ("New Exercise");
    newEx.addActionListener (new ExerciseListener(this.tree, this.treePanel, this.ins_rem, this.grad));
    manager.add(this.grad);
    manager.add(newEx);
    manager.add(this.ins_rem);

    this.add(manager, BorderLayout.NORTH);
    this.add(treePanel, BorderLayout.SOUTH);

  }

  /* create new exercise */
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
