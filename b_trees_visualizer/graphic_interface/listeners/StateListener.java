package b_trees_visualizer.graphic_interface.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import b_trees_visualizer.graphic_interface.custom_panels.BTreeScrollPanel;
import b_trees_visualizer.graphic_interface.custom_panels.StepsPanel;	

public class StateListener implements MouseListener {

	private BTreeScrollPanel treePanel;
	private StepsPanel stepsP;
	private int index;

	public StateListener(BTreeScrollPanel treePanel, StepsPanel stepsP, int index) {
		this.treePanel = treePanel;
		this.stepsP = stepsP;
		this.index = index;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (index >= 0) {
			treePanel.changeTree(stepsP.getTree(index), true, true);
		} else {
			treePanel.defaultTree();
		}
	}
}
