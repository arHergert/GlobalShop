package webeng.presentation.managedbeans;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import webeng.businesslogic.UserManager;
import webeng.transferobjects.User;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private UserManager manager;
	private User user;

	public UserManagedBean() {
		
	}
	
	
	@PostConstruct
	public void init() {
		manager = new UserManager();
		
		
		//MockUp User initialisieren
		user = new User();
		user.setEmail("mock@up.de");
		user.setID(0);
		user.setName("Michael Mockup");
		user.setPasswort("mockup123");
		user.setSessionID("4815162342");
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String login() {
		
		//MockupUser anmelden und in Session speichern
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
		
		/**
		if(manager.loginSucceeded(user)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
		} else {
			
		}
		*/
		return "startseite.xhtml";
	}
	
	
	public String logout() {
		user.setSessionID("-1");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "startseite.xhtml";
	}
	
	public String registrieren() {
		//manager.addUser(user.getID(), user.getName(), user.getEmail(), user.getPasswort(), user.getSessionID());
		System.out.println("REGISTRIEREN");
		return "startseite.xhtml";
	}
	
	
	
}//end class UserManagedBean