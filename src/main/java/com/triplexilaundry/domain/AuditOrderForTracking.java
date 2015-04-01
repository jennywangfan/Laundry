
package com.triplexilaundry.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.triplexilaundry.domain.company.Employee;

/**
 * <p>Title: AuditOrderForTracking</p>
 * <p>Description: order history for future use</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 31, 2015
 */
@Entity
@Table(name = "ORDERHISTORY")
public class AuditOrderForTracking implements Serializable{
	
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;

    private long orderHistoryId;
	private long orderId;
	//customer who initiated the order
	private String csRep;
	//carrier who get the order picked up
	private String pickedUpBy;
	//carrier who deliver this order
	private String deliveredBy;
	
	//order status
	private OrderStatus orderStatus;
	//last update time
	private Date lastUpdateTime;
	//last update employee
	private String lastUpdatedBy;
	
	//comments about the order
	private String comments;
	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 *
	 */
	public AuditOrderForTracking(LaundryOrder order) {
		this.orderId = order.getOrderId();
		Employee employee = order.getCsRep();
		if(employee != null)
		    this.csRep = employee.getFullName();
		employee = order.getPickedUpBy();
		if(employee != null)
			this.pickedUpBy = employee.getFullName();
		employee = order.getDeliveredBy();
		if(employee != null)
			this.deliveredBy = employee.getFullName();
		this.orderStatus = order.getOrderStatus();
		employee = order.getLastUpdatedBy();
		if(employee != null)
			this.lastUpdatedBy = employee.getFullName();
		this.comments = order.getComments();
		this.lastUpdateTime = order.getLastUpdateTime();
		
		
	}
	@Id
	@GeneratedValue
	@Column(name = "history_id")
	public long getOrderHistoryId() {
		return orderHistoryId;
	}
	public void setOrderHistoryId(long orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}
	@Column(name = "order_id")
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	@Column(name ="cs_rep",length = 20)
	public String getCsRep() {
		return csRep;
	}
	public void setCsRep(String csRep) {
		this.csRep = csRep;
	}
	@Column(name = "pickup_by",length = 20)
	public String getPickedUpBy() {
		return pickedUpBy;
	}
	public void setPickedUpBy(String pickedUpBy) {
		this.pickedUpBy = pickedUpBy;
	}
	@Column(name = "delivered_by")
	public String getDeliveredBy() {
		return deliveredBy;
	}
	public void setDeliveredBy(String deliveredBy) {
		this.deliveredBy = deliveredBy;
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
	@Column(name ="last_update_time")
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Column(name ="last_update_by",length = 20)
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	@Column(name = "comments",length = 500)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

		

}
