package ua.nure.cs.hura.usermanagement.web;

import ua.nure.cs.hura.usermanagement.domain.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;



public class BrowseServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	public void testBrowse() {
		User user = new User(new Long(1000), "John","Doe",new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		@SuppressWarnings("unchecked")
		Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session",collection);
		assertSame(list,collection);		

	}

	public void testEdit() {
		User user = new User(new Long(1000), "John", "Doe", new Date());
		getMockUserDao().expectAndReturn("find",new Long(1000),user);
		addRequestParameter("editButton","Edit");
		addRequestParameter("id","1000");
		doPost();
		User userInSession = (User)getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session ",user);
		assertSame(user, userInSession);

	}
}