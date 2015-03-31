
package com.triplexilaundry.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.EmployeeDao;
import com.triplexilaundry.domain.company.AccessRole;
import com.triplexilaundry.domain.company.Employee;
/**
 * 
* <p>Title: AccessUserDetailsService</p>
* <p>Description: class for implements UserDetailsService for authentication using spring security</p>
* <p>All Right Reserved</p> 
* @author Fan Wang
* @date Mar 30, 2015
 */
@Service("userDetailsService")

public class AccessUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeDao employeeDao;
   
	/* (non-Javadoc)
	* <p>Title: loadUserByUsername</p>
	* <p>Description: </p>
	* @param username
	* @return
	* @throws UsernameNotFoundException
	* @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	*/
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails user = null;
		Employee employee = employeeDao.findbyIdForAuthentication(username);		
		if(employee != null){
			user = new User(employee.getUsername(), employee.getPassword(), true, true, true, true, getAuthorities(employee.getAccessRole()));
		}
		return user;
	}

	/**
	* <p>Title: getAuthorities</p>
	* <p>Description: </p>
	* @param accessRole
	* @return
	*/
	private Collection<? extends GrantedAuthority> getAuthorities(
			AccessRole accessRole) {
		// TODO Auto-generated method stub
		Collection<SimpleGrantedAuthority> grantedAuth = new ArrayList<>();
		if(accessRole != null){
		SimpleGrantedAuthority simpleAuth = new SimpleGrantedAuthority(accessRole.getAuthority());
		grantedAuth.add(simpleAuth);
		}
		
		return grantedAuth;
	}

}
