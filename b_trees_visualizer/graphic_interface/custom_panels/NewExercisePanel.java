package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.listeners.ExerciseListener;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.data_structure.BTree;


import java.awt.*;
import javax.swing.*;

import java.util.Random;

public class NewExercisePanel extends JPanel {

  private BTree<Integer> tree;
  private int grade;
  private BTreeScrollPanel treePanel;
  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

  public NewExercisePanel () {

    super ();
    Random rand = new Random(System.currentTimeMillis());
    this.grade = rand.nextInt(2) + 2;
    this.tree = new BTree<Integer> (this.grade);
    this.setPreferredSize(new Dimension (this.screenWidth*16/33, this.screenHeight));
    /* panel with BTree exercise*/

    this.treePanel = new BTreeScrollPanel(this.tree, new Dimension(this.screenWidth*120/330, this.screenHeight));
    /* new exercise manager*/
    JPanel manager = new JPanel (new GridLayout(3,1,0,1));
    manager.setPreferredSize(new Dimension(this.screenWidth*1/10, this.screenHeight));
    JButton newEx = new JButton ("New Exercise");
    newEx.addActionListener (new ExerciseListener(this.tree, this.treePanel));
    manager.add(newEx);

    this.add(manager, BorderLayout.WEST);
    this.add(treePanel, BorderLayout.EAST);

  }

  public BTree<Integer> getTree() {
    return this.tree;
  }

  public void setTree (BTree<Integer> tree) {
    this.tree = tree;
    this.treePanel.changeTree(this.tree, false, false);
  }
}
