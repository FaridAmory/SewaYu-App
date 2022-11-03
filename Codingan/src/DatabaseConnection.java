
import java.sql.*;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData resultSetMetaData;
	public PreparedStatement preparedStatement;
	

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/badfinalproject", "root", "");
			statement = connection.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet insertUser(User newUser) {
    	try {
			preparedStatement=connection.prepareStatement("insert into customer(customer_Name,customer_PhoneNumber,customer_Password,customer_Gender,customer_Address) values(?,?,?,?,?)"); 
			preparedStatement.setString(1,newUser.getUsername());
			preparedStatement.setString(2,newUser.getPhoneNumber());
			preparedStatement.setString(3,newUser.getPassword());
			preparedStatement.setString(4,newUser.getGender());
			preparedStatement.setString(5,newUser.getAddress());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
    	
    }

	public ResultSet getUserData(String username, String password) {
        try {
            preparedStatement = connection.prepareStatement("Select *from customer where customer_Name=? and customer_Password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

        } catch (Exception e) {
            e.printStackTrace();	
        }
        return resultSet;
    }
	//
	public ResultSet query(String sql) {
		try {
			Statement st = connection.createStatement();
			return st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeUpdate(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exec(String sql) {
		try {
			Statement st = connection.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepare(String sql) {
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
