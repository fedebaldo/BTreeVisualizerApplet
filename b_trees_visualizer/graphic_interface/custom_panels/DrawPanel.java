package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.DrawManager;
import b_trees_visualizer.data_structure.BTree;


import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

  public DrawPanel () {

    super ();
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension (this.screenWidth*1/2, this.screenHeight));
    this.tree = new BTree<Integer> (2);
    this.treePanel = new BTreeScrollPanel (this.tree, new Dimension(this.screenWidth*8/20, this.screenHeight));

    DrawManager drawManager = new DrawManager (this.treePanel);

    this.add(drawManager, BorderLayout.WEST);
    this.add(this.treePanel, BorderLayout.EAST);

  }
}
