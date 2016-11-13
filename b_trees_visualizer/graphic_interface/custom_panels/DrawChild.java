package b_trees_visualizer.graphic_interface.custom_panels;

import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.listeners.DrawChildListener;
import b_trees_visualizer.graphic_interface.custom_panels.DrawRoot;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

import java.awt.*;
import javax.swing.*;

public class DrawChild extends JPanel {

  private DrawRoot drawRoot;

  public DrawChild (DrawRoot drawRoot) {

    this.drawRoot = drawRoot;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    this.setLayout(gbl);
    this.setBorder(BorderFactory.createEmptyBorder());

    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;

    JTextField level = new JTextField ();
    level.setHorizontalAlignment(JTextField.CENTER);

    JLabel lv = new JLabel ("Level", SwingConstants.CENTER);
    lv.setFont(lv.getFont().deriveFont(Applet.defaultFontSize));

    JTextField nodeNumber = new JTextField ();
    nodeNumber.setHorizontalAlignment(JTextField.CENTER);

    JLabel nN = new JLabel ("Node Number", SwingConstants.CENTER);
    nN.setFont(nN.getFont().deriveFont(Applet.defaultFontSize));

    JTextField childNumber = new JTextField ();
    childNumber.setHorizontalAlignment(JTextField.CENTER);

    JLabel cN = new JLabel ("Child Number", SwingConstants.CENTER);
    cN.setFont(cN.getFont().deriveFont(Applet.defaultFontSize));

    JTextField childKeys = new JTextField ();
    childKeys.setHorizontalAlignment(JTextField.CENTER);

    JLabel cK = new JLabel ("Child Keys", SwingConstants.CENTER);
    cK.setFont(cK.getFont().deriveFont(Applet.defaultFontSize));

    c.gridx = 0;
    c.gridy = 0;
    level.addActionListener(new DrawChildListener(level,nodeNumber,childNumber,childKeys, this.drawRoot));
    this.add(level, c);

    c.gridx = 0;
    c.gridy = 1;
    this.add(lv,c);

    c.gridx = 0;
    c.gridy = 2;
    nodeNumber.addActionListener(new DrawChildListener(level,nodeNumber,childNumber,childKeys, this.drawRoot));
    this.add(nodeNumber, c);

    c.gridx = 0;
    c.gridy = 3;
    this.add(nN,c);

    c.gridx = 0;
    c.gridy = 4;
    childNumber.addActionListener(new DrawChildListener(level,nodeNumber,childNumber,childKeys, this.drawRoot));
    this.add(childNumber, c);

    c.gridx = 0;
    c.gridy = 5;
    this.add(cN,c);


    c.gridx = 0;
    c.gridy = 6;
    childKeys.addActionListener(new DrawChildListener(level,nodeNumber,childNumber,childKeys, this.drawRoot));
    this.add(childKeys, c);

    c.gridx = 0;
    c.gridy = 7;
    this.add(cK,c);

    JButton draw  = new JButton("Draw");
    c.gridx =0;
    c.gridy =8;
    draw.addActionListener(new DrawChildListener(level,nodeNumber,childNumber,childKeys, this.drawRoot));
    this.add(draw,c);



  }
}
