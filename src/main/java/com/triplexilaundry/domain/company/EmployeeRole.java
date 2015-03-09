package com.triplexilaundry.domain.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
	

}
 