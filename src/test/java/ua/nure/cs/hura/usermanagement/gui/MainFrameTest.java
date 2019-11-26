package ua.nure.cs.hura.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import javax.swing.Box.Filler;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import ua.nure.cs.hura.usermanagement.domain.User;
import ua.nure.cs.hura.usermanagement.db.DaoFactory;
import ua.nure.cs.hura.usermanagement.db.MockDaoFactory;
import ua.nure.cs.hura.usermanagement.util.Messages;


public class MainFrameTest extends JFCTestCase {
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
	private static final Date DATE_OF_BIRTH = new Date();
	private static final String LAST_NAME = "Doe";
	private static final String FIRST_NAME = "John";
	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final int NUMBER_OF_COLUMNS_IN_USER_TABLE = 3;
	
	private MainFrame mainFrame;

	private Mock mockUserDao;

    private ArrayList<User> users;

	private final Date DATE = new Date();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
        try {
            Properties properties = new Properties();
            properties.setProperty("dao.Factory", MockDaoFactory.class.getName());
            DaoFactory.init(properties);
            mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
            User expectedUser = new User(new Long(1001L), FIRST_NAME, LAST_NAME, DATE);
            users = new ArrayList<User>();
            users.add(expectedUser);
            mockUserDao.expectAndReturn("findAll",users);
            setHelper(new JFCTestHelper());
            mainFrame = new MainFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainFrame.setVisible(true);
    }

	@Override
	protected void tearDown() throws Exception {
        try {
            super.tearDown();
            mockUserDao.verify();
            mainFrame.setVisible(false);
            getHelper();
			TestHelper.cleanUp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private Component find(Class<?> componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;
	}
	
	public void testBrowsePanel() {
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		JTable table = (JTable) find(JTable.class, "userTable");
		find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
		find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);

	}
	
	public void testAddUser() {
        try {
            String firstName = FIRST_NAME;
            String lastName = LAST_NAME;

            User user = new User(FIRST_NAME, LAST_NAME, DATE);

            User expectedUser = new User(new Long(1), FIRST_NAME, LAST_NAME, DATE);
            mockUserDao.expectAndReturn("create", user, expectedUser);
            System.out.println("1a" + expectedUser + user);

            
            ArrayList<User> users = new ArrayList<User>(this.users);
            users.add(expectedUser);
            mockUserDao.expectAndReturn("findAll", users);
            
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());

            JButton addButton = (JButton) find(JButton.class, "addButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

            find(JPanel.class, "addPanel");

            fillFields(firstName, lastName, DATE);

            JButton okButton = (JButton) find(JButton.class, "okButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(2, table.getRowCount());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
	//
	public void testCancelAddUser() {
        try {
            String firstName = "John";
            String lastName = "Doe";
            Date now = new Date();

            ArrayList<User> users = new ArrayList<User>(this.users);
            mockUserDao.expectAndReturn("findAll", users);
            
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());

            JButton addButton = (JButton) find(JButton.class, "addButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

            find(JPanel.class, "addPanel");

            fillFields(firstName, lastName, now);

            JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
	
	public void testEditUser() {
        try {  
            find(JPanel.class, "browsePanel");

            User expectedUser = new User(new Long(1001), "George", "Bush", DATE);
            mockUserDao.expect("update", expectedUser);
            List<User> users = new ArrayList<User>(this.users);
            users.add(expectedUser);

            mockUserDao.expectAndReturn("findAll", users);

            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton editButton = (JButton) find(JButton.class, "editButton");
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
            
            find(JPanel.class, "editPanel");

            JTextField firstNameField = (JTextField) find(JTextField.class,
                    "firstNameField");
            JTextField lastNameField = (JTextField) find(JTextField.class,
                    "lastNameField");
            
            getHelper().sendString(
                    new StringEventData(this, firstNameField, "1"));
            getHelper().sendString(
                    new StringEventData(this, lastNameField, "1"));

            JButton okButton = (JButton) find(JButton.class, "okButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
            

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(2, table.getRowCount());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

	public void testCancelEditUser() {
        try {
            String firstName = "John";
            String lastName = "Doe";
            Date now = new Date();

            User expectedUser = new User(new Long(1), firstName, lastName, now);
            List<User> users = new ArrayList<User>(this.users);
            users.add(expectedUser);
            
            mockUserDao.expectAndReturn("findAll", users);
            
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());

            JButton editButton = (JButton) find(JButton.class, "editButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

            String title = "Edit user";
            findDialog(title);
            
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
            
            find(JPanel.class, "editPanel");

            JTextField firstNameField = (JTextField) find(JTextField.class,
                    "firstNameField");
            JTextField lastNameField = (JTextField) find(JTextField.class,
                    "lastNameField");
            JTextField dateOfBirthField = (JTextField) find(JTextField.class,
                    "dateOfBirthField");
            
            assertEquals("George", firstNameField.getText());
            assertEquals("Bush", lastNameField.getText());
            
            getHelper().sendString(
                    new StringEventData(this, firstNameField, firstName));
            getHelper().sendString(
                    new StringEventData(this, lastNameField, lastName));
            DateFormat formatter = DateFormat.getDateInstance();
            String date = formatter.format(now);
            getHelper().sendString(
                    new StringEventData(this, dateOfBirthField, date));

            JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(2, table.getRowCount());
            
        } catch (Exception e) {
            fail(e.toString());
        }
    }
	
	public void testDeleteUser() {
        try {

            User expectedUser = new User(new Long(1001), "George", "Bush", DATE);
            mockUserDao.expect("delete", expectedUser);
            List<User> users = new ArrayList<User>();
            mockUserDao.expectAndReturn("findAll", users);

            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));
            
            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(0, table.getRowCount());


        } catch (Exception e) {
            fail(e.toString());
        }
    }
	
	public void testCancelDetailedPanel() {
        try {
            ArrayList<User> users = new ArrayList<User>(this.users);
            mockUserDao.expectAndReturn("findAll", users);

            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());

            JButton detailsButton = (JButton) find(JButton.class, "detailsButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));

            String title = "Details user";
            findDialog(title);

            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));


            find(JPanel.class, "detailsPanel");

            JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
	
	 private void findDialog(String title) {
	        JDialog dialog;
	        DialogFinder dFinder = new DialogFinder(title);
	        dialog = (JDialog) dFinder.find();
	        assertNotNull("Could not find dialog '" + title + "'", dialog);
	        getHelper();
	        TestHelper.disposeWindow( dialog, this );
	    }



	private void fillFields(String firstName, String lastName, Date dateOfBirth) {
		JTextField firtNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
		JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		
		getHelper().sendString(new StringEventData(this, firtNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		DateFormat formatter = DateFormat.getDateInstance();
		String date = formatter.format(dateOfBirth);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
	}
	
}
