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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uni.vt.education.history.model.User;

/**
 *
 * @author Gabriela Zaharieva, Asparuh Vitkinov
 */
public class UserDAO {

    private Connection connection;

    public UserDAO() {
    	System.out.println("Contructor-UserDAO");
        String dbtime;
        String dbUrl = "jdbc:postgresql://localhost/postgres";
        String dbClass = "com.posgresql.jdbc.Driver";
        String username = "postgres";
        String password = "admin";
        String query = "Select * FROM users";

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrl, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            String sql = "INSERT into users (username, password, email) values(?, ?, ?)";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, user.getUsername());
            statment.setString(2, user.getPassword());
            statment.setString(3, user.getEmail());


            System.err.println("sql=" + sql);
            statment.execute();

        } catch (SQLException s) {
            s.printStackTrace();
        }
    }


    public User getUser(String email, String password) {
        User user = null;
        try {
        	System.err.println("get User from DB");
            PreparedStatement statement;
            String sql = "SELECT id, username FROM users "
                    + " WHERE email=? AND password=?";// WHERE name like '%" + name + "%'  ";
            
            String sqlAllUsers = "Select * FROM users";
            statement = connection.prepareStatement(sqlAllUsers);
            ResultSet result = statement.executeQuery();
            System.out.println("result:"+result.next());
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            System.out.println("result"+rs.next());
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
               
                
                user = new User(id, email, password, username );
                System.out.println("USER-"+user.getPassword());
                System.out.println("USER-"+user.getUsername());
               
            }

        } catch (SQLException a) {
            a.printStackTrace();
            System.err.println(" getUser  ERROR -  " + a);
        }
        return user;
    }


    public boolean isEmailUsed(String email) {

        try {
            PreparedStatement statement;
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException a) {
            System.err.println(" isEmailUsed  ERROR -  " + a);
        }
        return false;
    }
    

}
