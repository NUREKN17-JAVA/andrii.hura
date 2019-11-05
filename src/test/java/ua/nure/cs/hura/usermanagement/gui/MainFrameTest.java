package ua.nure.cs.hura.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;

public class MainFrameTest extends JFCTestCase {

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
		mainFrame.setVisible(false)
		getHelper();
		TestHelper.cleanUp(this);
		super.tearDown();
		
	}

}
