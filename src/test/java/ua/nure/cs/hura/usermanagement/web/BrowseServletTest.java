package ua.nure.cs.hura.usermanagement.web;

import ua.nure.cs.hura.usermanagement.domain.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;



public class BrowseServletTest extends MockServletTestCase {
	private static final String DELETE_USER = "deleteUser";
	private static final String GET_USER = "getUser";
	private static final String DELETE_BUTTON = "deleteButton";
	private static final String DETAILS_BUTTON = "detailsButton";
	private static final String EDIT_BUTTON = "editButton";
	private static final String LAST_NAME_TEST2 = "Gates";
	private static final String FIRST_NAME_TEST2 = "Bill";
	private static final Long ID_TEST = new Long(1000);
	private static final String LAST_NAME_TEST = "Doe";
	private static final String FIRST_NAME_TEST = "John";

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	public void testBrowse() {
		User user = new User(ID_TEST, FIRST_NAME_TEST,LAST_NAME_TEST,new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		@SuppressWarnings("unchecked")
		Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session",collection);
		assertSame(list,collection);		

	}

	public void testEdit() {
		User user = new User(ID_TEST, FIRST_NAME_TEST,LAST_NAME_TEST, new Date());
		getMockUserDao().expectAndReturn("find",ID_TEST,user);
		addRequestParameter(EDIT_BUTTON,"Edit");
		addRequestParameter("id","1000");
		doPost();
		User userInSession = (User)getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session ",user);
		assertSame(user, userInSession);

	}
	public void testDetails(){
	    User user = new User(ID_TEST ,FIRST_NAME_TEST2, LAST_NAME_TEST2, new Date());
	    getMockUserDao().expectAndReturn(GET_USER, new Long(1000).longValue(), user);
	    addRequestParameter(DETAILS_BUTTON, "Details");
	    addRequestParameter("id", "1000");
	    doPost();
	    User userInsession = (User)getWebMockObjectFactory().getMockSession().getAttribute("user");
	    assertNotNull("No user in session" ,userInsession);
	    assertSame(user, userInsession);
	  }

	  public void testDelete(){
	    User user = new User(ID_TEST ,FIRST_NAME_TEST2, LAST_NAME_TEST2, new Date());
	    getMockUserDao().expect(DELETE_USER, user.getId().longValue());
	    addRequestParameter(DELETE_BUTTON, "Delete");
	    addRequestParameter("id", "1000");
	    doPost();
	  }
}