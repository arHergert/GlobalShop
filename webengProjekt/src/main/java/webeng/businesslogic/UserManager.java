package webeng.businesslogic;

import java.util.List;

import webeng.data.DAOFactory;
import webeng.data.UserDAO;
import webeng.transferobjects.User;
import webeng.data.DAOFactory.Backend;

/**
 * Der Usermanager
 * @author Artur
 *
 */
public class UserManager {

	UserDAO userDAO;
	
	public UserManager() {
		super();
		userDAO = DAOFactory.getDAOFactory(Backend.H2).getUserDAO();
	}
	
	
	//IM
	
	
	
	/**
	 * Hinzufügen eines Benutzers.
	 * 
	 * @return FALSE wenn eine Email schon in der Datenbank existiert und deshalb kein Eintrag erfolgen kann.
	 * TRUE wenn Email und restliche Userinformation erfolgreich eingefügt wurden
	 * 
	 */
	public boolean addUser(int id, String name, String email, String passwort, String sessionid){
		
		User newUser = new User(id,name,email,passwort,sessionid);
		
		if(userDAO.isEmailRegistered(newUser) == false){
			userDAO.addUser(newUser);
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Auslesen eines Benutzers per email oder ID
	 * @param userSearch ID oder Email des gewünschten Users
	 * @return Der gewünschte User
	 */
	public User getUser (int id){
		return userDAO.getUser(id);
		
		
/*		int userid = 0;
		boolean stringIsID = true;
		try {
			userid = Integer.parseInt(userSearch);
		} catch (NumberFormatException e) {
			stringIsID = false;
		}
	
		if (stringIsID){
			newUser.setID(userid);
		}else{
			newUser.setEmail(userSearch);
		}
		return userDAO.getUser(newUser);*/
		
	}
	
	/**
	 * Auslesen eines Benutzers per email oder ID
	 * @param userSearch ID oder Email des gewünschten Users
	 * @return Der gewünschte User
	 */
	public User getUser (String userSearch){
		
		
		User newUser = new User();
		
		/*
		newUser.setID(userSearch);
		return userDAO.getUser(newUser);
		*/
		
		int userid = 0;
		boolean stringIsID = true;
		try {
			userid = Integer.parseInt(userSearch);
		} catch (NumberFormatException e) {
			stringIsID = false;
		}
	
		if (stringIsID){
			newUser.setID(userid);
		}else{
			newUser.setEmail(userSearch);
		}
		return userDAO.getUser(newUser);
		
	}
	
	
	/**
	 * Auslesen aller Nutzer
	 * @return Lister mit allen Nutzern
	 */
	public List<User> getAllUser(){
		
		return userDAO.findAllUsers();
	}
	
	
	/**
	 * Löscht einen User anhand seiner ID
	 * @param delUser
	 */
	public void deleteUser(int id){
		
		User user = new User();
		user.setID(id);
		userDAO.deleteUser(user);
	}
	
	
	public boolean updateUser(User updateUser){
		List<User> userList = getAllUser();
		for (User u: userList){
			if(u.getEmail().equals(updateUser.getEmail()) && u.getID() != updateUser.getID()){
				
				return false;
			}
		}
		
		userDAO.updateUser(updateUser);
		return true;
	}
	
	public boolean loginSucceeded(User loginUser){
		User compareUser;
		
		try{
			compareUser = getUser(loginUser.getID()); 
		}catch (Exception e){
			//User wurde nicht gefunden -> login nicht erfolgreich
			return false;
		}
		
		if(loginUser.getEmail().equals(compareUser.getEmail()) && loginUser.getPassword().equals(compareUser.getPassword()) ){
			return true;
		}
		
		
		return false;
	}
	
	
}//end class UserManager
