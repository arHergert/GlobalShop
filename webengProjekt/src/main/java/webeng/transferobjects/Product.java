package webeng.transferobjects;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {

	private int id;
	private String name;
	private float price;
	private String category;
	private String description;
	private int count;
	
	public Product(int id, String name, float price, String category, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;
		this.count = 1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(Product o) {
		return this.id-o.id;
	}
	
	
}
