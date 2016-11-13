package b_trees_visualizer.graphic_interface;

import javax.swing.*;
import java.awt.*;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.ManagerPanel;
import b_trees_visualizer.graphic_interface.custom_panels.StepsPanel;
import b_trees_visualizer.graphic_interface.custom_panels.ExercisePanel;


public class Applet extends JApplet {

public static StepsPanel stepsP;
public static int defaultFontSize = Math.max(Toolkit.getDefaultToolkit().getScreenSize().width * 18/1920
                               ,Toolkit.getDefaultToolkit().getScreenSize().height * 18/1080);
public static int fontSize = defaultFontSize;

public void init() {

    JFrame f = new JFrame(); /* screen dimension depending on the computer used */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    f.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
    f.setLocationByPlatform(true);
    f.setResizable(true);
    /*Two panels, card1 and card2, are accessible through a tab menu*/
    JTabbedPane tab = new JTabbedPane();
     /* example panel */
    JPanel card1 = new JPanel (new BorderLayout());
    BTree<Integer> tree = new BTree<Integer> (2);
    BTreeScrollPanel pan = new BTreeScrollPanel(tree);
    stepsP = new StepsPanel(tree, pan);
    ManagerPanel man = new ManagerPanel(tree, pan, stepsP);
    card1.add(pan, BorderLayout.CENTER);
    card1.add(man, BorderLayout.NORTH);
     /* exercise panel */
    ExercisePanel exPanel = new ExercisePanel();
    JPanel card2 = exPanel.getPane();

    tab.addTab("Example",card1);
    tab.addTab("Exercise", card2);

    f.add(tab);
    f.pack();
    f.setVisible(true);

  }

}
