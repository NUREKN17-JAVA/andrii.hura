package ua.nure.cs.hura.usermanagement.web;
import java.text.DateFormat;
import java.util.Date;
import ua.nure.cs.hura.usermanagement.domain.User;



public class EditServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	public void testEdit() {
		Date date = new Date();
		User user = new User(new Long(1000),"John","Doe",date);
		getMockUserDao().expect("update",user);

		addRequestParameter("id","1000");
		addRequestParameter("firstName","John");
		addRequestParameter("lastName","Doe");
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
	}
	
	public void testEditEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("lastName","Doe");
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("firstName","John");
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testEditEmptyDate() {
		Date date = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("firstName","John");
		addRequestParameter("lastName","Doe");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	public void testEditEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("firstName","John");
		addRequestParameter("lastName","Doe");
		addRequestParameter("date","fjskgjakfja");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

}