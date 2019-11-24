package ua.nure.cs.hura.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import ua.nure.cs.hura.usermanagement.util.Messages;
import ua.nure.cs.hura.usermanagement.domain.User;
import ua.nure.cs.hura.usermanagement.db.DatabaseException;


public class BrowsePanel extends JPanel implements ActionListener {

	private static final String DETAILS_COMMAND = "details";
	private static final String DELETE_COMMAND = "delete";
	private static final String EDIT_COMMAND = "edit";
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
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText(Messages.getString("BrowsePanel.add")); //non localized
            addButton.setName("addButton"); //non localized
            addButton.setActionCommand("add"); //non localized
            addButton.addActionListener(this);
        }
        return addButton;
    }

	
	private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText(Messages.getString("BrowsePanel.edit")); //non localized
            editButton.setName("editButton"); //non localized
            editButton.setActionCommand(EDIT_COMMAND); //non localized
            editButton.addActionListener(this);
        }
        return editButton;
    }

	
	
	private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText(Messages.getString("BrowsePanel.delete")); //non localized
            deleteButton.setName("deleteButton"); //non localized
            deleteButton.setActionCommand(DELETE_COMMAND); //non localized
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }
	
	
	private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText(Messages.getString("BrowsePanel.details")); //non localized
            detailsButton.setName("detailsButton"); //non localized
            detailsButton.setActionCommand(DETAILS_COMMAND); //non localized
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
	
	public void initTable() {
        UserTableModel model;
        try {
            model = new UserTableModel(parent.getDao().findAll());//add to mainframe
        } catch (DatabaseException e) {
            model = new UserTableModel(new ArrayList<User>());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        getUserTable().setModel(model);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String acctionCommand = e.getActionCommand();
		if(ADD_COMMAND.equalsIgnoreCase(acctionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		} ///else if(EDIT_COMMANd)...
		
		else if (EDIT_COMMAND.equalsIgnoreCase(acctionCommand)) { //non localized
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            User user = ((UserTableModel) userTable.getModel())
                    .getUser(selectedRow);
            this.setVisible(false);
            parent.showEditPanel(user);//Add to MainFrame
        }
		
		else if (DELETE_COMMAND.equalsIgnoreCase(acctionCommand)) { //non localized
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                parent.getDao().delete(
                        ((UserTableModel) userTable.getModel())
                                .getUser(selectedRow)); //add getDAo
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            initTable();
            return;
        } 
		
		else if(DETAILS_COMMAND.equalsIgnoreCase(acctionCommand)){
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Details user", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            User user = ((UserTableModel) userTable.getModel())
                    .getUser(selectedRow);
            this.setVisible(false);
            parent.showDetailsPanel(user);//Add to mainframe
        }
		
	}
	

}
