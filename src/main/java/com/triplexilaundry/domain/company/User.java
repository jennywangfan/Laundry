package com.triplexilaundry.domain.company;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements Serializable, UserDetails{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private Boolean enabled;
	public String getUserName() {
		return userName;
	}
	
	
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
	
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	/* (non-Javadoc)
	* <p>Title: getAuthorities</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	* <p>Title: getUsername</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	*/
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}


	/* (non-Javadoc)
	* <p>Title: isAccountNonExpired</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	*/
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	* <p>Title: isAccountNonLocked</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	*/
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	* <p>Title: isCredentialsNonExpired</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	*/
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	* <p>Title: isEnabled</p>
	* <p>Description: </p>
	* @return
	* @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	*/
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	

}
