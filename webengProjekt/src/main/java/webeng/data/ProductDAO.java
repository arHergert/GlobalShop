package webeng.data;


import java.util.List;

import webeng.transferobjects.Product;

public interface ProductDAO {

	public void addProduct(Product product);
	public List<Product> getProducts();
//	public Product getProduct(Product product);
	public Product getProduct(int id);
	public void deleteProduct(Product product);
	public void updateProduct(Product product);
	public List<Product> findProducts(String product);
	public List<Product> findProductsByCategory(String product, String category);
	public List<String> getAllCategories();
	
}//end interface ProductDAO
