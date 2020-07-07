<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>


<body>

 <c:if test="${param.error != null}">
<p>Login Failed</p>
</c:if>

        <div id="login" style="margin-top:20px;">
                   <div id="login-row" class="row justify-content-center align-items-center">
                   <div class="col-md-3"> </div>
                       <div id="login-column" class="col-md-6 ">
                           <div id="login-box" class="col-md-8 ">
                            <form  method="post" class="form"  action="${pageContext.request.contextPath}/login">
                                   <h3 class="text-center">Login</h3>
                                   <div class="form-group">
                                       <label for="username" >Username:</label><br>
                                       <input type="text" maxlength="10"  required autofocus name="username" id="username" class="form-control">
                                   </div>
                                   <div class="form-group">
                                       <label for="password" >Password:</label><br>
                                       <input type="password" name="password"  maxLength="10" size="10" id="password" class="form-control">
                                   </div>
                                   <div class="form-group">
                                        <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                                   </div>
                                   <div id="register-link" class="text-right">
                                       <a href="#" class="text-info">Register here</a>
                                   </div>
                               </form>
                           </div>
                       </div>
                         <div class="col-md-3"> </div>
                   </div>

           </div>


</body>
</html>