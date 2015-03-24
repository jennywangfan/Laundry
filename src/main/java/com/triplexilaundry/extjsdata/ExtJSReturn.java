/**
* <p>Title: ExtJSReturn.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 5, 2015
* @version 1.0
*/
package com.triplexilaundry.extjsdata;

/**
 * <p>Title: ExtJSReturn</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 5, 2015
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


/**
 * 
 * Json return model
 *
 */
@Component
public class ExtJSReturn {

	
	
	
	public static Map<String,Object> mapUserListOK(List<EmployeeDataReturnModel> results){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("results", results);
		modelMap.put("success", true);
		
		return modelMap;
	}
	
	
	public static Map<String,Object> mapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);
		return modelMap;
	} 
		
	public static String simpleResult(boolean success,String message){
		return "{success:"+success+",message:\'"+message+"\'}";
	}
    
	public static Map<String, ? extends Object> simpleMapResult(boolean success, String message){
		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", message);
		modelMap.put("success", success);
		return modelMap;
	}

	/**
	* <p>Title: mapDepartmentListOK</p>
	* <p>Description: </p>
	* @param results
	* @return
	*/
	public static Map<String, ? extends Object> mapDepartmentListOK(
			List<DepartmentDataReturnModel> results) {
		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("results", results);
		modelMap.put("success", true);
		
		return modelMap;
	}


	/**
	* <p>Title: mapComboboxOK</p>
	* <p>Description: </p>
	* @param departmentCombo
	* @return
	*/
	public static Map<String, ? extends Object> mapComboboxOK(
			List<ComboboxModel> results) {
		// TODO Auto-generated method stub
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("results", results);
		modelMap.put("success", true);
		modelMap.put("message", "成功获取");
		return modelMap;
	}


	/**
	* <p>Title: mapOKMessage</p>
	* <p>Description: </p>
	* @param string
	* @return
	*/
	public static Map<String,Object> mapOKMessage(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", true);
		return modelMap;
	}


	/**
	* <p>Title: mapOrderListOK</p>
	* <p>Description: </p>
	* @param extOrderList
	* @return
	*/
	public static Map<String, ? extends Object> mapOrderListOK(
			Map<String, Object> extOrderList) {
		Map<String,Object> modelMap = new HashMap<String,Object>(4);
		modelMap.putAll(extOrderList);
		modelMap.put("success", true);
		modelMap.put("message", "成功获取");
		return modelMap;
	}




	
	
}
