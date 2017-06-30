package webeng.data.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import webeng.data.ProductDAO;
import webeng.transferobjects.Product;

public class H2ProductDAO implements ProductDAO {

	private Connection connection;
	
	public H2ProductDAO() {
		connection = H2DAOFactory.getConnection();
	}
	
	@Override
	public void addProduct(Product product) {
		
		String statement = "INSERT INTO Product (ProductID, Name, Price, Category, Description) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setFloat(3, product.getPrice());
			stmt.setString(4, product.getCategory());
			stmt.setString(5, product.getCategory());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> getProducts() {
		return null;
	}

	@Override
	public void deleteProduct(Product product) {
		
	}

	@Override
	public void updateProduct(Product product) {
		
		String statement = "UPDATE Product SET Name = ?, Price = ?, Category = ?, Description = ? WHERE ProductID = " + product.getId();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setString(1, product.getName());
			stmt.setFloat(2, product.getPrice());
			stmt.setString(3, product.getCategory());
			stmt.setString(4, product.getDescription());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> findProducts(String product) {
		return null;
	}

	@Override
	public List<Product> findProductsByCategory(String product, String category) {
		return null;
	}

	@Override
	public List<String> getAllCategories() {
		return null;
	}

}
