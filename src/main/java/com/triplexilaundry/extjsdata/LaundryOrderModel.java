
package com.triplexilaundry.extjsdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.triplexilaundry.domain.Address;
import com.triplexilaundry.domain.OrderItem;
import com.triplexilaundry.domain.OrderStatus;

/**
 * <p>Title: LaundryOrderModel</p>
 * <p>Description: data send to client when order list requested</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 18, 2015
 */
public class LaundryOrderModel implements Serializable{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long orderId;
	private String orderBy;
	private String csRep;
	private String pickedUpBy;
	private String deliveredBy;
	//private String phoneNum;
	private double price;
	private double actualIncome;
	private Address address;
	private Date preferedPickupStime;
	private Date preferedPickupEtime;
	private String orderStatus;
	private Date lastUpdateTime;
	private String lastUpdatedBy;
	private Date createDate;
	private String comments;
	private List<LaundryItemModel> orderItems;
	
	public LaundryOrderModel(){
	}
	
	/**
	* <p>Title: </p>
	* <p>Description: </p>
	* @param orderId
	* @param customer
	* @param csRep
	* @param pickedUpBy
	* @param deliveredBy
	* @param price
	* @param actualIncome
	* @param address
	* @param preferedPickupStime
	* @param preferedPickupEtime
	* @param orderStatus
	* @param lastUpdateTime
	* @param comments
	* @param laundryDetail
	*
	*/
	public LaundryOrderModel(long orderId, String customer, String csRep,
			String pickedUpBy, String deliveredBy, double price,
			double actualIncome, Address address, Date preferedPickupStime,
			Date preferedPickupEtime, OrderStatus orderStatus,
			Date lastUpdateTime,String lastUpdatedBy, String comments, List<OrderItem> laundryDetail) {
		super();
		this.orderId = orderId;
		this.orderBy = customer;
		this.csRep = csRep;
		this.pickedUpBy = pickedUpBy;
		this.deliveredBy = deliveredBy;
		this.price = price;
		this.actualIncome = actualIncome;
		//debug to set null
		
		this.address = address;
		this.preferedPickupStime = preferedPickupStime;
		this.preferedPickupEtime = preferedPickupEtime;
		this.orderStatus = OrderStatus.getChineseOrderStatus(orderStatus);
		this.lastUpdateTime = lastUpdateTime;
		this.lastUpdatedBy = lastUpdatedBy;
		this.comments = comments;
		if(laundryDetail != null){
			List<LaundryItemModel> itemList = new ArrayList<>();
			for(OrderItem oi : laundryDetail){
				LaundryItemModel lim = new LaundryItemModel();
				lim.setItemName(oi.getItem().getItemName());
				lim.setAmount(oi.getCount());
				lim.setPricePerItem(oi.getItem().getUnitPrice());
				//lim.setTotalPrice();
				itemList.add(lim);
			}
			this.orderItems = itemList;
		}
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCsRep() {
		return csRep;
	}
	public void setCsRep(String csRep) {
		this.csRep = csRep;
	}
	public String getPickedUpBy() {
		return pickedUpBy;
	}
	public void setPickedUpBy(String pickedUpBy) {
		this.pickedUpBy = pickedUpBy;
	}
	public String getDeliveredBy() {
		return deliveredBy;
	}
	public void setDeliveredBy(String deliveredBy) {
		this.deliveredBy = deliveredBy;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getActualIncome() {
		return actualIncome;
	}
	public void setActualIncome(double actualIncome) {
		this.actualIncome = actualIncome;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getPreferedPickupStime() {
		return preferedPickupStime;
	}
	public void setPreferedPickupStime(Date preferedPickupStime) {
		this.preferedPickupStime = preferedPickupStime;
	}
	public Date getPreferedPickupEtime() {
		return preferedPickupEtime;
	}
	public void setPreferedPickupEtime(Date preferedPickupEtime) {
		this.preferedPickupEtime = preferedPickupEtime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<LaundryItemModel> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<LaundryItemModel> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


}
