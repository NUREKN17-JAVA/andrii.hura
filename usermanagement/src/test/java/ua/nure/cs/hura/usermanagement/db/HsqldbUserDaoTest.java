package ua.nure.cs.hura.usermanagement.db;


import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import ua.nure.cs.hura.usermanagement.domain.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	
	private static final int FIRST_NAME = 19;
	private static final int LAST_NAME = 19;
	private HsqldbUserDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao();
		
	}
	
	public void testCreate() throws DatabaseException {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDateOfBirth(new Date());
		User userToCheck = dao.create(user);
		assertNotNull(userToCheck);
		assertNotNull(userToCheck.getId());
		assertEquals(user.getFirstName(), userToCheck.getFirstName());
		assertEquals(user.getLastName(), userToCheck.getLastName());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
