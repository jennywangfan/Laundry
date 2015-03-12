package com.triplexilaundry.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.extjsdata.ComboboxModel;
import com.triplexilaundry.extjsdata.DepartmentDataReturnModel;
import com.triplexilaundry.extjsdata.EmployeeDataReturnModel;
import com.triplexilaundry.extjsdata.ExtJSReturn;
import com.triplexilaundry.services.AdminService;

@Controller
public class AdminController{

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	//@Autowired
	//private UserWrapperService userWrapperService;
	
	
	@RequestMapping(method=RequestMethod.GET,value="/admin/userLoad.action")
	public @ResponseBody Map<String,? extends Object> getUserList() {
		log.info("load user list data");
		try{
			List<EmployeeDataReturnModel> results = adminService.findAllEmployees();
			log.info("success to load user list ");
			return ExtJSReturn.mapUserListOK(results);
		} catch (Exception e) {
			log.error("load user list controller exception");
			return ExtJSReturn.mapError("Error when retrieving user data from database.");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/admin/departmentLoad.action")
	public @ResponseBody Map<String, ? extends Object> getDepartmentList(){
		log.info("load department list data");
		try{
			List<DepartmentDataReturnModel> results = adminService.findAllDepartments();
			log.info("success to load department list");
			return ExtJSReturn.mapDepartmentListOK(results);
			
		}catch(Exception e){
			log.error("load department list controller exception");
			return ExtJSReturn.mapError("Error when retrieving department data from database.");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/admin/userIdValidate.action")
	public @ResponseBody String IdentifyUserByName(@RequestParam String userName){
		log.info("check userName whether exists");
		try{
			Employee e = adminService.checkUserName(userName);
			if(e == null){
				log.info("no such userName in database");
				return ExtJSReturn.simpleResult(true, userName + "can be created");
			}
			else{
				log.info("userName already existed");
				return ExtJSReturn.simpleResult(false,userName+ " already existed");
			}
			
		}catch(Exception e){
		log.error("fail to check the username exists or not");
		return ExtJSReturn.simpleResult(false, "error when checking userName");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getAllDepartment.action")
	public @ResponseBody Map<String,? extends Object> getAllDepartmentForComboList(){
		log.info("get all department list for showing in user window");
		try{
			List<ComboboxModel> departmentCombo = adminService.getDepartmentForCombo();
			
			return ExtJSReturn.mapComboboxOK(departmentCombo);
			
			
		}catch(Exception e){
			log.error("fail to get department list for combobox ");
			return ExtJSReturn.mapError("fail to get department list for combobox");
		}
		
	}

	
}
