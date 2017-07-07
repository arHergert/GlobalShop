package webeng.transferobjects;

import java.io.Serializable;

public class User implements Serializable{
	
	private int id = 0;
	private String name;
	private String email;
	private String password;
	private String sessionid;
	
	public User(){};
	
	public User( int id, String name, String email, String password, String sessionid){
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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
	 
	 
	 public String getPassword(){
		 return password;
	 }
	 
	 public void setPassword(String password){
		 this.password = password;
	 }
	 
	 
	 
	 //SessionId
	 public String getSessionID(){
		 return sessionid;
	 }
	 
	 public void setSessionID(String sessionid){
		 this.sessionid = sessionid;
	 }


	
}//end class User
