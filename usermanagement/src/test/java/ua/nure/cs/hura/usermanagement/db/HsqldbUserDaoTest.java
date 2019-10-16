package ua.nure.cs.hura.usermanagement.db;


import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import ua.nure.cs.hura.usermanagement.domain.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
		
	}
	
	public void testCreate() throws DatabaseException {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
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
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
