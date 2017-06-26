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
	
	public Transaction getTransaction(Transaction transaction) {
		return transactionDao.getTransaction(transaction);
	}
	
	public List<Transaction> findTransactions() {
		
		return transactionDao.findTransactions();
	}
	
}
