package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import java.util.Random;

import javax.swing.*;
import java.awt.*;

public class InsRem extends JPanel {

  private BTree<Integer> tree;
  private Random rand;
  private JLabel action, elem;
  private final int MAX_NUMBER_KEYS = 1000;

  public InsRem (BTree<Integer> tree) {

    super();
    this.setLayout(new GridBagLayout());
    this.tree = tree;
    this.rand = new Random(System.currentTimeMillis());

    }

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
        el = this.tree.peekRandomOne();
        elem = new JLabel(""+el);
        this.tree.removeKey(el, false);

    } else {

        this.action = new JLabel ("INSERT", JLabel.CENTER);
        this.action.setForeground(Color.green);

        this.add(this.action, c);
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
    try {
      this.remove(this.elem);
      this.remove(this.action);
    } catch (NullPointerException e) {}
  }

  public BTree<Integer> getTree() {return this.tree;}

}
