package ua.nure.cs.hura.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class BrowsePanel extends JPanel implements ActionListener {

	private static final String ADD_COMMAND = "add";
	private MainFrame parent;
	private JScrollPane tablePanel;
	private JTable userTable;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton detailsButton;
	
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";

	public BrowsePanel(MainFrame mainFrame) {
		parent = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel");// non-localized(No need to do localization)
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		if(buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(),null);//WE will implement one in class, others I have to do at home
			buttonPanel.add(getEditButton(),null);
			buttonPanel.add(getDeleteButton(),null);
			buttonPanel.add(getDetailsButton(),null);

		}
		return buttonPanel;
	}

	private JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton();
			addButton.setText("Add");//has to be localized
			addButton.setName(ADD_BUTTON_COMPONENT_NAME);
			addButton.setActionCommand(ADD_COMMAND);//non-localize
			addButton.addActionListener(this);
		}
		return addButton;
	}

	
	private JButton getEditButton() {
		if(editButton == null) {
			editButton = new JButton();
			editButton.setText("Edit");//has to be localized
			editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
			editButton.setActionCommand("edit");//non-localize
			editButton.addActionListener(this);
		}
		return editButton;
	}
	
	
	private JButton getDeleteButton() {
		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Delete");//has to be localized
			deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
			deleteButton.setActionCommand("delete");//non-localize
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}
	
	
	private JButton getDetailsButton() {
		if(detailsButton == null) {
			detailsButton = new JButton();
			detailsButton.setText("Details");//has to be localized
			detailsButton.setName(DETAILS_BUTTON_COMPONENT_NAME);
			detailsButton.setActionCommand("details");//non-localize
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	
	
	private JScrollPane getTablePanel() {
		if(tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
			//tablePanel.setName("userTable"); //non-localize
		}
		return tablePanel;
	}

	private JTable getUserTable() {
		if(userTable == null) {
			userTable = new JTable();
			userTable.setName("userTable"); //non-localize
		}
		return userTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acctionCommand = e.getActionCommand();
		if(ADD_COMMAND.equalsIgnoreCase(acctionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		} ///else if(EDIT_COMMANd)...
		
	}

}
