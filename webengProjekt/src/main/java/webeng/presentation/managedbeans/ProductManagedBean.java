package webeng.presentation.managedbeans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	private String currentCategory;
	

	private List<Product> suggestions;

	public List<Product> getSuggestions() {
		return suggestions;
	}

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
		suggestions = new ArrayList<>();
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
	
	
	public String getCurrentCategory() {
		return currentCategory;
	}
	
	public void setCurrentCategory(String category) {
		this.currentCategory = category;
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
	
	public List<Product> getProductsByCategories() {
		return manager.findProductsByCategory(currentCategory);
	}
	
	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}
	
	public String findProducts(){
		
		return "produkte.xhtml";
	}
	
	public void listener(AjaxBehaviorEvent e) {
		
	}
	
	public void searchSuggestions(AjaxBehaviorEvent e) {
		suggestions = new ArrayList<Product>();
		
		for(Product p : manager.findProducts(search)) {
			suggestions.add(p);
		}
		
		Collections.sort(suggestions);
		
	}
	
	/**
	 *
	 * @return Liste mit drei unterschiedlichen Produkten
	 */
	public List<Product> getRandomProducts(){
		List<Product> all = manager.getProducts();
		List<Product> r = new ArrayList<Product>();
		Random rand = new Random();
		int i = 0;
		while(i<3) {
			Product temp = all.get(rand.nextInt(all.size()));
			if(!r.contains(temp)) {
				r.add(temp);
				i++;
			}
		}
		return r;
	}
	
}//end class ProductManagedBean