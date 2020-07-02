<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<script>
document.getElementById("password_").maxLength = "10";
</script>
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
			<label  class="password"  for="password" >Password</label>
			<input id="password_"
				type="password" id="password" width="30px" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxLength="10" size="10"
				placeholder="Password" required>
		</p>

		<button type="submit">Sign in</button>
		 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>


	</form>
</body>
</html>