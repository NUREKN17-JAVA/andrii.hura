package ua.nure.cs.hura.usermanagement.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

	public DatabaseException(SQLException e) {
		super(e);
	}

	public DatabaseException(String string) {
		super(string);
	}


}
