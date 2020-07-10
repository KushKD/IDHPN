<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
         <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>



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
<c:if test="${not empty vehicledata}">
<div class="row">
               <div class="col-md-12">
                 <div class="tile">
                   <div class="tile-body">
                     <div class="table-responsive">
                       <table class="table table-hover table-bordered" id="sampleTable">
                         <thead>
                           <tr>
                             <th>S.No</th>
                             <th>Owner Name</th>
                             <th>Owner Mobile</th>
                             <th>ID Card Number</th>
                             <th>Valid From</th>
                             <th>Valid Upto</th>
                             <th>Generate Id Card</th>
                             <th>View Details</th>
                           </tr>
                         </thead>
                         <tbody>
                    <c:forEach items="${vehicledata}" var="vehicledata" varStatus="loopCounter">

                           <tr>
                             <td><c:out value="${loopCounter.count}"/></td>
                             <td>${vehicledata.vehicleOwnerName}</td>
                             <td>${vehicledata.vehicleOwnerMobileNumber}</td>
                             <td>${vehicledata.idCardNumber}</td>
                             <td>${vehicledata.isValidFrom}</td>
                             <td>${vehicledata.isValidUpto}</td>
                              <td><a class="button button-warning" href="${pageContext.request.contextPath}/generateId/${vehicledata.vehicleOwnerId}" target="blank_" >Generate ID</a></td>
                            <td><a href="#" class="button button-success" onclick="getData('${vehicledata.vehicleOwnerId}')";>View Details</a></td>

                           </tr>

    </c:forEach>
     </tbody>
                               </table>
                               </div>
                               </div>
                               <div>
                               </div>
                               </div>
</c:if>

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
   <script type="text/javascript">
   $('#sampleTable').DataTable(
   {
    dom: 'Bfrtip',
    buttons: [
                 'excel', 'pdf'
            ]
   });
   </script>