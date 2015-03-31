package com.triplexilaundry.domain.company;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.triplexilaundry.domain.Address;
/**
 * 
* <p>Title: Customer</p>
* <p>Description: customer information, right now only userName and addressList are used
* others are reservered for future use </p>
* <p>All Right Reserved</p> 
* @author Fan Wang
* @date Mar 30, 2015
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	
    private String userName;
    private String password;
    private Date createDate;
    private Date lastLoginDate;
   // private CustomerProfile customerProfile;
    private List<Address> addressList;
    
    
    
	@Id
    @Column(name = "user_name", length = 35)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    @Column (name ="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
	public Date getCreateDate() {
		return createDate;
	}
  
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    @Temporal(TemporalType.DATE)
    @Column(name = "lastlogin_date")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}



}
