package webeng.presentation.managedbeans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import webeng.businesslogic.ProductManager;
import webeng.businesslogic.UserManager;
import webeng.transferobjects.Product;
import webeng.transferobjects.User;

@SessionScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private UserManager manager;
	private User user;
	private Map<String, Integer> warenkorb;
	private List<String> warenkorbKeys;
	public UserManagedBean() {
		
	}
	
	
	@PostConstruct
	public void init() {
		manager = new UserManager();
		
		
		//MockUp Daten initialisieren
		user = new User();
		warenkorb = new TreeMap<>();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String login() {
		user.setEmail("mock@up.de");
		user.setID(0);
		user.setName("Michael Mockup");
		user.setPasswort("mockup123");
		user.setSessionID("4815162342");
		//MockupUser anmelden und in Session speichern
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
		
		
		ProductManager pm = new ProductManager();
		warenkorb.put(pm.getProducts().get(1).getName(), 20);
		warenkorb.put(pm.getProducts().get(2).getName(), 12);
		warenkorb.put(pm.getProducts().get(3).getName(), 9);
		
		warenkorbKeys = new ArrayList<String>(warenkorb.keySet());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("warenkorb", warenkorb);
		
		System.out.println("LOGIN");
		
		/**
		if(manager.loginSucceeded(user)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
		} else {
			
		}
		*/
		return "startseite.xhtml";
	}
	
	
	public String cartDeleteItem(String key) {
		warenkorb.remove(key);
		warenkorbKeys = new ArrayList<String>(warenkorb.keySet());
		
		return "warenkorb.xhtml";
	}
	
	public String logout() {
		user.setSessionID("-1");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "startseite.xhtml";
	}
	
	public String registrieren() {
		//manager.addUser(user.getID(), user.getName(), user.getEmail(), user.getPasswort(), user.getSessionID());
		System.out.println("REGISTRIEREN");
		return "login.xhtml";
	}
	
	
	public List<String> getWarenkorbKeys(){
	     return warenkorbKeys;
	}
	
	
	
}//end class UserManagedBean