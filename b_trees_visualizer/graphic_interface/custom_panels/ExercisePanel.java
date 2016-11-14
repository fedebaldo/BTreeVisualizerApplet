package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.NewExercisePanel;
import b_trees_visualizer.graphic_interface.custom_panels.DrawPanel;
import b_trees_visualizer.graphic_interface.listeners.CheckTreeListener;
import b_trees_visualizer.data_structure.BTree;


import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class ExercisePanel extends JPanel{

  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private int grade;

  public ExercisePanel () {

    super();

    NewExercisePanel newExercisePanel = new NewExercisePanel ();

    /*
    * the panel used set draw sheet and draw commands
    */
    JPanel drawPanel = new JPanel ();
    drawPanel.setLayout(new BorderLayout());
    drawPanel.setPreferredSize(new Dimension (this.screenWidth*1/2, this.screenHeight));
    this.tree = new BTree<Integer> (2);
    this.treePanel = new BTreeScrollPanel (this.tree, new Dimension(this.screenWidth*8/20, this.screenHeight));
    /*
    * command panel
    */
    JPanel manPanel =  new JPanel();
    manPanel.setLayout(new GridLayout(3,1,0,1));
    manPanel.setPreferredSize(new Dimension(this.screenWidth*1/10, this.screenHeight));
    DrawRoot drawRoot = new DrawRoot (this.treePanel, newExercisePanel);
    manPanel.add(drawRoot);

    drawPanel.add(manPanel, BorderLayout.WEST);
    drawPanel.add(this.treePanel, BorderLayout.EAST);

    this.setLayout(new BorderLayout());
    this.add(newExercisePanel, BorderLayout.WEST);
    this.add(drawPanel, BorderLayout.EAST);

  }

}
