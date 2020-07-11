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

 <!-- Modal -->
   <div class="modal fade" id="empModal" role="dialog">
    <div class="modal-dialog  modal-lg">

     <!-- Modal content-->
     <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Vehicle Owner Details</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">

      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
     </div>
    </div>
   </div>

   <!-- Modal Pop Up Closed -->

        <form:form method="POST" modelAttribute="searchId" action="${pageContext.request.contextPath}/getIdCardsSearch" class="form-signin">
            <h2 class="form-signin-heading">Search ID Card </h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>


                        <div class="row">
                         <spring:bind path="vehicleNumber">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:input path="vehicleNumber" name="vehicleNumber" class="form-control" type="text" placeholder="Enter Vehicle Number" />
                              <form:errors  path="vehicleNumber"></form:errors>
                            </div>
                             </spring:bind>


                          <spring:bind path="mobileNumber">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:input path="mobileNumber" name="mobileNumber" class="form-control" type="text" placeholder="Enter Mobile Number" required="required"  />

                              <form:errors  path="mobileNumber"></form:errors>
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
   $('#sampleTable').DataTable();
   </script>