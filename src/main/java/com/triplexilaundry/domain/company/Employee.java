package com.triplexilaundry.domain.company;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements UserDetails, Serializable{
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	//private String employeeId;
	private String username;
	private String password;
	private CompanyDepartment department;
	private EmployeeRole employeeRole;
	private Employee reportTo;
	private AccessRole accessRole;
	private Date startDate;
    private String fullName;
    private Date birthDay;
    private Character enable;
    private Date expiredDate;
    private Date createDate;
	
    @Id
	@Column(name = "user_name",unique = true, length = 20)
    public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
	public CompanyDepartment getDepartment() {
		return department;
	}

	public void setDepartment(CompanyDepartment department) {
		this.department = department;
	}
    
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
	public Employee getReportTo() {
		return reportTo;
	}

	public void setReportTo(Employee reportTo) {
		this.reportTo = reportTo;
	}
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_role_id")
	public AccessRole getAccessRole() {
		return accessRole;
	}

	public void setAccessRole(AccessRole accessRole) {
		this.accessRole = accessRole;
	}
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_role_id")
	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}
    
    public void setEmployeeRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
    
    public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    @Column(name = "fullname", length = 10)
	public String getFullName() {
		return fullName;
	}
    
    public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    
    @Temporal(TemporalType.DATE)
	public Date getBirthDay() {
		return birthDay;
	}
    
    public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
    
    @Column(name = "enabled", columnDefinition = "char(1)")
	public Boolean getEnable() {
    	if(enable == null)
    		return null;
       
		return enable =='Y' ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setEnable(Boolean enable) {
		if(enable == null)
			this.enable = null;
		else
			this.enable = (enable == Boolean.TRUE) ? 'Y' :'N';

	}
	@Temporal(TemporalType.DATE)
	@Column(name = "expire_date")
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	/* (non-Javadoc)
	* <p>Title: getAuthorities</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	*/
	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/* (non-Javadoc)
	* <p>Title: isAccountNonExpired</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	*/
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		if(this.expiredDate == null)
			return true;
		else 
            return this.expiredDate.after(new Date());
	}
	/* (non-Javadoc)
	* <p>Title: isAccountNonLocked</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	*/
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	/* (non-Javadoc)
	* <p>Title: isCredentialsNonExpired</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	*/
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	/* (non-Javadoc)
	* <p>Title: isEnabled</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	*/
	@Override
	@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.getEnable();
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
