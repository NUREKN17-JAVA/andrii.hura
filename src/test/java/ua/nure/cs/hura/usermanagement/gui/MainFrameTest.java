package ua.nure.cs.hura.usermanagement.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final int NUMBER_OF_COLUMNS_IN_USER_TABLE = 3;
	private MainFrame mainFrame;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	@Override
	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper();
		TestHelper.cleanUp(this);
		super.tearDown();
		
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
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(NUMBER_OF_COLUMNS_IN_USER_TABLE, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("First Name", table.getColumnName(1));
		assertEquals("Last Name", table.getColumnName(2));
		find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
		find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);

	}
	
}
