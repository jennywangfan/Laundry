/**
* <p>Title: Address.java</p>
* <p>Description: address for customers , every customer has many addresses
* like amazon shipping address, the attributes include phone number , name and address for shipping</p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 19, 2015
* @version 1.0
*/
package com.triplexilaundry.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class Address implements Serializable{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long addressId;
	private String fullName;
	private String phoneNumber;
	private String district;
	private String streetNum;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	@Id
	@GeneratedValue
	@Column(name = "address_id",nullable = false,unique = true)
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	@Column(name = "fullname",length = 10)
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name = "phonenumber", length = 20)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name ="district",length = 10)
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	@Column(name ="streetnumber",length = 15)
	public String getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	@Column (name = "street", length = 50)
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name = "city", length = 10)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column (name = "state", length = 5)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "zipcode",length = 6)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	

}
