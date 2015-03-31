package com.triplexilaundry.domain.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* <p>Title: CompanyDepartment</p>
* <p>Description: department information </p>
* <p>All Right Reserved</p> 
* @author Fan Wang
* @date Mar 30, 2015
 */
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depDesc == null) ? 0 : depDesc.hashCode());
		result = prime * result + ((depName == null) ? 0 : depName.hashCode());
		result = prime * result + departmentId;
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
		CompanyDepartment other = (CompanyDepartment) obj;
		if (depDesc == null) {
			if (other.depDesc != null)
				return false;
		} else if (!depDesc.equals(other.depDesc))
			return false;
		if (depName == null) {
			if (other.depName != null)
				return false;
		} else if (!depName.equals(other.depName))
			return false;
		if (departmentId != other.departmentId)
			return false;
		return true;
	}
	
	
	
	
	

}
