package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.SingleConnection;

public class DaoLogin {

	private Connection connection;

	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}

	public boolean validarLogin(String uname, String password) throws Exception {

		String sql = "select * from public.user where login= '" +uname+ "' and password = '" + password + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return true;
		} else {
			return false;

		}
	}

}
