package uni.vt.education.history.db;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uni.vt.education.history.model.User;

/**
 * 
 * @author Gabriela Zaharieva, Asparuh Vitkinov
 */
public class UserDAO {
	private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin";
	private Connection connection;

	public UserDAO() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUser(User user) throws SQLException {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PASSWORD);
			String sql = "INSERT into users (username, password, email) values(?, ?, ?)";
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, user.getUsername());
			statment.setString(2, user.getPassword());
			statment.setString(3, user.getEmail());

			System.err.println("sql=" + sql);
			statment.execute();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		}
	}

	public User getUser(String email, String password) {
		User user = null;
		try {
			PreparedStatement statement;
			String sql = "SELECT id, username FROM users "
					+ " WHERE email=? AND password=?";// WHERE name like '%" +
														// name + "%'  ";
			connection = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PASSWORD);
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				user = new User(id, email, password, username);
			}
		} catch (SQLException a) {
			a.printStackTrace();
			System.err.println(" getUser  ERROR -  " + a);
		}

		return user;
	}

	public boolean isEmailUsed(String email) {
		boolean result = false;
		try {
			PreparedStatement statement;
			String sql = "SELECT * FROM users WHERE email = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				result = true;
			}
		} catch (SQLException a) {
			System.err.println(" isEmailUsed  ERROR -  " + a);
		}
		return result;
	}

}
