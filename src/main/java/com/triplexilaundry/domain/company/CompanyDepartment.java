package com.triplexilaundry.domain.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
public class CompanyDepartment implements Serializable{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int departmentId;
	private String depName;
	private String depDesc;
	
	@Id
	@GeneratedValue
	@Column(name = "department_id")
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name = "department_name",length = 20)
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	@Column (name = "department_desc",length = 50)
	public String getDepDesc() {
		return depDesc;
	}
	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}
	
	
	
	
	

}
