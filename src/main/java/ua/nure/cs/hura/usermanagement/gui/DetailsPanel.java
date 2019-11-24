package ua.nure.cs.hura.usermanagement.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;

import ua.nure.cs.hura.usermanagement.domain.User;
import ua.nure.cs.hura.usermanagement.db.DatabaseException;
import ua.nure.cs.hura.usermanagement.util.Messages; 

public class DetailsPanel extends JPanel implements ActionListener {

	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
	private static final String CANCEL_COMMAND = "cancel";
	private static final long serialVersionUID = 5955495123527812587L;
	private MainFrame parent;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private User user;
    protected JPanel fieldPanel;
    protected JTextField firstNameField;
    protected JTextField dateOfBirthField;
    protected JTextField lastNameField;
    protected Color bgColor;

    public DetailsPanel(MainFrame frame) {
        parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("detailsPanel"); //$NON-NLS-1$
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    protected JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabeledField(fieldPanel, Messages.getString("DetailsPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
            addLabeledField(fieldPanel, Messages.getString("DetailsPanel.last_name"), getLastNameField()); //$NON-NLS-1$
            addLabeledField(fieldPanel, Messages.getString("DetailsPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
        }
        return fieldPanel;
    }

    protected void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JPanel getButtonsPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(closeButton(), null);
        }
        return buttonPanel;
    }

    private JButton closeButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setText(Messages.getString("DetailsPanel.cancel")); //$NON-NLS-1$
            cancelButton.setName(CANCEL_BUTTON_COMPONENT_NAME); //$NON-NLS-1$
            cancelButton.setActionCommand(CANCEL_COMMAND); //$NON-NLS-1$
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    public void actionPerformed(ActionEvent e) {
        clearFields();
        this.setVisible(false);
        parent.showBrowsePanel();
    }

    protected void clearFields() {
        getFirstNameField().setText("");
        getFirstNameField().setBackground(bgColor);

        getLastNameField().setText("");
        getLastNameField().setBackground(bgColor);

        getDateOfBirthField().setText("");
        getDateOfBirthField().setBackground(bgColor);
    }

    protected JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
        }
        return dateOfBirthField;
    }

    protected JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField"); //$NON-NLS-1$
        }
        return lastNameField;
    }

    protected JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField"); //$NON-NLS-1$
        }
        return firstNameField;
    }

    public void setUser(User user) {
        DateFormat format = DateFormat.getDateInstance();
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getLastNameField().setText(user.getLastName());
        getDateOfBirthField().setText(format.format(user.getDateOfBirth()));
    }

}