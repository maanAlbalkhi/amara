package amara.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class DBController {
	
	public static String registerUser(final String firstName,
			final String lastName,
			final String email,
			final String password) throws SQLException, IOException {
		
		final String query = "INSERT INTO user "
				+ "(id, first_name, last_name, email, password) "
				+ "VALUES (?, ?, ?, ?, ?)";

		final Connection conn = ConnectionManager.getInstance().getDBConn();
		final PreparedStatement statement = conn.prepareStatement(query);
		
		final String uuid = UUID.randomUUID().toString();
		
		statement.setString(1, uuid);
		statement.setString(2, firstName);
		statement.setString(3, lastName);
		statement.setString(4, email);
		statement.setString(5, password);
		
		final int rows = statement.executeUpdate();
		statement.close();
		
		if(rows == 1) {
			return uuid;
		} else {
			return "SOME THING WENT WRONG";
		}

	}
	
	public static String getPassword(final String email) throws SQLException, IOException {
		
		final String query = "SELECT password "
				+ "FROM user "
				+ "WHERE email = ?";
		
		final Connection conn = ConnectionManager.getInstance().getDBConn();
		final PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, email);
		
		final ResultSet resultSet = statement.executeQuery();
		String password = null; 
		while (resultSet.next()) {
			password = resultSet.getString("password"); 
		}
		
		resultSet.close();
		statement.close();
		
		return password;
	}

}
