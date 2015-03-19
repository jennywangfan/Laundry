/**
* <p>Title: AbstractControllerService.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 5, 2015
* @version 1.0
*/
package com.triplexilaundry.controller;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>Title: AbstractControllerService</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 5, 2015
 */
public class AbstractControllerService {
	
	
	// get the current login user name for showing on the page
	   public String getCurrentUserId() {
	        String username = null;
	        UserDetails user = null;
	        SecurityContext securityContext = SecurityContextHolder.getContext();

	        if ((securityContext != null) && (securityContext.getAuthentication() != null)) {
	            user = (UserDetails) securityContext.getAuthentication().getPrincipal();
	        }

	        if (user != null) {
	            username = user.getUsername();
	        }

	        return username;
	    }
	   
	    @SuppressWarnings("unchecked")
		public String getCurrentRole() {
	        String role = null;
	        List<GrantedAuthority> authorities = null;
	        SecurityContext securityContext = SecurityContextHolder.getContext();

	        if ((securityContext != null) && (securityContext.getAuthentication() != null)) {
	            authorities = (List<GrantedAuthority>) securityContext.getAuthentication()
	                                                     .getAuthorities();
	            role = authorities.get(0).getAuthority();
	        }

	        return role;
	    }

}
