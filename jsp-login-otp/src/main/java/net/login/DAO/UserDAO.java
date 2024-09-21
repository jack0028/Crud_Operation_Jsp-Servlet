package net.login.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import net.login.model.User;


public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/sample?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Jebaraj2002@";
    
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, contact, otp, country) VALUES " +
            " (?, ?, ?, ?, ?);";

        private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
        private static final String SELECT_ALL_USERS = "select * from users";
        private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
        private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?,contact=?, otp=?  country =? where id = ?;";
        private static final String LOGIN_USER = "SELECT contact FROM users WHERE contact = ? AND otp = ?;";

        protected Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return connection;
        }
        public boolean loginEmployee(User user) throws ClassNotFoundException {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false", "root", "Jebaraj2002@");
                 PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER)) {
                
                preparedStatement.setString(1, user.getContact());
                preparedStatement.setLong(2, user.getOtp());

                ResultSet rs = preparedStatement.executeQuery();
                System.out.println(preparedStatement);
                return rs.next();  // returns true if a match is found

            } catch (SQLException e) {
                printSQLException(e);
            }
            return false;
        }

        
        public void insertUser(User user) throws SQLException {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getContact());
                preparedStatement.setInt(4, user.getOtp());
                preparedStatement.setString(5, user.getCountry());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public User selectUser(int id) {
            User user = null;
            try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String country = rs.getString("country");
                    int otp =rs.getInt("otp");
					String contact = rs.getString("contact");
					user = new User(id, name, email,contact, otp, country);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return user;
        }

        

        public List<User> selectAllUsers() {
            List<User> users = new ArrayList<>();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String contact = rs.getString("contact");
                    int otp = rs.getInt("otp");
                    String country = rs.getString("country");
                    users.add(new User(id, name, email, contact, otp, country));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return users;
        }


        public boolean deleteUser(int id) throws SQLException {
            boolean rowDeleted;
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
            }
            return rowDeleted;
        }


        public boolean updateUser(User user) throws SQLException {
            boolean rowUpdated;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getContact());
                statement.setInt(4, user.getOtp());
                statement.setString(5, user.getCountry());
                statement.setInt(6, user.getId());

                rowUpdated = statement.executeUpdate() > 0;
            }
            return rowUpdated;
        }

        private void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }

}
