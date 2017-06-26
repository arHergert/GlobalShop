package webeng.presentation.managedbeans;

import java.io.Serializable;

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
	
	
	
}//end class TransactionManagedBean
