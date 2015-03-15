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
	
	private String depName;
	private String depDesc;
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepDesc() {
		return depDesc;
	}
	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}

}
