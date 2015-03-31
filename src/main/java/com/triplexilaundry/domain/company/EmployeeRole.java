package com.triplexilaundry.domain.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
* <p>Title: EmployeeRole</p>
* <p>Description: employee role , considering to refactor to  enum now</p>
* <p>All Right Reserved</p> 
* @author Fan Wang
* @date Mar 30, 2015
 */
@Entity
@Table(name = "EMPLOYEE_ROLE")

public class EmployeeRole implements Serializable{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;

	private int employeeRoleId;
	
	private String roleName;
	
	private String roleDesc;
	
    @Id
    @GeneratedValue
    @Column(name = "employee_role_id")
	public int getEmployeeRoleId() {
		return employeeRoleId;
	}

	public void setEmployeeRoleId(int employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}


    @Column(name ="employee_role_name", length = 15)
	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


    @Column(name = "employee_role_desc", length = 50)
	public String getRoleDesc() {
		return roleDesc;
	}



	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeRoleId;
		result = prime * result
				+ ((roleDesc == null) ? 0 : roleDesc.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
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
		EmployeeRole other = (EmployeeRole) obj;
		if (employeeRoleId != other.employeeRoleId)
			return false;
		if (roleDesc == null) {
			if (other.roleDesc != null)
				return false;
		} else if (!roleDesc.equals(other.roleDesc))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
	
	

}
 