package ua.nure.cs.hura.usermanagement.web;
import java.text.DateFormat;
import java.util.Date;

import ua.nure.cs.hura.usermanagement.domain.User;
public class AddServletTest extends MockServletTestCase {
	private static final Long ID_TEST = new Long(1000);
	private static final String OK_BUTTON_REQUEST_PARAMETER = "okButton";
	private static final String DATE_REQUEST_PARAMETER = "date";
	private static final String LAST_NAME_REQUEST_PARAMETER = "lastName";
	private static final String FIRST_NAME_REQUEST_PARAMETER = "firstName";
	private static final String CREATE_COMMAND = "create";
	private static final String LAST_NAME_TEST = "Doe";
	private static final String FIRST_NAME_TEST = "John";

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	public void testAdd() {
		Date date = new Date();
		User newUser = new User(FIRST_NAME_TEST,LAST_NAME_TEST,date);
		User user = new User(ID_TEST,FIRST_NAME_TEST,LAST_NAME_TEST,date);
		getMockUserDao().expectAndReturn(CREATE_COMMAND,newUser,user);

		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,DateFormat.getDateInstance().format(date));
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,"Ok");
		doPost();
	}

	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,DateFormat.getDateInstance().format(date));
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,"Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testAddEmptyLastName() {
		Date date = new Date();
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,DateFormat.getDateInstance().format(date));
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,"Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testAddEmptyDate() {
		Date date = new Date();
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,"Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testAddEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,"fjskgjakfja");
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,"Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}