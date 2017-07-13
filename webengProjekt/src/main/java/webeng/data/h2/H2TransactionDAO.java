package webeng.data.h2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webeng.data.TransactionDAO;
import webeng.transferobjects.Product;
import webeng.transferobjects.Transaction;

public class H2TransactionDAO implements TransactionDAO {

	private Connection connection;
	
	public H2TransactionDAO() {
		connection = H2DAOFactory.getConnection();
	}
	
	@Override
	public void addTransaction(Transaction transaction) {
		String statement = "INSERT INTO Transaction (TransactionID, UserID, ProductID, Quantity, Subtotal, TransactionDate) VALUES (default,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setInt(1, transaction.getUserId());
			stmt.setInt(2, transaction.getProductId());
			stmt.setInt(3, transaction.getQuantity());
			stmt.setFloat(4, transaction.getSubtotal());
			stmt.setDate(5, transaction.getDate());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Transaction getTransaction(int id) {
		
		String statement = "SELECT * FROM Transaction WHERE TransactionID = " + id;
		int tid=0,pid=0,uid=0,quantity=0;
		float subtotal=0;
		Date date=new Date(0);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			tid = rs.getInt("transactionid");
			pid = rs.getInt("productid");
			uid = rs.getInt("userid");
			quantity = rs.getInt("quantity");
			subtotal = rs.getFloat("subtotal");
			date = rs.getDate("transactiondate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Transaction(tid,uid,pid,quantity,subtotal,date);
	}

	@Override
	public List<Transaction> getTransactions() {
		String statement = "SELECT * FROM Transaction;";
		List<Transaction> list = new ArrayList<>();
		int tid=0,pid=0,uid=0,quantity=0;
		float subtotal=0;
		Date date=new Date(0);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tid = rs.getInt("transactionid");
				pid = rs.getInt("productid");
				uid = rs.getInt("userid");
				quantity = rs.getInt("quantity");
				subtotal = rs.getFloat("subtotal");
				date = rs.getDate("transactiondate");
				list.add(new Transaction(tid,uid,pid,quantity,subtotal,date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
	}

	@Override
	public List<Transaction> getTransactionsByUser(int id) {
		String statement = "SELECT * FROM Transaction WHERE Userid = " + id;
		List<Transaction> list = new ArrayList<>();
		int tid=0,pid=0,uid=0,quantity=0;
		float subtotal=0;
		Date date=new Date(0);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tid = rs.getInt("transactionid");
				pid = rs.getInt("productid");
				uid = rs.getInt("userid");
				quantity = rs.getInt("quantity");
				subtotal = rs.getFloat("subtotal");
				date = rs.getDate("transactiondate");
				list.add(new Transaction(tid,uid,pid,quantity,subtotal,date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
