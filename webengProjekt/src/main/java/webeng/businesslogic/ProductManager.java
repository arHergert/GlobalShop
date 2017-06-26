package webeng.businesslogic;

import java.util.ArrayList;
import java.util.List;

import webeng.data.DAOFactory;
import webeng.data.ProductDAO;
import webeng.data.DAOFactory.Backend;
import webeng.transferobjects.Product;
import webeng.transferobjects.User;

public class ProductManager {

	ProductDAO productDAO;
	
	public ProductManager() {
		productDAO = DAOFactory.getDAOFactory(Backend.H2).getProductDAO();
	}

	public void addProduct(int id, String name, float price, String category, String description){
		
		Product newProduct = new Product(id,name,price,category,description);
		productDAO.addProduct(newProduct);
	}
	
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}
	
	public void deleteProduct(int id){
		
		Product product = new Product(id, null, 0, null, null);
		productDAO.deleteProduct(product);
	}
	
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}
	
	public List<Product> findProducts(String product) {
		return productDAO.findProducts(product);
	}
	
	public List<Product> findProductsByCategory(String product, String category) {
		return productDAO.findProductsByCategory(product, category);
	}
	
	public List<String> getAllCategories() {
		List<String> mockupCategories = new ArrayList<>();
		mockupCategories.add("Schuhe");
		mockupCategories.add("Computer");
		return mockupCategories;
	}
}
