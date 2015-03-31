
package com.triplexilaundry.extjsdata;

/**
 * <p>Title: LaundryItemModel</p>
 * <p>Description: data include in laundryordermodel sent back to client when order list requested</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 18, 2015
 */
public class LaundryItemModel {
	
	private String itemName;
	private int amount;
	private double pricePerItem;
	private double totalPrice;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
