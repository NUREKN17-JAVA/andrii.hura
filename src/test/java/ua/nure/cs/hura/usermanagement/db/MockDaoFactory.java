package ua.nure.cs.hura.usermanagement.db;

import com.mockobjects.dynamic.Mock;

import ua.nure.cs.hura.usermanagement.domain.User;

public class MockDaoFactory extends DaoFactory {

	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(Dao.class);
	}

	public Mock getMockUserDao() {
	       return mockUserDao;
	}
	    
	public Dao<User> getUserDao() {
	       return (Dao<User>) mockUserDao.proxy();
	}

}