/**
* <p>Title: ComboboxModel.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 11, 2015
* @version 1.0
*/
package com.triplexilaundry.extjsdata;

/**
 * <p>Title: ComboboxModel</p>
 * <p>Description: combox box show department ,accessrole ,employeerole, used for admin page
 * attribute is id for those class, name is name for those class</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 11, 2015
 */
public class ComboboxModel {
	private String name;
	private String attribute;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	

}
