package net.javaguides.registration.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.registration.model.Employee;

public class EmployeeDao {
    private static final String LOGIN_USER = "SELECT username FROM employee WHERE username = ? AND password = ?;";
    private static final String SELECT_ALL_USERS = "select id,first_name, last_name, username, address, contact from employee";

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO employee" +
	            "  (first_name, last_name, username, password, address, contact) VALUES " +
	            " (?, ?, ?, ?, ?,?);";

	        int result = 0;

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false", "root", "Jebaraj2002@");

	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setString(1, employee.getFirstName());
	            preparedStatement.setString(2, employee.getLastName());
	            preparedStatement.setString(3, employee.getUsername());
	            preparedStatement.setString(4, employee.getPassword());
	            preparedStatement.setString(5, employee.getAddress());
	            preparedStatement.setString(6, employee.getContact());

	            System.out.println(preparedStatement);
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return result;
	    }

	    
		public static List<Employee> selectAllUsers() {
			List < Employee > employees = new ArrayList < > ();
	         try (Connection connection = DriverManager
	 	            .getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false", "root", "Jebaraj2002@");

	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
	             System.out.println(preparedStatement);
	             ResultSet rs = preparedStatement.executeQuery();

	             while (rs.next()) {
	                 int id = rs.getInt("id");
	                 String lastName = rs.getString("first_name");
	                 String firstName = rs.getString("last_name");
	                 String username = rs.getString("username");
	                 String address = rs.getString("address");
	                 String contact = rs.getString("contact");
	                 employees.add(new Employee(id, firstName, lastName, username, address,contact));
	             }
	         } catch (SQLException e) {
	             printSQLException(e);
	         }
	         return employees;
		}


    public boolean loginEmployee(Employee employee) throws ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false", "root", "Jebaraj2002@");
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER)) {

            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();  // returns true if a match is found

        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
