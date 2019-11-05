package ua.nure.cs.hura.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.cs.hura.usermanagement.domain.User;

public class HsqldbUserDao implements Dao<User> {
	
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String FIND_QUERY = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_QUERY = "SELECT * FROM users";
	private static final String CALL_IDENTITY = "call IDENTITY()";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES(?, ? ,?);";
	ConnectionFactory connectionFactory;
	
	public ConnectionFactory getConnectionFactory() {
	    return connectionFactory;
	  }
	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
	    this.connectionFactory = connectionFactory;
	  }
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User entity) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
		try {
			
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			int numberOfRows = preparedStatement.executeUpdate();
			if(numberOfRows != 1) {
				throw new DatabaseException("Number of inserted rows is wrong: " +numberOfRows);
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
		 try {
	            Connection connection = connectionFactory.createConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
	            preparedStatement.setString(1, entity.getFirstName());
	            preparedStatement.setString(2, entity.getLastName());
	            preparedStatement.setDate(3, new Date(entity.getDateOfBirth()
	                                                        .getTime()));
	            preparedStatement.setLong(4, entity.getId());

	            int changedRows = preparedStatement.executeUpdate();

	            if (changedRows != 1) {
	                throw new DatabaseException("Number of inserted rows: " + changedRows);
	            }

	            connection.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            throw new DatabaseException(e);
	        }
		
	}

	@Override
	public void delete(User entity) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
			statement.setLong(1, entity.getId());
			int removedRows = statement.executeUpdate();

            if (removedRows != 1) {
                throw new DatabaseException("Number of removed rows: " + removedRows);
            }
			
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}

	@Override
	public User find(Long id) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
		User user = new User();
		try {
			PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return user;
	}

	@Override
	public Collection<User> findAll() throws DatabaseException {
		Collection<User> result = new LinkedList<User>();
		
		Connection connection = connectionFactory.createConnection();
		Statement statement;
		try {
			
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
			
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		return result;
	}

}
