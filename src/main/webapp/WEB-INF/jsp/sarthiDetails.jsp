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



        <form:form method="POST" modelAttribute="saarthiDetails" action="${pageContext.request.contextPath}/saarthiServiceGet" class="form-signin">
            <h2 class="form-signin-heading">Driving Licence Service (Saarthi) </h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>


                        <div class="row">



                          <spring:bind path="dlNumber">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:input path="dlNumber" name="dlNumber" class="form-control" type="text" placeholder="Enter  Driving Licence Number" required="required"  />

                              <form:errors  path="dlNumber"></form:errors>
                            </div>
                             </spring:bind>




            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
               <c:remove var="successMessage" scope="session" />

               </div>
        </form:form>

        <c:if test="${not empty saarthi}">
         <div class="row">
                <div class="col-md-12">
                  <div class="tile">

                  <section class="invoice">
                          		  <div class="row invoice-info">
                                     <div class="col-8">

                                     <b>Driving Licence Number:</b> ${saarthi.dlLicNum} <br>
                                     <b>Name:</b> ${saarthi.dlLicName} <br>
                                     <b>Satus :</b> ${saarthi.dlLicStatus} <br>
                                     <b>Issuing Authority</b> ${saarthi.issuing_authority} <br>
                                     <b>DL Non Transferable Till:</b> ${saarthi.dlNonTransValidTill} <br>
                                     <b>DL Transfer Valid Till</b> ${saarthi.dlTransValidTill} <br>


                                     </div>
                                      </div>
                                      </section>


                  </div>
                  </div>
                  </div>





        	</c:if>

    </div>


    </main>



