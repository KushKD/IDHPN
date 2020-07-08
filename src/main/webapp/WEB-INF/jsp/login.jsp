<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>


<body>


<section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div>
        <h1>HP Transport ID Card</h1>
      </div>
      <div class="login-box">
         <form  method="post"class="login-form"  action="${pageContext.request.contextPath}/login">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>SIGN IN</h3>
          <div class="form-group">
            <label class="control-label" for="username" >Username:</label><br>
             <input class="form-control" type="text" maxlength="10"  required autofocus name="username" id="username" class="form-control">
            </div>
             <div class="form-group">
               <label class="control-label" for="password" >Password:</label><br>
                 <input class="form-control" type="password" name="password"  maxLength="10" size="10" id="password" class="form-control">
                   </div>




           <div class="form-group">
             <input class="btn btn-primary btn-block" type="submit" name="submit" class="btn btn-info btn-md" value="submit">
               <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
             </div>

             <div class="form-group">
              <c:if test="${param.error != null}">
             <p>Login Failed</p>
             </c:if>

             </div>

        </form>
      </div>
    </section>







</body>
</html>