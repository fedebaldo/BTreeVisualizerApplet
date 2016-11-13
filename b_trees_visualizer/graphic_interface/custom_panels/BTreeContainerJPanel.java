package b_trees_visualizer.graphic_interface.custom_panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

<<<<<<< HEAD
import javax.swing.*;
=======
import javax.swing.JLabel;
import javax.swing.JPanel;
>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7

public class BTreeContainerJPanel extends JPanel {

	private boolean hasFocusedChild;
	private boolean hasFocusedData;

	public BTreeContainerJPanel() {
		super();
		hasFocusedChild = false;
		hasFocusedData = false;
	}

	public void setFocusedChild(boolean focused) {
		this.hasFocusedChild = focused;
	}

	public boolean hasFocusedChild() {
		return hasFocusedChild;
	}

	public void setFocusedData(boolean focused) {
		this.hasFocusedData = focused;
	}

	public boolean hasFocusedData() {
		return hasFocusedData;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		JPanel dataPanel = (JPanel) this.getComponent(0);

		for (int i = 1; i < this.getComponentCount(); i++) {
			// Starting point
			/* The coordinates are taken as the sum of the distance from the left/top of
			 * the container panel to the data panel plus the offset from the borders of
			 * the data panel and the labels of the keys. As x1 and y1 are the coordinates
			 * for the starting points, y1 is incremented by the height of the labels so
			 * the lines start from the bottom of the data array. X1 is handled in a way
			 * so the starting point falls before the key that is smaller than any of the
			 * keys in the subtree and the key that is greater than all the keys in the
			 * subtree that has the child as root.
			 */
			int x1 = dataPanel.getX();
			JLabel tempL = null;
			// if i hasn't reached the last child position, consider only the child X position
			if (i < this.getComponentCount() - 1) {
				tempL = (JLabel) dataPanel.getComponent(i - 1);
				x1 += tempL.getX();
			} else { // otherwise get the last key in data and place it at its bottom-right corner
				tempL = (JLabel) dataPanel.getComponent(i - 2);
				x1 += tempL.getX() + dataPanel.getComponent(i - 2).getWidth();
			}
			// Position of the data panel in the parent panel
			// + Offset of labels in the data panel
			// + Height of the labels
			int y1 = dataPanel.getY() + tempL.getY() + tempL.getHeight();

			// Ending point
			// Gets the i-th child jpanel
			JPanel temp = (JPanel) this.getComponent(i);
			// Gets the panel that contains the jlabels with the node's data
			JPanel tempData = (JPanel)temp.getComponent(0);
			/* To make the line point to the middle of the child's data, it's taken in consideration
			 * whether there is an even number of keys or not. If there are, the left side of the
			 * label in the middle is taken as centred position, otherwise it gets the same label position
			 * but it also adds half of the width of the single label.
			 */
<<<<<<< HEAD
			JComponent tempEl = (JComponent)tempData.getComponent((int)(tempData.getComponentCount() / 2));
=======
			JLabel tempEl = (JLabel)tempData.getComponent((int)(tempData.getComponentCount() / 2));
>>>>>>> c58f2ac914e50ffa9c7c569ea6dc7f54806883c7
			int x2 = temp.getX() + tempData.getX();
			if (tempData.getComponentCount() % 2 == 0) {
				x2 += tempEl.getX();
			} else {
				x2 += tempEl.getX() + tempEl.getWidth() / 2;
			}
			// Position of the child panel in the parent panel
			// + Position of data panel in the child panel
			// + Offset of the labels in the data panel
			int y2 = temp.getY() + tempData.getY() + tempEl.getY();


			//Set antialiasing for the lines drawn.
			Graphics2D g2d = (Graphics2D)g;
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.drawLine(x1, y1, x2, y2);
		}
	}
}
