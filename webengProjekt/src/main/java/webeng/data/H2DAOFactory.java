package webeng.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import webeng.data.DAOFactory;
import webeng.data.UserDAO;


public class H2DAOFactory extends DAOFactory {
	
	private static final String DBDRIVER = "org.h2.Driver";
	private static final String DBURL = "jdbc:h2:./build/WebEng07DB";
	private static final String DBUSER = "sa";
	private static final String DBPASSWORD = "sa";

	private static Connection openConnection() {
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			System.out.println("Connected to database");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | 	ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return connection;
	}
	
//	private static void closeConnection() throws SQLException {
//		connection.createStatement().execute("SHUTDOWN");
//	}
	
	private static Connection connection;

	public static Connection getConnection() {
		if(connection == null)
			connection = openConnection();
		return connection;
	}



	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ProductDAO getProductDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TransactionDAO getTransactionDAO() {
		// TODO Auto-generated method stub
		return null;
	} 
	
}//end class H2DAOFactory