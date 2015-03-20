package com.triplexilaundry.domain;

public enum OrderStatus {
	PENDINGPROCESS("PENDINGPROCESS"),
	WAITINGFORPICKUP("INPROCESS"),
	CANCELED("CANCELED"),
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
