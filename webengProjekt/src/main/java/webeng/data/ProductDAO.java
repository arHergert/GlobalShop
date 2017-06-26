package webeng.data;


import java.util.List;

import webeng.transferobjects.Product;

public interface ProductDAO {

	public void addProduct(Product product);
	public List<Product> getProducts();
	public void deleteProduct(Product product);
	public void updateProduct(Product product);
	public List<Product> findProducts(String product);
	public List<Product> findProductsByCategory(String product, String category);
	public List<String> getAllCategories();
	
}//end interface ProductDAO
