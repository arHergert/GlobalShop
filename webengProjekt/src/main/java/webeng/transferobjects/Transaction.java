package webeng.transferobjects;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	private int transactionId;
	private int userId;
	private int productId;
	private int quantity;
	private float subtotal;
	private Date date;

	public Transaction(int transactionId, int userId, int productId, int quantity, float subtotal, Date date) {
		this.transactionId = transactionId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.date = date;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
