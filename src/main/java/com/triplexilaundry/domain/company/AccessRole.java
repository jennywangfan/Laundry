/**
* <p>Title: AccessRole.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 19, 2015
* @version 1.0
*/
package com.triplexilaundry.domain.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: AccessRole</p>
 * <p>Description: access role,according to spring security, must start with "ROLE_",  </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 19, 2015
 */
@Entity
@Table(name = "ACCESS_ROLE")
public class AccessRole implements Serializable{
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int roleId;
	private String authority;
	private String authorityDesc;
	@Id
	@GeneratedValue
	@Column(name = "role_id",unique = true, nullable = false)
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Column(name = "authority",length = 20)
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Column(name = "authority_desc",length = 50)
	public String getAuthorityDesc() {
		return authorityDesc;
	}
	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result
				+ ((authorityDesc == null) ? 0 : authorityDesc.hashCode());
		result = prime * result + roleId;
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
		AccessRole other = (AccessRole) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (authorityDesc == null) {
			if (other.authorityDesc != null)
				return false;
		} else if (!authorityDesc.equals(other.authorityDesc))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}
	

}
