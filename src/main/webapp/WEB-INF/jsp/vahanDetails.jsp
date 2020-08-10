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



        <form:form method="POST" modelAttribute="vahanDetails" action="${pageContext.request.contextPath}/getDetailsVehicle" class="form-signin">
            <h2 class="form-signin-heading">Search Vehicle Details Via VAHAN Service </h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>


                        <div class="row">
                         <spring:bind path="serviceType">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:select path="serviceType" name="serviceType" class="form-control" type="text"  >
                            <form:option value="" label="--- Select ---"/>
                              <form:option value="vehicleDetails" label="Registration Details"/>
                              <form:option value="chassisDetails" label="Chassis Details"/>
                               <form:option value="engineDetails" label="Engine Details"/>
                            </form:select>
                              <form:errors  path="serviceType"></form:errors>
                            </div>
                             </spring:bind>


                          <spring:bind path="parameter">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:input path="parameter" name="parameter" class="form-control" type="text" placeholder="Enter  Number" required="required"  />

                              <form:errors  path="parameter"></form:errors>
                            </div>
                             </spring:bind>




            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
               <c:remove var="successMessage" scope="session" />

               </div>
        </form:form>

        <c:if test="${not empty vehicledata}">
         <div class="row">
                <div class="col-md-12">
                  <div class="tile">

                  <section class="invoice">
                          		  <div class="row invoice-info">
                                     <div class="col-8">
                                     <b>Registration Number:</b> ${vehicledata.rcRegistrationNo} <br>
                                     <b>Chassis Number:</b> ${vehicledata.rcChassisNo} <br>
                                     <b>Engine Number:</b> ${vehicledata.rcEngineNumber} <br>
                                     <b>RC Fit Upto:</b> ${vehicledata.rcFitUpto} <br>
                                     <b>RC Registered At:</b> ${vehicledata.rcRegisteredAt} <br>
                                      <b>RC Status:</b> ${vehicledata.rcStatus} <br>
                                       <b>RC Status As On At:</b> ${vehicledata.rcStatusAsOn} <br>
                                     </div>
                                      </div>
                                      </section>


                  </div>
                  </div>
                  </div>





        	</c:if>

    </div>


    </main>



