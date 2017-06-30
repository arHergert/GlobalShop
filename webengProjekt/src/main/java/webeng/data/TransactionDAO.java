package webeng.data;

import java.util.List;

import webeng.transferobjects.Transaction;

public interface TransactionDAO {

	public void addTransaction(Transaction transaction);
//	public Transaction getTransaction(Transaction transaction);
	public Transaction getTransaction(int id);
	public List<Transaction> getTransactions();
	
}
