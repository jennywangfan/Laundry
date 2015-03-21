
package com.triplexilaundry.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>Title: OrderItem</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 19, 2015
 */
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem implements Serializable {
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long orderItemId;
	private LaundryItem item;
	private int count;
	private LaundryOrder belongto;
 
	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}
	
    @Enumerated(EnumType.STRING)
    @Column(name = "laundry_item",length = 20)
	public LaundryItem getItem() {
		return item;
	}
	public void setItem(LaundryItem item) {
		this.item = item;
	}
	
	@Column(name = "count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@ManyToOne
	@JoinColumn(name = "landry_order_id")
	public LaundryOrder getBelongto() {
		return belongto;
	}
	public void setBelongto(LaundryOrder belongto) {
		this.belongto = belongto;
	}
	
	
	

}
