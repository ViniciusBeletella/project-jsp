package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import beans.BeansJsp;
import db.SingleConnection;


public class DaoUser {

	private Connection connection;

	public DaoUser() {
		connection = SingleConnection.getConnection();
	}

	public void save(BeansJsp user) {

		try {

			String sql = "insert into public.user (login, password, name) values(?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, user.getLogin());
			insert.setString(2, user.getPassword());
			insert.setString(3, user.getName());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<BeansJsp> listar() throws Exception {
		List<BeansJsp> listar = new ArrayList<BeansJsp>();

		String sql = "select * from public.user";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			BeansJsp beanJsp = new BeansJsp();
			beanJsp.setId(resultSet.getLong("id"));
			beanJsp.setLogin(resultSet.getString("login"));
			beanJsp.setPassword(resultSet.getString("password"));
			beanJsp.setName(resultSet.getString("name"));
			listar.add(beanJsp);
		}
		return listar;

	}
	
	public void delete (String id) {
		
		try {
		String sql ="delete from public.user where id ='" + id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.execute();
		
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public BeansJsp consultar (String id) throws Exception {
		String sql="select * from public.user where id ='" + id + "'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeansJsp beansJsp = new BeansJsp();
			beansJsp.setId(resultSet.getLong("id"));
			beansJsp.setLogin(resultSet.getString("login"));
			beansJsp.setPassword(resultSet.getString("password"));
			beansJsp.setName(resultSet.getString("name"));
			return beansJsp;
		}
		
		return null;
		
	}
	
	
	public boolean validarLogin (String login) throws Exception {
		String sql="select count(1) as qtd from public.user where login ='" + login + "'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			
			return resultSet.getInt("qtd") <=0;
		}
		
		return false;
		
	}

	public void update(BeansJsp user) {
		
		try {
		String sql = "update public.user set login = ?, password = ?, name = ? where id = " + user.getId();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getLogin());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.executeUpdate();
		connection.commit();
		}catch (Exception e) {
			e.printStackTrace();	
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
	}

}
