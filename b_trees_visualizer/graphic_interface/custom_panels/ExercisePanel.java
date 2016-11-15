package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.NewExercisePanel;
import b_trees_visualizer.graphic_interface.custom_panels.DrawCheckRoot;
import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.data_structure.BTree;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

/**
** this class provide a panel for the exercise. Include a panel that
** generate random exercise and a draw sheet for the "solution BTree"
**/

public class ExercisePanel extends JPanel{

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private int grade;

  public ExercisePanel () {

    super();

    NewExercisePanel newExercisePanel = new NewExercisePanel ();

    /** draw sheet **/
    /*
    * the panel used set draw sheet and draw commands
    */
    JPanel drawPanel = new JPanel ();
    drawPanel.setLayout(new BorderLayout());
    drawPanel.setPreferredSize(new Dimension (Applet.screenWidth*1/2, Applet.screenHeight));
    this.tree = new BTree<Integer> (2);
    this.treePanel = new BTreeScrollPanel (this.tree, new Dimension(Applet.screenWidth*8/20, Applet.screenHeight));
    /*
    * command panel
    */
    JPanel manPanel =  new JPanel();
    manPanel.setLayout(new GridLayout(3,1,0,1));
    manPanel.setPreferredSize(new Dimension(Applet.screenWidth*1/10, Applet.screenHeight));
    DrawCheckRoot drawRoot = new DrawCheckRoot (this.treePanel, newExercisePanel);
    manPanel.add(drawRoot);

    drawPanel.add(manPanel, BorderLayout.WEST);
    drawPanel.add(this.treePanel, BorderLayout.EAST);

    this.setLayout(new BorderLayout());
    this.add(newExercisePanel, BorderLayout.WEST);
    this.add(drawPanel, BorderLayout.EAST);

  }

}
