package com.triplexilaundry.domain.company;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
    @ManyToOne
    @JoinColumn(name = "department_id")
	public CompanyDepartment getDepartment() {
		return department;
	}

	public void setDepartment(CompanyDepartment department) {
		this.department = department;
	}
    
	
    @ManyToOne
    @JoinColumn(name = "manager_id")
	public Employee getReportTo() {
		return reportTo;
	}

	public void setReportTo(Employee reportTo) {
		this.reportTo = reportTo;
	}
    @ManyToOne
    @JoinColumn(name = "access_role_id")
	public AccessRole getAccessRole() {
		return accessRole;
	}

	public void setAccessRole(AccessRole accessRole) {
		this.accessRole = accessRole;
	}
    @ManyToOne
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessRole == null) ? 0 : accessRole.hashCode());
		result = prime * result
				+ ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result
				+ ((employeeRole == null) ? 0 : employeeRole.hashCode());
		result = prime * result + ((enable == null) ? 0 : enable.hashCode());
		result = prime * result
				+ ((expiredDate == null) ? 0 : expiredDate.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((reportTo == null) ? 0 : reportTo.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (accessRole == null) {
			if (other.accessRole != null)
				return false;
		} else if (!accessRole.equals(other.accessRole))
			return false;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (employeeRole == null) {
			if (other.employeeRole != null)
				return false;
		} else if (!employeeRole.equals(other.employeeRole))
			return false;
		if (enable == null) {
			if (other.enable != null)
				return false;
		} else if (!enable.equals(other.enable))
			return false;
		if (expiredDate == null) {
			if (other.expiredDate != null)
				return false;
		} else if (!expiredDate.equals(other.expiredDate))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (reportTo == null) {
			if (other.reportTo != null)
				return false;
		} else if (!reportTo.equals(other.reportTo))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}
