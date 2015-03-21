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

	public static String getChineseOrderStatus(OrderStatus status){
		switch(status){
		case PENDINGPROCESS:
			return "等待处理";
		case WAITINGFORPICKUP:
			return "等待取单";
		case CANCELED:
			return "已取消";
		case ONTHEWAY:
			return "快递在途";
		case CLEANING:
			return "洗涤中";
		case ONROUTE:
			return "派送在途";
		case DELIVERED:
			return "派送完成";
		default:
			return "无法识别状态";
		}
	}
	

}
