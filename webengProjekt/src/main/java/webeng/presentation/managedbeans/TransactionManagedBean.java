package webeng.presentation.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import webeng.businesslogic.TransactionManager;
import webeng.transferobjects.Transaction;
import webeng.transferobjects.User;

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
		
		
		User tempUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		int id = tempUser.getID();
		
		for(Transaction t : manager.getTransactionsByUser(id)) {
			System.out.println(t.getTransactionId()  + " " + t.getUserId() + " " + t.getProductId());
		}
		return manager.getTransactionsByUser(id);
	}
	
	
	
}//end class TransactionManagedBean
