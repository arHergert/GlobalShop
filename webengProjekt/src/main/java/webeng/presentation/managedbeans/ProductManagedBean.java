package webeng.presentation.managedbeans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import webeng.businesslogic.ProductManager;
import webeng.transferobjects.Product;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Product product;
	private ProductManager manager;
	private List<Product> list;
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public ProductManagedBean() {
		
	}
	
	
	@PostConstruct
	public void init() {
		manager = new ProductManager();
	}

	public BigDecimal floorPrice(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
	
	public Product getProduct() {
		return product;
	}
	
	public Product getProduct(int id) {
		return manager.getProduct(id);
	}
	
	public Product product(String s){
		return manager.getProduct(Integer.parseInt(s));
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<String> getCategories() {
		return manager.getAllCategories();
	}
	
	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}
	
	public List<Product> getProducts() {
		return  manager.getProducts();
	
	}
	
	
	public List<Product> getRandomProducts(){
		return manager.getRandomProducts();
	
	}
	public String findProducts(){
		
		return "produkte.xhtml";
	}
	
	public void listener(AjaxBehaviorEvent e) {
		
	}
	
	public List<String> autoComplete() {
		List<String> res = new ArrayList<String>();
		for(Product p : manager.findProducts(search)) {
			res.add(p.getName());
		}
		
		return res;
	}
	
}//end class ProductManagedBean