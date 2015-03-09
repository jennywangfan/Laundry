package com.triplexilaundry.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LAUNDRYCATEGORY")
public class LaundryItem implements Serializable {
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int laundryItemId;
	private String itemName;
	private String itemCategory;
	private double price;
	

    @Id
    @GeneratedValue
    @Column(name = "laundry_item_id")
	public int getLaundryItemId() {
		return laundryItemId;
	}

	public void setLaundryItemId(int laundryItemId) {
		this.laundryItemId = laundryItemId;
	}

	@Column(name = "item_name", length = 10)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    @Column(name = "category",length = 10)
	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	@Column(name = "price",precision= 10, scale = 2)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}



}
