package webeng.transferobjects;

import java.io.Serializable;

public class User implements Serializable{

	
	private int id = 0;
	private String name;
	private String email;
	private String passwort;
	private String sessionid;
	
	public User(){};
	
	public User( int newId, String newName, String email, String newPasswort, String sessionid){
		this.id = newId;
		this.name = newName;
		this.email = email;
		this.passwort = newPasswort;
		this.sessionid = sessionid;
	}
	 

	//ID
	 public int getID() {
	 return id;
	 }
	 
	 public void setID(int id) {
		 this.id = id;
	 }
	 
	 
	 //Name
	 public String getName() {
		 return name;
	 }
	 
	 
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 
	 
	 //Email
	 public String getEmail(){
		 return email;
	 }
	 
	 
	 public void setEmail(String email){
		 this.email = email;
	 }
	 
	 
	 //Passwort
	 public String getPasswort(){
		 return passwort;
	 }
	 
	 public void setPasswort(String passwort){
		 this.passwort = passwort;
	 }
	 
	 
	 
	 //SessionId
	 public String getSessionID(){
		 return sessionid;
	 }
	 
	 public void setSessionID(String sessionid){
		 this.sessionid = sessionid;
	 }


	
}//end class User
