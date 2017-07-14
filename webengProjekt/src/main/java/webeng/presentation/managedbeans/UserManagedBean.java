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
	private static Map<Integer, Integer> warenkorb;
	private static List<Integer> warenkorbKeys;
	private static float warenkorbSum;
	private String currentViewedProduct;
	private String tempName, tempEmail, tempPassword;
	

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

	public String getCurrentViewedProduct() {
		return currentViewedProduct;
	}

	public void setCurrentViewedProduct(String currentViewedProduct) {
		this.currentViewedProduct = currentViewedProduct;
	}

	
	
	public String login() {
		
		if(manager.loginSucceeded(user)) {
			user.setID(manager.getUser(user.getEmail()).getID());
			user.setName(manager.getUser(user.getEmail()).getName());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("warenkorb", warenkorb);
			user.setSessionID(FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));
			manager.updateSessionId(user);
			tempName = user.getName();
			tempEmail = user.getEmail();
			tempPassword = user.getPassword();
			System.out.println("User logged: " + user.getID() + " " + tempName + " " + tempEmail + " " + tempPassword);
			return "login_success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail oder Passwort falsch. Bitte erneut versuchen!", null));
			return "login_failed";
		}
		
		
	}
	
	public String cartAddItem() {
		
		System.out.println("VIEW: " +currentViewedProduct);
		int id = Integer.parseInt(currentViewedProduct);
		ProductManager pm = new ProductManager();
		if(warenkorb.containsKey(id)) {
			warenkorb.replace(id, warenkorb.get(id), warenkorb.get(id)+1);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("warenkorb", warenkorb);
			warenkorbKeys = new ArrayList<Integer>(warenkorb.keySet());
			warenkorbSum += ( (pm.getProduct(id).getPrice()) );
		} else {
			warenkorb.put(id, 1);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("warenkorb", warenkorb);
			warenkorbKeys = new ArrayList<Integer>(warenkorb.keySet());
			warenkorbSum += (pm.getProduct(id).getPrice()*warenkorb.get(id));
		}
		return "produkte.xhtml";
	}
	
	public String cartDeleteItem(Integer key) {
		ProductManager pm = new ProductManager();
		warenkorbSum -= ( (pm.getProduct(key).getPrice()) *(warenkorb.get(key)) );
		warenkorb.remove(key);
		warenkorbKeys = new ArrayList<Integer>(warenkorb.keySet());
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("warenkorb", warenkorb);
		
		return "warenkorb.xhtml";
	}
	
	public String cartUpdateItem(Integer id, Integer quantity) {
		return "warenkorb.xhtml";
	}
	
	public static void clearCart() {
		warenkorb = new TreeMap<>();
		warenkorbKeys = new ArrayList<Integer>();
		warenkorbSum = 0;
	}
	
	public String logout() {
		user.setSessionID("-1");
		manager.updateSessionId(user);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "logout";
	}
	
	public String registrieren() {
		System.out.println("Registrierung: "+manager.addUser(user.getName(), user.getEmail(), user.getPassword(), user.getSessionID()));
		user.setID(manager.getUser(user.getEmail()).getID());
		return "register_success";
	}
	
	public float getWarenkorbSum() {
		return warenkorbSum;
	}
	
	public List<Integer> getWarenkorbKeys(){
	     return warenkorbKeys;
	}
	
	public String update(){
		System.out.println("Update User: " + user.getID() + " " +tempName + " " + tempEmail + " " + tempPassword);
		manager.updateUser(new User(user.getID(), tempName, tempEmail, tempPassword, ""));
		return "myAccount.xhtml";
	}
	
	public String reset() {
		return "register_reset";
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	public String getTempEmail() {
		return tempEmail;
	}

	public void setTempEmail(String tempEmail) {
		this.tempEmail = tempEmail;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	
}//end class UserManagedBean