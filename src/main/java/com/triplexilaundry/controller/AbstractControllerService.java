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

}
