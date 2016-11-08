package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.listeners.DrawerListener;
import b_trees_visualizer.graphic_interface.listeners.ExerciseListener;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class ExercisePanel {

  private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
  private BTree<Integer> tree;
  private BTreeScrollPanel treePanel;
  private int grade;
  private Random rand;
  private JPanel card;
  private JPanel exPan;
  private JPanel drawPan;

  public ExercisePanel () {

    /* big panel containig exercise*/
    this.exPan = new JPanel (new BorderLayout());
    this.exPan.setPreferredSize(new Dimension (this.screenWidth*16/33, this.screenHeight));
    this.exPan.setBackground(Color.gray);

    /*preparing the tree*/
    this.rand = new Random(System.currentTimeMillis());
    this.grade = rand.nextInt(2) + 2;
    this.tree  = new BTree<Integer> (this.grade);
    this.treePanel = new BTreeScrollPanel(this.tree,new Dimension(this.screenWidth*127/330, this.screenHeight));

    /* bar of button to generate exercise*/
    JPanel comPan = new JPanel (new BorderLayout());
    comPan.setPreferredSize(new Dimension(this.screenWidth*1/10, this.screenHeight));
    comPan.setBackground(Color.gray);
    JButton newEx = new JButton ("New Exercise");
    newEx.addActionListener (new ExerciseListener(this.tree, this.treePanel));
    comPan.add(newEx, BorderLayout.NORTH);

    this.exPan.add(comPan, BorderLayout.WEST);
    this.exPan.add(treePanel, BorderLayout.EAST);

    this.drawPan = new JPanel(new BorderLayout());
    this.drawPan.setPreferredSize(new Dimension (this.screenWidth*1/2, this.screenHeight));

    JPanel paintPan = new JPanel ();
    paintPan.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.NORTH;
    paintPan.setPreferredSize(new Dimension(this.screenWidth*1/10, this.screenHeight));
    paintPan.setBackground(Color.gray);

    JTextField keys = new JTextField();
    keys.setHorizontalAlignment(JTextField.CENTER);
    keys.addActionListener(new DrawerListener(keys, this.tree, drawPan));

    JButton drawRoot = new JButton ("Draw Root");
    drawRoot.addActionListener(new DrawerListener(keys, this.tree, drawPan));
    c.insets = new Insets(0, 0, 0, 0);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridx = 0;
    c.gridy = 0;
    paintPan.add(drawRoot, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridx = 0;
    c.gridy = 1;
    paintPan.add(keys,c);
    drawPan.add(paintPan, BorderLayout.WEST);

    this.card = new JPanel (new BorderLayout());
    this.card.add(exPan, BorderLayout.WEST);
    this.card.add(drawPan, BorderLayout.EAST);
  }

  public JPanel getPane() {return this.card;}

}