package amara.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	final private static String HOST = "localhost";
	final private static int PORT = 3306;
	final private static String DB_NAME = "amara";
	
	final private static String USER = "amara_user";
	final private static String PASSWORD = "amara";
	
	private static ConnectionManager instance;
	
	private final Connection amara;
	
	private ConnectionManager() throws SQLException, IOException {
		
		final String url = String.format("jdbc:mariadb://%s:%s/%s",
				HOST,
				PORT,
				DB_NAME);
		
		final Properties props = new Properties();
		props.setProperty("user", USER);
		props.setProperty("password", PASSWORD);
//		props.setProperty("ssl", "false");
		
		this.amara = DriverManager.getConnection(url, props);
	}
	
	public static ConnectionManager getInstance() throws SQLException, IOException {
		if(ConnectionManager.instance == null) {
			ConnectionManager.instance = new ConnectionManager();
		}
		return ConnectionManager.instance;
	}
	
	
	public Connection getDBConn() {
		return amara;
	}

	public static void closeConnections() throws SQLException, IOException {
		instance.amara.close();
//		Logging.info("DB connections are closed");
	}

}
