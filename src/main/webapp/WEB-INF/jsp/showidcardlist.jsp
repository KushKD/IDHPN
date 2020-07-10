<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
         <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>



<main class="app-content">

        <form:form method="POST" modelAttribute="showIdCardList" action="${pageContext.request.contextPath}/getIdCards" class="form-signin">
            <h2 class="form-signin-heading">Generate ID Card List</h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>


                        <div class="row">
                         <spring:bind path="district_id">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                           <form:label path="district_id" for="roles">District</form:label>
                            <form:select path="district_id" name="district_id" class="form-control" id="districts" onchange="getBarriers(this.value)">

                              </form:select>
                              <form:errors  path="district_id"></form:errors>
                            </div>
                             </spring:bind>


                          <spring:bind path="barrier_id">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                           <form:label path="barrier_id" for="roles"> Barrier</form:label>
                            <form:select path="barrier_id" name="barrier_id" class="form-control" id="barriers" >

                              </form:select>
                              <form:errors  path="barrier_id"></form:errors>
                            </div>
                             </spring:bind>

                             <spring:bind path="date">
                                                       <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                                        <form:label path="date" >Select Date</form:label>

                               <form:input class="form-control" path="date" id="demoDate" type="text" placeholder="Select Date" />
                                                           <form:errors  path="date"></form:errors>
                                                         </div>
                                                          </spring:bind>


            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
               <c:remove var="successMessage" scope="session" />

               </div>
        </form:form>

    </div>
    </main>



<script type="text/javascript">

  function getBarriers(data){
          loadBarriers(data);
      }

  $( document ).ready(function() {
 $('#demoDate').datepicker({
        	format: "dd/mm/yyyy",
        	autoclose: true,
        	todayHighlight: true
        });

      getdistricts();

  });
   </script>