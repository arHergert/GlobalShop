package webeng.presentation.managedbeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import webeng.businesslogic.ProductManager;
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
	
	public String addTransactions() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		ProductManager pm = new ProductManager();
		Map<Integer, Integer> sessionMap = (Map<Integer, Integer>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("warenkorb");
		Map<Integer, Integer> warenkorb = new TreeMap<>();
		warenkorb.putAll(sessionMap);
		Set<Entry<Integer, Integer>> set = warenkorb.entrySet();
		
		for(Entry<Integer, Integer> e : set) {
			float subtotal = pm.getProduct(e.getKey()).getPrice()*e.getValue();
			manager.addTransaction(new Transaction(0,user.getID(),e.getKey(),e.getValue(),subtotal,new Date(System.currentTimeMillis())));
		}
		
		UserManagedBean.clearCart();
		
		return "warenkorb.xhtml";
	}
	
	
	
}//end class TransactionManagedBean
