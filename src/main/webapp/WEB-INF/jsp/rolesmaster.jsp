<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>



<main class="app-content">

        <form:form method="POST" modelAttribute="rolesForm" action="${pageContext.request.contextPath}/saveRole" class="form-signin">
            <h2 class="form-signin-heading">Create Roles</h2>
             <c:if test="${not empty successMessage}">
                    <div id="serverError" class="successMessage">${successMessage}</div>
                </c:if>
                 <br>
                <c:if test="${not empty serverError}">
                    <div id="serverError" class="plErroMessage">${serverError}</div>
                </c:if>
            <spring:bind path="roleName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="roleName" class="form-control" placeholder="Role Name"
                                autofocus="true"></form:input>
                    <form:errors  path="roleName"></form:errors>
                </div>
            </spring:bind>

             <spring:bind path="roleDescription">
                            <div class="form-group  ${status.error ? 'has-error' : ''}">
                                <form:input type="text" path="roleDescription"   class="form-control"  name="roleDescription" placeholder="Role Description" ></form:input>
                                <form:errors  path="roleDescription"></form:errors>
                            </div>
                        </spring:bind>


            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            <c:remove var="successMessage" scope="session" />
        </form:form>


    </div>
    </main>


