package webeng.presentation.managedbeans;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "hellojsfbean")
@RequestScoped
public class HelloJSF implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelloJSF() {
	      System.out.println("Hello JSF started!");   
	   }
	private String message = "Hello JSF!";
	
	public String update()
	{
		System.out.println("HelloJSF: update called");
		message = "Hello JSF! " + new Date().toString();
		return "";
	}
	public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
        
    }
}