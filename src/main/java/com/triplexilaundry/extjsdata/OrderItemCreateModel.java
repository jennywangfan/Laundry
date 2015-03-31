
package com.triplexilaundry.extjsdata;

import java.io.Serializable;

/**
 * <p>Title: OrderItemCreateModel</p>
 * <p>Description: data included in LaundryDataCreateModel after a new order created</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 29, 2015
 */
public class OrderItemCreateModel implements Serializable {

	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int itemId;
	private int amount;
	private double totalPrice;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
