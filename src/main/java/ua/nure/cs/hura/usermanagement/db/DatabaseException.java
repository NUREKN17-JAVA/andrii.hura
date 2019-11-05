package ua.nure.cs.hura.usermanagement.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {
	
	
	
	private static final long serialVersionUID = 1L;

	public DatabaseException(SQLException e) {
		super(e);
	}

	public DatabaseException(String string) {
		super(string);
	}


}
