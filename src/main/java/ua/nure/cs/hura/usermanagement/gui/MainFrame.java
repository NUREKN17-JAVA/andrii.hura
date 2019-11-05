package ua.nure.cs.hura.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("User Manager");
		this.setContentPane(getContentPanel());
	}

	private JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel());
		}
		return contentPanel;
	}
	

	private BrowsePanel getBrowsePanel() {
		if(browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		return browsePanel;
	}

}
