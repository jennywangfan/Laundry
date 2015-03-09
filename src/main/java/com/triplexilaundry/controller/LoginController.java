package com.triplexilaundry.controller;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.triplexilaundry.services.CustomerService;

@Controller

public class LoginController extends AbstractControllerService {
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="customerService")
	
	private CustomerService customerService;
	
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
	        return "success";
	    }

	  @RequestMapping(method = RequestMethod.GET, value = "/username")
	    public @ResponseBody String usernameHandler() {
	    	
	        StringBuffer userName = new StringBuffer(getCurrentUserId());
	       
	        return "{\"username\":\"" + userName +"\""  + "}";
	    }

}
