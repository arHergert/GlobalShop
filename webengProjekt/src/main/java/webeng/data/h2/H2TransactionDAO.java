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
		String statement = "INSERT INTO Transaction (TransactionID, UserID, ProductID, Quantity, Subtotal, TransactionDate) VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setInt(1, transaction.getTransactionId());
			stmt.setInt(2, transaction.getUserId());
			stmt.setInt(3, transaction.getProductId());
			stmt.setInt(4, transaction.getQuantity());
			stmt.setFloat(5, transaction.getSubtotal());
			stmt.setDate(6, transaction.getDate());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Transaction getTransaction(int id) {
		
		String statement = "SELECT * FROM Transaction WHERE TransactionID = " + id;
		Transaction t = new Transaction();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			t.setTransactionId(rs.getInt("transactionid"));
			t.setProductId(rs.getInt("productid"));
			t.setUserId(rs.getInt("userid"));
			t.setQuantity(rs.getInt("quantity"));
			t.setSubtotal(rs.getFloat("subtotal"));
			t.setDate(rs.getDate("transactiondate"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public List<Transaction> getTransactions() {
		String statement = "SELECT * FROM Transaction;";
		List<Transaction> list = new ArrayList<>();
		Transaction t = new Transaction();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				t.setTransactionId(rs.getInt("transactionid"));
				t.setProductId(rs.getInt("productid"));
				t.setUserId(rs.getInt("userid"));
				t.setQuantity(rs.getInt("quantity"));
				t.setSubtotal(rs.getFloat("subtotal"));
				t.setDate(rs.getDate("transactiondate"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
	}

}
