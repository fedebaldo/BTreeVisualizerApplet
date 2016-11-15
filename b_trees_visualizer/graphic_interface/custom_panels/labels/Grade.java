package b_trees_visualizer.graphic_interface.custom_panels.labels;

import b_trees_visualizer.data_structure.BTree;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JLabel;

/* class that provide a panel with the grade of the actual exercise tree */

public class Grade extends JPanel {

  private int grade;
  private JLabel gradeL ;
  private GridBagConstraints c;

  /* constructor*/
  public Grade (BTree<Integer> tree) {

    super ();
    this.grade = tree.getGrade();
    this.setLayout(new GridBagLayout());
    c = new GridBagConstraints ();

    JLabel lab = new JLabel ("Grade :", JLabel.CENTER);
    gradeL = null;

    c.gridx = 0;
    c.gridy = 0;
    this.add(lab, c);

  }

  /*
  * @param grade : integer, new grade to be setted
  * EFFECT change the grade label of the this with the value grade
  */
  public void update (int grade) {

    this.grade = grade;
    if (this.gradeL != null) {this.remove(gradeL);}
    gradeL = new JLabel ("" + this.grade, JLabel.CENTER);
    this.c.gridx = 0;
    this.c.gridy = 1;
    this.add(gradeL, c);

  }

}
