package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.NewExercisePanel;
import b_trees_visualizer.graphic_interface.listeners.CheckTreeListener;
import b_trees_visualizer.graphic_interface.listeners.DrawRootListener;
import b_trees_visualizer.data_structure.BTree;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


/* this class provide a panel with draw root button and check drawed tree button */

public class DrawCheckRoot extends JPanel {

  private DrawRootListener l;

  /* constructor */
  public DrawCheckRoot (BTreeScrollPanel treePanel, NewExercisePanel exPan) {

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    this.setLayout(gbl);
    this.setBorder(BorderFactory.createEmptyBorder());

    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;

    /*check drawed tree button */
    JButton check = new JButton ("Check");
    CheckTreeListener cT = new CheckTreeListener(exPan, treePanel);
    check.addActionListener(cT);

    /* fiel to write the keys to draw in the root */
    JTextField keys = new JTextField();
    keys.setHorizontalAlignment(JTextField.CENTER);
    /* button to draw the root with inserted keys */
    JButton drawRoot = new JButton ("Draw Root");
    l = new DrawRootListener(keys, treePanel, cT);
    keys.addActionListener(l);
    drawRoot.addActionListener(l);


    c.gridx = 0;
    c.gridy = 0;
    this.add(keys, c);

    c.gridx = 0;
    c.gridy = 1;
    this.add(drawRoot, c);

    c.gridx = 0;
    c.gridy = 2;
    this.add(check, c);

  }

  public BTreeScrollPanel getScrollPanel () {return l.getScrollPanel();}
  public boolean getFlag() {return l.getFlag();}

}
