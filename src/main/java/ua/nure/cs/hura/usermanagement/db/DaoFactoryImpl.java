package ua.nure.cs.hura.usermanagement.db;

import ua.nure.cs.hura.usermanagement.domain.User;

public class DaoFactoryImpl extends DaoFactory {
	@SuppressWarnings("unchecked")
	@Override
	public Dao<User> getUserDao() {
		Dao<User> result = null;
		try {
			Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (Dao<User>) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
