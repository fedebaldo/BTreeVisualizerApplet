package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.data_structure.BTree;

import javax.swing.*;
import java.awt.*;

public class Grade extends JPanel {

  private int grade;
  private JLabel grad ;
  private GridBagConstraints c;

  public Grade (BTree<Integer> tree) {

    this.grade = tree.getGrade();
    this.setLayout(new GridBagLayout());
    c = new GridBagConstraints ();

    JLabel lab = new JLabel ("Grade :", JLabel.CENTER);
    grad = new JLabel ("" + this.grade, JLabel.CENTER);

    c.gridx = 0;
    c.gridy = 0;
    this.add(lab, c);
    c.gridx = 0;
    c.gridy = 1;
    this.add(grad, c);

  }

  public void update (int grade) {
    this.grade = grade;
    this.remove(grad);
    grad = new JLabel ("" + this.grade, JLabel.CENTER);
    this.c.gridx = 0;
    this.c.gridy = 1;
    this.add(grad, c);
  }

}
