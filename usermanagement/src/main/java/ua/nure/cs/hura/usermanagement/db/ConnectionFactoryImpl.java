package ua.nure.cs.hura.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	private String url = "jdbc:hsqldb:file:db/usermanagement";
	private String user = "sa";
	private String password = "";

	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseException(e);
		}
	}

}
