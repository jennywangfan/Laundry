package com.triplexilaundry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class LoginController extends AbstractControllerService {
	
	
	private final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	
	
	  @RequestMapping(value = "/login",method = RequestMethod.GET)
	    public String login(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		    log.info("handling url /login");
	        return "login";
	    }
	
	  
	  @RequestMapping(value = "/admin",method = RequestMethod.GET)
	    public String loginSuccessful(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
	        return "admin";
	    }
	  
	  @RequestMapping(value = "/main",method = RequestMethod.GET)
	    public String mainSuccess(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		  String authority = getCurrentRole();
	        if("ROLE_ADMIN".equals(authority))
	        	return "admin";
	        else if("ROLE_CS".equals(authority))
	        	return "main";
	        else
			return "login";
	    }

	  @RequestMapping(method = RequestMethod.GET, value = "/username")
	    public @ResponseBody String usernameHandler() {
	    	
	        StringBuffer userName = new StringBuffer(getCurrentUserId());
	       
	        return "{\"username\":\"" + userName +"\""  + "}";
	    }

}
