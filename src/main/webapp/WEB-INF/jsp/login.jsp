<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>

<body>

 <c:if test="${param.error != null}">
<p>Login Failed</p>
</c:if>


	<form  method="post"  action="${pageContext.request.contextPath}/login">
		<h2 >Please sign in</h2>
		<p>
			<label class="username" for="username" >Username</label> <input
				type="text" id="username" maxlength="10" size="10" name="username"
				placeholder="Username" required autofocus>
		</p>
		<p>
			<label class="password" maxlength="10" size="10" for="password" >Password</label> <input
				type="password" id="password" name="password"
				placeholder="Password" required>
		</p>

		<button type="submit">Sign in</button>
		 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>


	</form>
</body>
</html>