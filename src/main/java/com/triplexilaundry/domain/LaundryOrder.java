package com.triplexilaundry.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.triplexilaundry.domain.company.Customer;
import com.triplexilaundry.domain.company.Employee;

@Entity
@Table(name = "LAUNDRYORDER")

public class LaundryOrder implements Serializable{
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long orderId;
	private Customer customer;
	private Employee csRep;
	private Employee pickedUpBy;
	private Employee deliveredBy;
	private String phoneNum;
	private double price;
	private double actualIncome;
	private Address address;
	private Date preferedPickupStime;
	private Date preferedPickupEtime;
	private OrderStatus orderStatus;
	private Date lastUpdateTime;
	private Employee lastUpdatedBy;
	private String comments;
	private List<OrderItem> laundryDetail;
	
	@Id
	@GeneratedValue
	@Column(name = "order_id", nullable = false)
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@ManyToOne
	@JoinColumn(name= "cs_rep_id")
	public Employee getCsRep() {
		return csRep;
	}
	public void setCsRep(Employee csRep) {
		this.csRep = csRep;
	}
	@ManyToOne
	@JoinColumn(name = "pick_rep_id")
	public Employee getPickedUpBy() {
		return pickedUpBy;
	}
	public void setPickedUpBy(Employee pickedUpBy) {
		this.pickedUpBy = pickedUpBy;
	}
	@ManyToOne
	@JoinColumn(name = "delivered_rep_id")
	public Employee getDeliveredBy() {
		return deliveredBy;
	}
	public void setDeliveredBy(Employee deliveredBy) {
		this.deliveredBy = deliveredBy;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Column(name = "price",precision= 10, scale = 2)
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name = "actual_income",precision = 10, scale = 2)
	public double getActualIncome() {
		return actualIncome;
	}
	public void setActualIncome(double actualIncome) {
		this.actualIncome = actualIncome;
	}
	@ManyToOne
	@JoinColumn(name="address_id")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "prefer_start_ptime")
	public Date getPreferedPickupStime() {
		return preferedPickupStime;
	}
	public void setPreferedPickupStime(Date preferedPickupStime) {
		this.preferedPickupStime = preferedPickupStime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "perfer_end_ptime")
	public Date getPreferedPickupEtime() {
		return preferedPickupEtime;
	}
	public void setPreferedPickupEtime(Date preferedPickupEtime) {
		this.preferedPickupEtime = preferedPickupEtime;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdated_date")
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Column(name = "comments", length = 500)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@OneToMany(mappedBy = "belongto")
	
	public List<OrderItem> getLaundryDetail() {
		return laundryDetail;
	}
	public void setLaundryDetail(List<OrderItem> laundryDetail) {
		this.laundryDetail = laundryDetail;
	}
	@ManyToOne
	@JoinColumn(name = "lastupdatedby")
	public Employee getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Employee lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}
