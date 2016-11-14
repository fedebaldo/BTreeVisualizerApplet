package b_trees_visualizer.graphic_interface.custom_panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.graphic_interface.utility.Pair;

public class BTreeScrollPanel extends JScrollPane {

	private BTree<?> tree;
	private BTree<?> visualizedTree;
	private JPanel background;
	private GridBagConstraints c;
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	public BTreeScrollPanel(BTree<?> tree) {
		super();
		// The differentiation between visualizedTree and tree is in order to freely switch
		// between the tree states in the steps visualization
		this.tree = tree;
		visualizedTree = tree;

		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


		// Panel to give a solid colour to the tree background
		background = new JPanel();
		background.setBackground(Color.white);
		// Set layout to GridBagLayout so that panel gets centred
		background.setLayout(new GridBagLayout());

		// Set the content panel of the JScrollPane to background
		this.setViewportView(background);
		// Set scrollbars behaviour
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getHorizontalScrollBar().setUnitIncrement(15);
		this.getVerticalScrollBar().setUnitIncrement(15);

		this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
	}

	public BTreeScrollPanel(BTree<?> tree, Dimension dim) {
		super();
		// The differentiation between visualizedTree and tree is in order to freely switch
		// between the tree states in the steps visualization

		this.tree = tree;
		this.visualizedTree = this.tree;
		// Panel to give a solid colour to the tree background
		background = new JPanel();
		background.setBackground(Color.white);
		// Set layout to GridBagLayout so that panel gets centred
		background.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		// Set the content panel of the JScrollPane to background
		this.setViewportView(background);
		// Set scrollbars behaviour
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getHorizontalScrollBar().setUnitIncrement(15);
		this.getVerticalScrollBar().setUnitIncrement(15);

		this.setPreferredSize(dim);

	}


	// Updates the B-Tree's panel by removing the previous one from the background panel
	// and then re-adding the freshly made one
	public void updateTreePanel(boolean showChanges, boolean keepPrevious) {
		if (background.getComponentCount() > 0) {
			background.remove(0);
		}

		background.add(BTreePanelMaker.makePanelFromTree(visualizedTree, showChanges, keepPrevious,this),c);
		this.setViewportView(background);
		repaint();

		//System.out.println(this.visualizedTree.getRoot().getNumberOfLeaf());
	}

	/** Code Used by Donato Davide to fill the starting BTree
	public void autoFill(int rangeMin, int rangeMax) {
		int random = 50000;
		int r = 0;
		int i;
		for (i = 0; i < random; i++) {
			((BTree<Integer>)tree).insertKey(i, false);
		}
		updateTreePanel(false, false);
	}
	**/

	public void changeTree(BTree<?> tempTree, boolean showChanges, boolean keepPrevious) {
		// Changes the currently visualized tree with another one
		visualizedTree = tempTree;
		updateTreePanel(showChanges, keepPrevious);
	}

	public BTree<?> getTree () {return this.visualizedTree;}

	public void defaultTree() {
		visualizedTree = tree;
		updateTreePanel(true, false);
	}

	public void centreScrollPanel() {
		/* Centres the view of the treePanel. This needs to be done after the pack due to the
		 * scrollRectWidth which takes a value only after the frame sizes are set by pack.
		 */
		// Get the width of the scrollbar view rectangle
		int scrollRectWidth = (int)this.getViewport().getExtentSize().getWidth();
		// Get the width of the view
		int viewWidth = this.getViewport().getViewSize().width;
		// Set the initial position of the scrollbar at half window minus half of the scrollbar width
		// because setViewPosition considers the top-left corner.
		this.getViewport().setViewPosition(new Point((viewWidth - scrollRectWidth) / 2, 0));
	}

	public void centreToFocusedPanel() {
		int scrollRectWidth = (int)this.getViewport().getExtentSize().getWidth();
		BTreeContainerJPanel temp = (BTreeContainerJPanel)background.getComponent(0);
		int x = 0;
		while (temp != null) {
			x += temp.getX();
			if (temp.hasFocusedData()) {
				break;
			} else if (temp.hasFocusedChild()) {
				for (int i = 1; i < temp.getComponentCount(); i++) {
					BTreeContainerJPanel tempChild = (BTreeContainerJPanel)temp.getComponent(i);
					if (tempChild.hasFocusedChild() || tempChild.hasFocusedData()) {
						temp = tempChild;
						break;
					}
				}
			} else {
				break;
			}
		}
		this.getViewport().setViewPosition(new Point(x, 0));
	}
}
