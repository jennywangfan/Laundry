<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value='css/LoginDemo.css'/>" type="text/css" />
<title>Xixixi Login</title>
</head>

<body onload='document.f.j_username.focus();'>
<div id="container">

  <div id="header">
  	<!-- <img id="blueowl_logo" alt="logo" src="images/logo1.png"/> -->
   <!--  	<div id="title_line"><div id="title">汐汐洗</div></div>-->
  </div>
  
  <div id="BODY">
 	 <div id="blank2"></div>
 	 <div id="map">
 	 	<div id="LoginBox">
 	 		<div id="Up_dectration"></div>
 	 		<div id="middle_dectration">
 	 			<div id="blank2"></div>
 	 			<div id="center"></div>
				<form  name='f' action="<c:url value='j_spring_security_check'/>" method="post">
 	 			<div id="User_Info">
 	 					<div id="username_style">
							<label>用户名:</label><input id="username" type="text" name="j_username"/>
 	 					</div>
 	 					<div id="password_style">
 	 						<label>密码:</label><input id="Password" type="password" name="j_password"/>
 	 					</div>
						<div id="checkbox_style">
 	 						<input id="check" type="checkbox" name="_spring_security_remember_me"/>
							<label class="remember">记住用户名</label>
							<a id="Forgot" href="#">忘记密码? </a>
						</div>
						<div id="btn_area">
          					 <button type="submit" id="login" ></button>
         					 <button type="reset" id="reset"></button>        
        				</div>
        		</div>
 	 			</form>
 	 		</div>
 	 		<div id="bottom_dectration"></div>
 	 	</div>
 	 	<div id="error_info">
		  <c:choose>
		   <c:when test="${not empty error}">
		        <label id="lbl_error_info">
		         ${SPRING_SECURITY_LAST_EXCEPTION_MESSAGE}
		        <!-- Your login attempt was not successful, Please try again. -->
		        </label>
		   </c:when> 
		   <c:otherwise>
		   &nbsp;<br />&nbsp;
		   </c:otherwise>
		  </c:choose>
	    </div>
 	 </div>
 	 
  </div>
</div>

</body>

</html>
