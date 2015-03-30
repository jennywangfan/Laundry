/**
* <p>Title: LaundryDataCreateModel.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 29, 2015
* @version 1.0
*/
package com.triplexilaundry.extjsdata;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: LaundryDataCreateModel</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 29, 2015
 */
public class LaundryDataCreateModel implements Serializable {

	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	List<OrderItemCreateModel> orderItems;
	private String orderBy;
	private String fullName;
	private String phoneNumber;
	private String state;
	private String city;
	private String district;
	private String street;
	private String streetNum;
	private String comments;
	private String preferedPickupSDate;
	private String preferedPickupSTime;
	private String preferedPickupEDate;
	private String preferedPickupETime;
	public List<OrderItemCreateModel> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemCreateModel> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPreferedPickupSDate() {
		return preferedPickupSDate;
	}
	public void setPreferedPickupSDate(String preferedPickupSDate) {
		this.preferedPickupSDate = preferedPickupSDate;
	}
	public String getPreferedPickupSTime() {
		return preferedPickupSTime;
	}
	public void setPreferedPickupSTime(String preferedPickupSTime) {
		this.preferedPickupSTime = preferedPickupSTime;
	}
	public String getPreferedPickupEDate() {
		return preferedPickupEDate;
	}
	public void setPreferedPickupEDate(String preferedPickupEDate) {
		this.preferedPickupEDate = preferedPickupEDate;
	}
	public String getPreferedPickupETime() {
		return preferedPickupETime;
	}
	public void setPreferedPickupETime(String preferedPickupETime) {
		this.preferedPickupETime = preferedPickupETime;
	}
	

}
