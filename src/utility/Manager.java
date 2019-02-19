package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Manager {
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/project");
			
			connection = dataSource.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return connection;
	} // getConnection();
	
	// SELECT 문을 수행 후 RESOURCE 해제를 위한 메서드
	public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (connection != null) connection.close();
			if (preparedStatement != null) preparedStatement.close();
			if (resultSet != null) resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} // SELECT close();
	
	// INSERT, UPDATE, DELETE 문을 수행 후 RESOURCE 해제를 위한 메서드
	public static void close(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (connection != null) connection.close();
			if (preparedStatement != null) preparedStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} // INSERT, UPDATE, DELETE close();
}
