<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

<script type="text/javascript">
  $( document ).ready(function() {
      getroles();
  });
   </script>

<main class="app-content">

        <form:form method="POST" modelAttribute="registerUser" action="${pageContext.request.contextPath}/saveuser" class="form-signin">
            <h2 class="form-signin-heading">Create account</h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors  path="username"></form:errors>
                </div>
            </spring:bind>

             <spring:bind path="mobileNumber">
                            <div class="form-group  ${status.error ? 'has-error' : ''}">
                                <form:input type="text"  required="required" path="mobileNumber" maxlength="10" minlength="10"  class="form-control"  name="mobileNumber" placeholder="Mobile Number" ></form:input>
                                <form:errors  path="mobileNumber"></form:errors>
                            </div>
                        </spring:bind>

                          <spring:bind path="roleId">
                          <div class="form-group  ${status.error ? 'has-error' : ''}">
                           <form:label path="roleId" for="roles">Select Roles</form:label>
                            <form:select path="roleId" name="roleId" class="form-control" id="roles">

                              </form:select>
                              <form:errors  path="roleId"></form:errors>
                            </div>
                             </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
               <c:remove var="successMessage" scope="session" />
        </form:form>

    </div>
    </main>


