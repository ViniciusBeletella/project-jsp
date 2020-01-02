package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * 
 * 
 * @author viniciusbeletella
 *
 */

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost/project-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "root";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR to Connect");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
