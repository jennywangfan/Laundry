package com.triplexilaundry.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* <p>Title: LaundryItem</p>
* <p>Description: laundry item is a item which used to describe the information of an item,
* like the price for laundry,the category(top, suits or dress), a constant hashmap is maintained
* to get all information from LAUNDRYITEM table which records should be inserted before we use 
* the system, also remember the information in table should be consist with data/category.json 
* which used in client side </p>
* <p>All Right Reserved</p> 
* @author Fan Wang
* @date Mar 30, 2015
 */
@Entity
@Table(name = "LAUNDRYITEM")
public class LaundryItem implements Serializable{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int itemId;
	private String category;
	private double unitPrice;
	private String itemName;
	
	@Id
    @GeneratedValue
	@Column(name = "itemId")
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
    @Column(name = "category",length = 20)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "unit_price",precision= 10, scale = 2)
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double price) {
		this.unitPrice = price;
	}
	
    @Column(name = "itemname",length = 20)
    public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

//	public String getChineseCategory(LaundryItem li){
//    	switch(li){
//    	case TOP:
//    		return "上衣";
//    	case SUITS:
//    		return "套装";
//    	case DRESS:
//    		return "裙子";
//    	case OUTDOOR:
//    		return "户外";
//    	case TROUSERS:
//    		return "裤子";
//    	case KNITWEAR:
//    		return "毛衣";
//    	case ACCESSORIES:
//    		return "小配件";
//		default:
//    		return "";
//    	}
//    }




}
