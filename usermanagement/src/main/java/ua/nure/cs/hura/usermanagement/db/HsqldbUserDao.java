package ua.nure.cs.hura.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.cs.hura.usermanagement.domain.User;

public class HsqldbUserDao implements Dao<User> {
	
	private static final String CALL_IDENTITY = "call IDENTITY()";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES(?, ? ,?);";
	ConnectionFactory connectionFactory;

	public HsqldbUserDao(ConnectionFactory connectionFactory2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create(User entity) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setDate(3, new Date(entity.getDateOfBirth()));
			int numberOfRows = preparedStatement.executeUpdate();
			if(numberOfRows != 1) {
				throw new DatabaseException("Number of inserted rows is wrong");
			}
			CallableStatement callableStatement = 
					connection.prepareCall(CALL_IDENTITY);
			ResultSet keys = callableStatement.executeQuery();
			if(keys.next()) {
				entity.setId(keys.getLong(1));
			}
			keys.close();
			callableStatement.close();
			preparedStatement.close();
			connection.close();
			return entity;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void update(User entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> findAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
