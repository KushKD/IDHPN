<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Pop Up Closed -->
	<form:form method="POST" id="form" modelAttribute="showIdCardList" action="${pageContext.request.contextPath}/getIdCards" class="form-signin">
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
						<form:select path="district_id" name="district_id" class="form-control" id="districts" onchange="getBarriers(this.value)"></form:select>
						<form:errors  path="district_id"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="barrier_id">
					<div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
						<form:label path="barrier_id" for="roles"> Barrier</form:label>
						<form:select path="barrier_id" name="barrier_id" class="form-control" id="barriers" ></form:select>
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
	<c:if test="${not empty barrierid}">
		<input class="form-control col-md-6"  id="bid" type="hidden" value="${barrierid}"  />
	</c:if>
	<c:if test="${not empty districtid}">
		<input class="form-control col-md-6"  id="did" type="hidden" value="${districtid}"  />
	</c:if>
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
											<td>
												<c:out value="${loopCounter.count}"/>
											</td>
											<td>${vehicledata.vehicleOwnerName}</td>
											<td>${vehicledata.vehicleOwnerMobileNumber}</td>
											<td>${vehicledata.idCardNumber}</td>
											<td>${vehicledata.isValidFrom}</td>
											<td>${vehicledata.isValidUpto}</td>
											<td>
												<a class="button button-warning" href="${pageContext.request.contextPath}/generateId/${vehicledata.vehicleOwnerId}" target="blank_" >Generate ID</a>
											</td>
											<td>
												<a href="#" class="button button-success" onclick="getData('${vehicledata.vehicleOwnerId}')";>View Details</a>
											</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div></div>
				</div>
			</c:if>
		</main>
		<script type="text/javascript">

  function getBarriersOnLoad(){
  if(document.getElementById('did') != null && document.getElementById('did').value  != null ){
          loadBarriers(document.getElementById('did').value);
          }
      }

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
      getBarriersOnLoad();

  });
   </script>

<script type="text/javascript">
   $('#sampleTable').DataTable();

/*

   $( document ).ajaxComplete(function() {
      var did =  document.getElementById("did").value;
        if(did != undefined || did != null){
           alert(did);
            $('#districts select').first().find(':selected').removeAttr('selected');
             alert(did  + 2);

             $('#districts > option').each(function() {
                 alert(this.text + ' ' + this.value);
                //  alert($(this).val());
             });

}
                      alert(did  + 3);
   });

*/

   </script>