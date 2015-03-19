package com.triplexilaundry.domain;

public enum OrderStatus {
	PENDINGPROCESS("PENDINGPROCESS"),
	INPROCESSING("INPROCESS"),
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
