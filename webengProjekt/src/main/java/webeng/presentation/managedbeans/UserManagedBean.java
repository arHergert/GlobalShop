package webeng.presentation.managedbeans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import webeng.businesslogic.ProductManager;
import webeng.businesslogic.UserManager;
import webeng.transferobjects.Product;
import webeng.transferobjects.User;

@SessionScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private UserManager manager;
	private User user;
	private Map<Integer, Integer> warenkorb;
	private List<Integer> warenkorbKeys;
	private float warenkorbSum;
	
	public UserManagedBean() {
		
	}
	
	@PostConstruct
	public void init() {
		manager = new UserManager();
		
		
		//MockUp Daten initialisieren
		user = new User();
		warenkorb = new TreeMap<>();
		warenkorbSum = 0;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String login() {
		
		if (user.getEmail().equals("mock@up.de") && user.getPasswort().equals("mockup123")){
			user.setEmail("mock@up.de");
			user.setID(0);
			user.setName("Michael Mockup");
			user.setPasswort("mockup123");
			user.setSessionID("4815162342");
			//MockupUser anmelden und in Session speichern
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
			
			
			ProductManager pm = new ProductManager();
			warenkorb.put(pm.getProducts().get(1).getId(), 20);
			warenkorb.put(pm.getProducts().get(2).getId(), 12);
			warenkorb.put(pm.getProducts().get(3).getId(), 9);
			
			warenkorbKeys = new ArrayList<Integer>(warenkorb.keySet());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("warenkorb", warenkorb);
			
			for(Map.Entry<Integer,Integer> entry : warenkorb.entrySet()) {
				warenkorbSum += (pm.getProduct(entry.getKey()).getPrice() * entry.getValue() );
			}
			
			/**
			if(manager.loginSucceeded(user)) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
			} else {
				
			}
			*/
			return "login_success";
		}else{
			System.out.println("LOGIN NICHT ERFOLGREICH");
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail oder Passwort falsch. Bitte erneut versuchen!", null));
			return "login_failed";
		}
		
	}
	
	public String cartAddItem(String key) {
		int id = Integer.parseInt(key);
		if(warenkorb.containsKey(id)) {
			warenkorb.replace(id, warenkorb.get(Integer.parseInt(key)), warenkorb.get(Integer.parseInt(key)+1));
		} else {
			warenkorb.put(id, 1);
		}
		return "";
	}
	
	public String cartDeleteItem(Integer key) {
		ProductManager pm = new ProductManager();
		warenkorbSum -= (pm.getProduct(key).getPrice()*warenkorb.get(key));
		warenkorb.remove(key);
		warenkorbKeys = new ArrayList<Integer>(warenkorb.keySet());
		
		return "warenkorb.xhtml";
	}
	
	public String cartUpdateItem(Integer id, Integer quantity) {
		return "warenkorb.xhtml";
	}
	
	public String logout() {
		user.setSessionID("-1");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "logout";
	}
	
	public String registrieren() {
		//manager.addUser(user.getID(), user.getName(), user.getEmail(), user.getPasswort(), user.getSessionID());
		System.out.println("REGISTRIEREN");
		return "register_success";
	}
	
	public float getWarenkorbSum() {
		return warenkorbSum;
	}
	
	public List<Integer> getWarenkorbKeys(){
	     return warenkorbKeys;
	}
	
	
	public String update(){
		return "";
	}
	
	
}//end class UserManagedBean