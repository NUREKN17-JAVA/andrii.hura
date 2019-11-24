package ua.nure.cs.hura.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.cs.hura.usermanagement.domain.User;
import ua.nure.cs.hura.usermanagement.db.DaoFactory;
import ua.nure.cs.hura.usermanagement.db.Dao;
import ua.nure.cs.hura.usermanagement.util.Messages;

public class MainFrame extends JFrame {
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private Dao dao;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	private DetailsPanel detailsPanel;
    private EditPanel editPanel;
	
	public MainFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("User Manager");
		this.setContentPane(getContentPanel());
	}
	
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}
	

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
	}

	private AddPanel getAddPanel() {
		if(addPanel == null) {
			addPanel = new AddPanel();
		}
		return addPanel;
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
