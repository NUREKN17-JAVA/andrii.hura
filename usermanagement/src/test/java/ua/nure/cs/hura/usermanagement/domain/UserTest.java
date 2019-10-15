package ua.nure.cs.hura.usermanagement.domain;

import java.util.Calendar;

import junit.framework.TestCase;
import ua.nure.cs.hura.usermanagement.domain.User;

public class UserTest extends TestCase {

	private static final int ETALONE_OF_AGE1 = 19;
	private static final int ETALONE_OF_AGE2 = 18;
	private static final int ETALONE_OF_AGE3 = 21;
	private static final int MONTH_OF_BIRTH = 4;
	private static final int YEAR_OF_BIRTH = 2000;
	private static final int DAY_OF_BIRTH = 8;
	private User user;
	
	public void testGetFullName() {
		user.setFirstName("Andrii");
		user.setLastName("Hura");
		assertEquals("Hura, Andrii", user.getFullName());
	}
	
	public void testGetAge1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALONE_OF_AGE1, user.getAge());
	}
	
	public void testGetAge2() {
		boolean boolValue = false;
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		if(user.getAge() > ETALONE_OF_AGE2)
		{
			boolValue = true;
		}
		assertTrue("We can't sell you alcohol or tabaco sir", boolValue);
	}
	
	public void testGetAge3() {
		boolean boolValue = false;
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		if(user.getAge() > ETALONE_OF_AGE3)
		{
			boolValue = true;
		}
		assertTrue("We can't sell you strong alcohol sir", boolValue);
	}
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
