
<%@ page language="java" import="java.util.*" contentType="text/html; charset=GB2312" %> 
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    <table>
         <tr>
             <td><label for="username">username:</label></td>
             <td><input type="text" id="username" name="j_username" 
                    value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/></td>
        </tr>
         <tr>
             <td><label for="password">password:</label></td>
             <td><input type="password" id="password" name="j_password" value=""/></td>
         </tr>
         <tr><td></td>
             <td><input type="checkbox" name="_spring_security_remember_me">两周内记住我</td>
         </tr>
         <tr><td colspan="2"><input type="submit" value="提交"/>
         <input type="reset" value="重置"/></td></tr>
     </table>
 </form>
</html>
