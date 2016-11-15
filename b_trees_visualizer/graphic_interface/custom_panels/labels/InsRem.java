package b_trees_visualizer.graphic_interface.custom_panels.labels;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;  

/* class that provide a panel with the label
* showing the next insertion or deltion to be done with the exercise tree
*/

public class InsRem extends JPanel {

  /* this.tree will contain the solution to the exercise proposed */
  private BTree<Integer> tree;
  private Random rand;
  private JLabel action, elem;
  private final int MAX_NUMBER_KEYS = 1000;

/* constructor */
  public InsRem (BTree<Integer> tree) {

    super();
    this.setLayout(new GridBagLayout());
    this.tree = tree;
    this.rand = new Random(System.currentTimeMillis());

    }

/*
* EFFECT generate a new insertion/deletion label
*/
  public void next() {

    int el;
    int i_r = rand.nextInt(10);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;

    if (i_r < 5 && !this.tree.isEmpty()) {

        this.action= new JLabel ("REMOVE", JLabel.CENTER);
        this.action.setForeground(Color.red);

        this.add(this.action, c);
        /* the element that has to be deleted must be in the exercise tree*/
        el = this.tree.peekRandomOne();
        elem = new JLabel(""+el);
        this.tree.removeKey(el, false);

    } else {

        this.action = new JLabel ("INSERT", JLabel.CENTER);
        this.action.setForeground(Color.green);

        this.add(this.action, c);
        /* the element that has to be inserted mustn't be in the exercise tree*/
        while (this.tree.isIn(el = this.rand.nextInt(MAX_NUMBER_KEYS)));
        elem = new JLabel(""+el);
        this.tree.insertKey(el, false);

    }

    c.gridx = 0;
    c.gridy = 1;
    this.add(elem, c);
    this.updateUI();

  }

  public void clear() {
    /* delet the labels to be updated
    * with new one. used if the exercise is completed
    * or new exercise tree is generated
    */
    try {
      this.remove(this.elem);
      this.remove(this.action);
    } catch (NullPointerException e) {}

  }

  public BTree<Integer> getTree() {return this.tree;}

}
