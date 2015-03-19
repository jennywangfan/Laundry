package com.triplexilaundry.domain;



public enum LaundryItem{
	TOP("TOP",3),SUITS("SUITS",15),DRESS("DRESS",10),
	OUTDOOR("OUTDOOR",20),TROUSERS("TROUSERS",8),KNITWEAR("KNITWEAR",15),
	ACCESSORIES("ACCESSORIES",5);
	
	private String category;
	private double price;
	
	LaundryItem(String category,double price){
		this.category = category;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

    public String getChineseCategory(LaundryItem li){
    	switch(li){
    	case TOP:
    		return "上衣";
    	case SUITS:
    		return "套装";
    	case DRESS:
    		return "裙子";
    	case OUTDOOR:
    		return "户外";
    	case TROUSERS:
    		return "裤子";
    	case KNITWEAR:
    		return "毛衣";
    	case ACCESSORIES:
    		return "小配件";
		default:
    		return "";
    	}
    }




}
