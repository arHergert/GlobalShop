package webeng.presentation.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import webeng.businesslogic.TransactionManager;
import webeng.transferobjects.Transaction;

@ManagedBean(name = "transactionBean")
@RequestScoped
public class TransactionManagedBean implements Serializable {

	private Transaction transaction;
	private TransactionManager manager;
	
	public TransactionManagedBean() {
		
	}
	
	@PostConstruct
	public void init() {
		manager = new TransactionManager();
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public List<Transaction> getTransactionsByUser() {
		List<Transaction> r = new ArrayList<>();
		r.add(new Transaction(1, 1, 1, 4, 2.99F, new java.sql.Date(0)));
		r.add(new Transaction(2, 1, 2, 6, 256.99F, new java.sql.Date(1)));
		return r;
	}
	
	
	
}//end class TransactionManagedBean
