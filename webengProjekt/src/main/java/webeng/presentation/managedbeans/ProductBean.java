package webeng.presentation.managedbeans;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "productBean")
@RequestScoped
public class ProductBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductBean() {
	       
	   }
	
}//end class ProductBean