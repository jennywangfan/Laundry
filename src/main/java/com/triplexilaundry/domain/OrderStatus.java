package com.triplexilaundry.domain;

public enum OrderStatus {
	PENDINGPROCESS("等待处理"),
	INPROCESSING("处理中"),
	ONTHEWAY(""),
	CLEANING(""),
	ONROUTE(""),
	DELIVERED("");
	
	private String statusDes;
	
	OrderStatus(String dec){
		this.statusDes = dec;
	}

	public String getStatusDes() {
		return statusDes;
	}

	
	

}
