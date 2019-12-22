package ua.nure.cs.hura.usermanagement.web;
import java.text.DateFormat;
import java.util.Date;
import ua.nure.cs.hura.usermanagement.domain.User;



public class EditServletTest extends MockServletTestCase {
	private static final String OK_COMMAND = "Ok";
	private static final String ID_TEST_2 = "1000";
	private static final String ID_REQUEST_PARAMETER = "id";
	private static final String OK_BUTTON_REQUEST_PARAMETER = "okButton";
	private static final String DATE_REQUEST_PARAMETER = "date";
	private static final String LAST_NAME_REQUEST_PARAMETER = "lastName";
	private static final String FIRST_NAME_REQUEST_PARAMETER = "firstName";
	private static final String UPDATE_COMMAND = "update";
	private static final String LAST_NAME_TEST = "Doe";
	private static final String FIRST_NAME_TEST = "John";
	private static final Long ID_TEST = new Long(1000);

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	public void testEdit() {
		Date date = new Date();
		User user = new User(ID_TEST,FIRST_NAME_TEST,LAST_NAME_TEST,date);
		getMockUserDao().expect(UPDATE_COMMAND,user);

		addRequestParameter(ID_REQUEST_PARAMETER,ID_TEST_2);
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,DateFormat.getDateInstance().format(date));
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,OK_COMMAND);
		doPost();
	}
	
	public void testEditEmptyFirstName() {
		Date date = new Date();
		addRequestParameter(ID_REQUEST_PARAMETER,ID_TEST_2);
		addRequestParameter(ID_REQUEST_PARAMETER,ID_TEST_2);
		addRequestParameter(DATE_REQUEST_PARAMETER,DateFormat.getDateInstance().format(date));
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,OK_COMMAND);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter(ID_REQUEST_PARAMETER,ID_TEST_2);
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,DateFormat.getDateInstance().format(date));
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,OK_COMMAND);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testEditEmptyDate() {
		Date date = new Date();
		addRequestParameter(ID_REQUEST_PARAMETER,ID_TEST_2);
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,OK_COMMAND);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testEditEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter(ID_REQUEST_PARAMETER,ID_TEST_2);
		addRequestParameter(FIRST_NAME_REQUEST_PARAMETER,FIRST_NAME_TEST);
		addRequestParameter(LAST_NAME_REQUEST_PARAMETER,LAST_NAME_TEST);
		addRequestParameter(DATE_REQUEST_PARAMETER,"fjskgjakfja");
		addRequestParameter(OK_BUTTON_REQUEST_PARAMETER,OK_COMMAND);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

}