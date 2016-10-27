package b_trees_visualizer.graphic_interface.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import b_trees_visualizer.graphic_interface.Applet;
import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;

public class ZoomListener implements ActionListener {

	private BTreeScrollPanel treePanel;
	private int button;
	private int d;

	public ZoomListener(BTreeScrollPanel treePanel, int button) {
		this.treePanel = treePanel;
		this.button = button;
		d = 2;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (button == 0) {
			Applet.fontSize += d;
		} else if (button == 1) {
			if (Applet.fontSize > d) {
				Applet.fontSize -= d;
			} else {
				Applet.fontSize = 1;
			}
		} else if (button == 2) {
			Applet.fontSize = Applet.defaultFontSize;
		}
		treePanel.updateTreePanel(true, true);
		treePanel.repaint();
		treePanel.centreScrollPanel();
	}
}
