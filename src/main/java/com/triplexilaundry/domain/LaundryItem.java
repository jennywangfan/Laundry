package com.triplexilaundry.domain;


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
//@Entity
//@Table(name = "LAUNDRYITEM")
public enum LaundryItem{
	VEST(1,1,5),
	TSHIRT(1,2,9),
	SHORT(2,3,6),
	PANT(2,4,7),
	SUITS(3,5,4),
	OUTDOOR(3,6,9),
	UNKNOWN(10000,10000,0);
	
	LaundryItem(int categoryId,int itemId,double unitPrice){
		this.setCategoryId(categoryId);
		this.itemId = itemId;
		this.unitPrice = unitPrice;
	}
	/** long   serialVersionUID */
//	private static final long serialVersionUID = 1L;
	private int itemId;
	private int categoryId;
	//private String category;
	private double unitPrice;
	//private String itemName;
	
//	@Id
//    @GeneratedValue
//	@Column(name = "itemId")
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
//    @Column(name = "category",length = 20)
	public String getCategory() {
		switch(this.categoryId){
		case 1 :
			return "上装";
		case 2 :
			return "下装";
		case 3 :
			return "套装";
		default :
			return "未知";
		}
	}


//	@Column(name = "unit_price",precision= 10, scale = 2)
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double price) {
		this.unitPrice = price;
	}
	
//    @Column(name = "itemname",length = 20)
    public String getItemName() {
		switch(this){
		case VEST:
			return "马甲";
		case TSHIRT:
			return "T恤";
		case SHORT:
			return "短裤";
		case PANT:
			return "长裤";
		case SUITS:
			return "西装";
		case OUTDOOR:
			return "登山套装";
		default:
			return "未知";
		
		}
	}

	

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	public static LaundryItem getLaundryItembyId(int itemId){
		switch(itemId){
		case 1 : return VEST;
		case 2 : return TSHIRT;
		case 3 : return SHORT;
		case 4 : return PANT;
		case 5 : return SUITS;
		case 6 : return OUTDOOR;
		default : return UNKNOWN;
		}
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
