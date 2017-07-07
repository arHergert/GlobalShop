package webeng.data.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String statement = "SELECT * FROM PRODUCT;";
		List<Product> list = new ArrayList<>();
		String name, cat, desc;
		int id;
		float price;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				id = rs.getInt("productid");
				price = rs.getFloat("price");
				cat = rs.getString("category");
				desc = rs.getString("description");
				list.add(new Product(id,name,price,cat,desc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
	}

	@Override
	public void deleteProduct(Product product) {
		String statement = "DELETE FROM Product WHERE ID = " + product.getId();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
		String statement;
		if(product.equals("")) {
			statement = "SELECT * FROM PRODUCT WHERE UPPER(Name) LIKE '" + product.toUpperCase() + "';";
		} else {
			statement = "SELECT * FROM PRODUCT WHERE UPPER(Name) LIKE '" + product.toUpperCase() + "%' OR Name LIKE '%" + product + "';";
		}
		
		List<Product> list = new ArrayList<>();
		String name, cat, desc;
		int id;
		float price;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				id = rs.getInt("productid");
				price = rs.getFloat("price");
				cat = rs.getString("category");
				desc = rs.getString("description");
				list.add(new Product(id,name,price,cat,desc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
		
	}

	@Override
	public List<Product> findProductsByCategory(String product, String category) {
		String statement;
		if(product.equals("")) {
			statement = "SELECT * FROM PRODUCT WHERE Name LIKE '" + product + "' OR Name LIKE '" + product + "';";
		} else {
			statement = "SELECT * FROM PRODUCT WHERE Name LIKE '" + product + "%' OR Name LIKE '%" + product + "';";
		}
		
		List<Product> list = new ArrayList<>();
		String name, cat, desc;
		int id;
		float price;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				id = rs.getInt("productid");
				price = rs.getFloat("price");
				cat = rs.getString("category");
				desc = rs.getString("description");
				list.add(new Product(id,name,price,cat,desc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
	}
	
	public List<Product> findProductsByCategory(String category) {
		
		String statement = "SELECT * FROM Product WHERE Category = '" + category + "';";
		List<Product> list = new ArrayList<>();
		String name, cat, desc;
		int id;
		float price;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				id = rs.getInt("productid");
				price = rs.getFloat("price");
				cat = rs.getString("category");
				desc = rs.getString("description");
				list.add(new Product(id,name,price,cat,desc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
		
	}

	@Override
	public List<String> getAllCategories() {
		
		String statement = "SELECT CATEGORY FROM PRODUCT;";
		List<String> list = new ArrayList<>();
		String cat = "";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				cat = rs.getString("category");
				if(!list.contains(cat)) {
					list.add(cat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
	}

	@Override
	public Product getProduct(int id) {
		String statement = "SELECT * FROM Product WHERE ProductID = " + id;
		String name="", cat="", desc="";
		int p_id=0;
		float price=0F;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			name = rs.getString("name");
			p_id = rs.getInt("productid");
			price = rs.getFloat("price");
			cat = rs.getString("category");
			desc = rs.getString("description");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Product(p_id,name,price,cat,desc);
	}

}
