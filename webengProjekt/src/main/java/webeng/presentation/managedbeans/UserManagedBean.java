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
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String login() {
		if(manager.loginSucceeded(user)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
		} else {
			
		}
		return "/startseite.xhtml";
	}
	
	public String logout(User user) {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/startseite.xhtml";
	}
	
	public String registrieren() {
		manager.addUser(user.getID(), user.getName(), user.getEmail(), user.getPasswort(), user.getSessionID());
		return "/startseite.xhtml";
	}
	
	
	
}//end class UserManagedBean