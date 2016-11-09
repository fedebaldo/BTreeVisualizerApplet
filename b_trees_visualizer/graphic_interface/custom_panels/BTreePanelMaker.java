package b_trees_visualizer.graphic_interface.custom_panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import b_trees_visualizer.data_structure.BTree;
import b_trees_visualizer.data_structure.BTreeNode;
import b_trees_visualizer.graphic_interface.Applet;

public class BTreePanelMaker<E extends Comparable<? super E>> {

	public static BTreeContainerJPanel makePanelFromTree(BTree<?> b, boolean showChanges, boolean keepPrevious) {
		return makePanelFromRoot(b.getRoot(), b.getGrade(), showChanges, keepPrevious);
	}

	private static BTreeContainerJPanel makePanelFromRoot(BTreeNode<?> root, int treeGrade, boolean showChanges, boolean keepPrevious) {
		int numberOfElements = root.getNumberOfElements();
		int fontSize = Applet.fontSize;
		int treeSpacing = (treeGrade - 1) * fontSize;

		//Initialise the layout manager and its constraints
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		//Initialise the panel that will contain both the keys and the children panels
		BTreeContainerJPanel container = new BTreeContainerJPanel();
		container.setLayout(gbl);
		container.setOpaque(false);
		//container.setBorder(BorderFactory.createLineBorder(Color.black));

		// Initialise the panel that will contain all the keys of the node
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(gbl);
		dataPanel.setOpaque(false);
		if (showChanges && root.isFocused()) {
			dataPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
			container.setFocusedData(true);
		}
		//dataPanel.setBorder(BorderFactory.createLineBorder(Color.red));

		/* The field previouslyModified in the nodes is used to differentiate the newly modified nodes from
		 * the ones that were modified in the previous add/remove. This is useful for the zoom as without
		 * these the "recentlyModified" nodes would have set the recentlyModified field to false after the
		 * first panel paint, making the highlighting disappear with the zoom repaint.
		 */
		if (!keepPrevious && root.isPreviouslyModified()) {
			root.removePreviousModifications();
		}

		// Gets the ArrayList containing all the keys that were added to the node
		ArrayList<?> modifications = null;
		if (root.isRecentlyModified() || root.isPreviouslyModified()) {
			modifications = root.getAddedKeys();
		}

		//For each element in the root's data, creates a label and adds it to the data panel
		for (int i = 0; i < numberOfElements; i++) {
			JLabel temp = new JLabel(root.getKey(i).toString(), SwingConstants.CENTER);

			// If the root has been modified, it check whether there are any keys that have been added and it
			// colours their label's background with green if there's any.
			if (showChanges && (root.isRecentlyModified() || root.isPreviouslyModified()) && modifications.contains(root.getKey(i))) {
				temp.setBackground(Color.green);
			} else {
				temp.setBackground(Color.white);
			}

			// If the root has been modified and there was at least one removed key, the borders of the whole node will be red.
			if (showChanges && root.hasRemovedKeys()) {
				temp.setBorder(BorderFactory.createLineBorder(Color.red));
			} else {
				temp.setBorder(BorderFactory.createLineBorder(Color.black));
			}

			temp.setOpaque(true);
			temp.setFont(temp.getFont().deriveFont((float)fontSize));
			c.fill = GridBagConstraints.BOTH;
			c.ipadx = fontSize;
			c.gridx = i;
			c.gridy = 0;
			dataPanel.add(temp, c);
		}

		/* This is needed to differentiate the latest modifications with the previous ones. The recent modifications are
		 * deleted immediately after using them and they're stored as "previous modifications" in order to let the zoom
		 * work without having the colours disappear from the tree.
		 */
		if (root.isRecentlyModified()) {
			if (showChanges && keepPrevious) {
				root.setPreviouslyModified(root.getModifications());
			}
			root.removeModifications();
		}

		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = fontSize;
		c.ipady = fontSize * 2;
		// Vertical spacing between the nodes
		c.gridwidth = root.getNumberOfElements() + 1;
		container.add(dataPanel, c);

		// Reinitialise the constrains to get all values to default
		c = new GridBagConstraints();

		if (!root.isLeaf()) {
			c.fill = GridBagConstraints.VERTICAL;

			/* For that inserts the panels of all children except the last one to
			 * the container panel.
			 */
			for (int i = 0; i < numberOfElements; i++) {
				// A 5px space is left on the top of the panel and a 10px space on the right.
				c.insets = new Insets(treeSpacing, 0, 0, fontSize);
				// Each panel has a 10px padding both on the left and on the right
				c.ipadx = fontSize;
				// they're positioned in the i-th position of the gridbaglayout grid
				c.gridx = i;
				c.gridy = 1;
				BTreeContainerJPanel temp = BTreePanelMaker.makePanelFromRoot(root.getChild(i), treeGrade, showChanges, keepPrevious);

				if (temp.hasFocusedChild() || temp.hasFocusedData()) {
					container.setFocusedChild(true);
				}

				container.add(temp, c);

			}
			// for the last child the 10px space is removed
			c.insets = new Insets(treeSpacing, 0, 0, 0);
			c.gridx = numberOfElements;
			c.gridy = 1;
			container.add(BTreePanelMaker.makePanelFromRoot(root.getChild(numberOfElements), treeGrade, showChanges, keepPrevious), c);

		}

		return container;

	}
}
