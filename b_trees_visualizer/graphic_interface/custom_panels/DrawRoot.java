package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.listeners.DrawerListener;

import javax.swing.*;
import java.awt.*;

public class DrawRoot extends JPanel {

  private BTreeScrollPanel treePanel;
  private DrawerListener l;

  public DrawRoot (BTreeScrollPanel treePanel) {

    this.treePanel = treePanel;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    this.setLayout(gbl);
    this.setBorder(BorderFactory.createEmptyBorder());

    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;

    JTextField keys = new JTextField();
    keys.setHorizontalAlignment(JTextField.CENTER);
    JButton drawRoot = new JButton ("Draw Root");
    l = new DrawerListener(keys, treePanel);
    keys.addActionListener(l);
    drawRoot.addActionListener(l);

    c.gridx = 0;
    c.gridy = 0;
    this.add(keys, c);

    c.gridx = 0;
    c.gridy = 1;
    this.add(drawRoot, c);

  }

  public BTreeScrollPanel getScrollPanel () {return this.treePanel;}
  public boolean getFlag() {return l.getFlag();}

}
