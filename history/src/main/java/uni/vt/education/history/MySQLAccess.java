package uni.vt.education.history;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import uni.vt.education.history.model.User;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public User login(User user) {
		try {
			// This will load the MySQL driver, each DB has its own driver
//			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/history");
			// + "user=sqluser&password=sqluserpw");

			preparedStatement = connect
					.prepareStatement("Select name, score, rank from users where email=? and password=?");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user.setName(resultSet.getString("name"));
				user.setRank(resultSet.getString("rank"));
				user.setScore(resultSet.getInt("score"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return user;
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
