/**
* <p>Title: DepartmentDataCreateModel.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 14, 2015
* @version 1.0
*/
package com.triplexilaundry.extjsdata;

import java.io.Serializable;

/**
 * <p>Title: DepartmentDataCreateModel</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 14, 2015
 */
public class DepartmentDataCreateModel implements Serializable {

	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String departmentId;
	private String departmentName;
	private String departmentDesc;
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	
}
