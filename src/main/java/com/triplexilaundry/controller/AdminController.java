package com.triplexilaundry.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.extjsdata.ComboboxModel;
import com.triplexilaundry.extjsdata.DepartmentDataCreateModel;
import com.triplexilaundry.extjsdata.DepartmentDataReturnModel;
import com.triplexilaundry.extjsdata.EmployeeDataCreateModel;
import com.triplexilaundry.extjsdata.EmployeeDataReturnModel;
import com.triplexilaundry.extjsdata.ExtJSReturn;
import com.triplexilaundry.services.AdminService;
import com.triplexilaundry.services.DepartmentWrapperService;
import com.triplexilaundry.services.EmployeeWrapperService;

@Controller
public class AdminController{

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private EmployeeWrapperService employeeWrapperService;
	@Autowired
	private DepartmentWrapperService depWrapperService;
	
	
	@RequestMapping(method=RequestMethod.GET,value="/admin/userLoad.action")
	public @ResponseBody Map<String,? extends Object> getUserList() {
		log.info("load user list data");
		try{
			List<EmployeeDataReturnModel> results = adminService.findAllEmployees();
			log.info("success to load user list ");
			return ExtJSReturn.mapUserListOK(results);
		} catch (Exception e) {
			log.error("load user list controller exception");
			return ExtJSReturn.mapError("获取所有员工信息失败!");
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
			return ExtJSReturn.mapError("获取所有部门信息失败!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/admin/userIdValidate.action")
	public @ResponseBody String IdentifyUserByName(@RequestParam String userName){
		log.info("check userName whether exists");
		try{
			Employee e = adminService.checkUserName(userName);
			if(e == null){
				log.info("no such userName in database");
				return ExtJSReturn.simpleResult(true, userName + "可以使用!");
			}
			else{
				log.info("userName already existed");
				return ExtJSReturn.simpleResult(false,userName+ " 已经存在!");
			}
			
		}catch(Exception e){
		log.error("fail to check the username exists or not");
		return ExtJSReturn.simpleResult(false, "验证用户名失败!");
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
			return ExtJSReturn.mapError("获取部门列表失败!");
		}
		
	}
	
	@RequestMapping(method= RequestMethod.GET,value = "/getAllEmployeeRole.action")
	public @ResponseBody Map<String, ? extends Object> getAllEmployeeRoleForComboList(){
		log.info("get all employee role list for showing in user window");
		try{
			List<ComboboxModel> employeeRoleCombo = adminService.getEmployeeRoleForCombo();
			return ExtJSReturn.mapComboboxOK(employeeRoleCombo);
		}catch(Exception e){
			log.error("fail to get all employee role list for combobox");
			return ExtJSReturn.mapError("获取员工身份列表失败!");
		}
	}

	@RequestMapping(method= RequestMethod.GET,value = "/getAllAccessRole.action")
	public @ResponseBody Map<String, ? extends Object> getAllAccessRoleForComboList(){
		log.info("get all access role list for showing in user window");
		try{
			List<ComboboxModel> accessRoleCombo = adminService.getAccessRoleForCombo();
			return ExtJSReturn.mapComboboxOK(accessRoleCombo);
		}catch(Exception e){
			log.error("fail to get all access role list for combobox");
			return ExtJSReturn.mapError("获取权限列表失败!");
		}
	}
	
	@RequestMapping(method= RequestMethod.GET,value = "/getAllDepManagers.action")
	public @ResponseBody Map<String, ? extends Object> getAllManagersForComboList(@RequestParam String departmentId){
		log.info("get all managers for a department showing in user window");
		try{
			List<ComboboxModel> managerCombo = adminService.getDepManagersForCombo(Integer.valueOf(departmentId));
			return ExtJSReturn.mapComboboxOK(managerCombo);
		}catch(Exception e){
			log.error("fail to get all manager list for combobox");
			return ExtJSReturn.mapError("获取经理列表失败!");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/admin/userCreate.action")
	public @ResponseBody Map<String,? extends Object> createUser(@RequestBody EmployeeDataCreateModel data) {
		log.info(" add a new user ");
		try{
			 employeeWrapperService.createUser(data);
			 log.info("add user success");
			return ExtJSReturn.mapOKMessage("添加成功!");
		} catch (Exception e) {
			log.error("add user controller exception");
			return ExtJSReturn.mapError("添加用户过程出错!");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/admin/userDestory.action")
	public @ResponseBody Map<String, ? extends Object> destroyUser(@RequestParam String userName){
		log.info("delete user " +userName);
		try{
			adminService.destroyUser(userName);
			
				log.info("success to delete the user");
				return ExtJSReturn.mapOKMessage("删除成功!");
			}catch(Exception e){
				log.error("fail to delete the user");
				return ExtJSReturn.mapError("删除失败!");
			}
			
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="admin/depNameValidate.action")
	public @ResponseBody String validateDepName(@RequestParam String depName){
		log.info("validate whether dep name exists");
		try{
			boolean cd = adminService.checkDepName(depName);
			if(cd){
				log.info(depName + " exists");
				return ExtJSReturn.simpleResult(false, depName +"已经存在!");
			}
			else{
				log.info(depName +" not exists");
				return ExtJSReturn.simpleResult(true, depName + "可以使用!");
			}
		}catch(Exception e){
			log.error("fail to check dep name "+depName);
			return ExtJSReturn.simpleResult(false, "验证部门名称出错!");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/admin/depCreateOrEdit.action")
	public @ResponseBody Map<String,? extends Object> createDepartment(@RequestBody DepartmentDataCreateModel data) {
		log.info(" add a new user ");
		try{
			
			if(data.getDepartmentId() == null){
			 depWrapperService.createDepartment(data);
			 log.info("success to add department");
			 return ExtJSReturn.mapOKMessage("添加成功!");
			}else{
				depWrapperService.editDepartment(data);
				log.info("success to edit department");
				return ExtJSReturn.mapOKMessage("编辑成功!");
			}
			
		} catch (Exception e) {
			log.error("add department controller exception");
			return ExtJSReturn.mapError("操作部门过程出错!");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/admin/depEdit.action")
	public @ResponseBody Map<String,? extends Object> EditDepartment(@RequestBody DepartmentDataCreateModel data) {
		log.info(" add a new user ");
		try{
			depWrapperService.createDepartment(data);
			 log.info("add department success");
			return ExtJSReturn.mapOKMessage("添加成功!");
		} catch (Exception e) {
			log.error("add department controller exception");
			return ExtJSReturn.mapError("添加部门过程出错!");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/admin/depDestory.action")
	public @ResponseBody Map<String, ? extends Object> destroyDepartment(@RequestParam String depId){
		log.info("delete department " +depId);
		try{
			adminService.destroyDepartment(depId);
			
				log.info("success to delete the department");
				return ExtJSReturn.mapOKMessage("删除成功!");
			}catch(Exception e){
				log.error("fail to delete the department");
				return ExtJSReturn.mapError("删除失败!");
			}
			
		
	}
}
