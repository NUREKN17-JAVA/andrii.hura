package ua.nure.cs.hura.usermanagement.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ua.nure.cs.hura.usermanagement.domain.User;

public class MockUserDao implements Dao {
	private long id = 0;
	private Map<Long, User> users = new HashMap<> ();
	
	public User create(User user) throws DatabaseException {
        Long id = new Long(users.size() + 1);
        users.put(id, user);
        user.setId(id);

        return user;
    }

    public void update(User user) throws DatabaseException {
        users.put(new Long(user.getId()), user);
    }

    public void delete(User user) throws DatabaseException {
        users.remove(new Long(user.getId()));
    }

    public User find(Long id) throws DatabaseException {
        return users.get(id);
    }

    public Collection<User> findAll() throws DatabaseException {
        return users.values();
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
    }

	@Override
	public Object create(Object entity) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}




}