package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.DrawRoot;
import b_trees_visualizer.graphic_interface.custom_panels.DrawChild;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import java.awt.*;
import javax.swing.*;

public class DrawManager extends JPanel {

  private BTreeScrollPanel treePanel;
  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

  public DrawManager (BTreeScrollPanel treePanel) {

    super ();
    this.treePanel = treePanel;

    this.setLayout(new GridLayout(3,1,0,1));
    this.setPreferredSize(new Dimension(this.screenWidth*1/10, this.screenHeight));

    DrawRoot drawRoot = new DrawRoot (this.treePanel);
    this.add(drawRoot);
  }

}
