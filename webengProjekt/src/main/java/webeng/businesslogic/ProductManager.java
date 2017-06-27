package webeng.businesslogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import webeng.data.DAOFactory;
import webeng.data.ProductDAO;
import webeng.data.DAOFactory.Backend;
import webeng.transferobjects.Product;
import webeng.transferobjects.User;

public class ProductManager {

	ProductDAO productDAO;
	
	List<Product> mockproducts;
	
	public ProductManager() {
		productDAO = DAOFactory.getDAOFactory(Backend.H2).getProductDAO();
		
	}

	public void addProduct(int id, String name, float price, String category, String description){
		
		Product newProduct = new Product(id,name,price,category,description);
		productDAO.addProduct(newProduct);
	}
	
	public List<Product> getProducts() {
		
		mockproducts = new ArrayList<>();
		mockproducts.add(new Product(1, "Yeezy Boost", 199.99F, "Schuhe", "Richtig geile Schuhe, digga!"));
		mockproducts.add(new Product(2, "Mundharmonika", 19.99F, "Instrumente", "Hochwertige Mundharmonika f�r talentierte Mundharmonikaristen"));
		mockproducts.add(new Product(3, "Taschent�cher", 0.99F, "Haushaltswaren", "Falls deine Freundin dich verlassen hat"));
		mockproducts.add(new Product(4, "Vans", 49.99F, "Schuhe", "Coole Schuhe"));
		mockproducts.add(new Product(5, "Blockfl�te", 34.99F, "Instrumente", "Nervig"));
		mockproducts.add(new Product(6, "Maultrommel", 44.99F, "Instrumente", "Macht lustige Ger�usche"));
		return mockproducts;
		
//		return productDAO.getProducts();
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
		mockupCategories.add("Kleidung");
		mockupCategories.add("Smartphones");
		mockupCategories.add("K�chenger�te");
		mockupCategories.add("Rasenm�her");
		mockupCategories.add("Blu-Rays");
		Collections.sort(mockupCategories);
		return mockupCategories;
	}
	
	
	/**
	 *
	 * @return Liste mit drei unterschiedlichen Produkten
	 */
	public List<Product> getRandomProducts(){
		List<Product> all = this.getProducts();
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
}
