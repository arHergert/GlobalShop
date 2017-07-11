package webeng.businesslogic;

import java.util.List;

import webeng.data.DAOFactory;
import webeng.data.DAOFactory.Backend;
import webeng.transferobjects.Transaction;
import webeng.data.TransactionDAO;

public class TransactionManager {

	TransactionDAO transactionDao;
	
	public TransactionManager() {
		transactionDao = DAOFactory.getDAOFactory(Backend.H2).getTransactionDAO();
	}
	
	public void addTransaction(Transaction transaction) {
		
		transactionDao.addTransaction(transaction);
		
	}
	
	public Transaction getTransaction(int id) {
		return transactionDao.getTransaction(id);
	}
	
	public List<Transaction> getTransactions() {
		return transactionDao.getTransactions();
	}
	
	public List<Transaction> getTransactionsByUser(int id) {
		return transactionDao.getTransactionsByUser(id);
	}
	
}
