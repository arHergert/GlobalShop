package webeng.data.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import webeng.data.DAOFactory;
import webeng.data.ProductDAO;
import webeng.data.TransactionDAO;
import webeng.data.UserDAO;


public class H2DAOFactory extends DAOFactory {
	
	private static final String DBDRIVER = "org.h2.Driver";
	private static final String DBURL = "jdbc:h2:./build/WebEngProjDB";
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
		return new H2UserDAO();
	}



	@Override
	public ProductDAO getProductDAO() {
		// TODO Auto-generated method stub
		return new H2ProductDAO();
	}



	@Override
	public TransactionDAO getTransactionDAO() {
		// TODO Auto-generated method stub
		return new H2TransactionDAO();
	} 
	
}//end class H2DAOFactory