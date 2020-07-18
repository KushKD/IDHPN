<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
history.pushState(null, null, 'no-back-button');
window.addEventListener('popstate', function(event) {
  history.pushState(null, null, 'no-back-button');
});
</script>



<main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-dashboard"></i> Dashboard</h1>
          <p>Himachal Police Transport ID Card Panel</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-6 col-lg-3">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-id-badge fa-3x"></i>
            <div class="info">
            <a href="${pageContext.request.contextPath}/showIdCards" style=" text-decoration: none !important;">
                          <h4>View ID Card List</h4></a>

            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small info coloured-icon"><i class="icon fa fa-search fa-3x"></i>
            <div class="info">
             <a href="${pageContext.request.contextPath}/searchId" style=" text-decoration: none !important;">
                           <h4>Search ID Card</h4></a>

            </div>
          </div>
        </div>
         <sec:authorize access="hasAuthority('ADMIN')">
           <div class="col-md-6 col-lg-3">
                  <div class="widget-small warning coloured-icon"><i class="icon fa fa-user fa-3x"></i>
                    <div class="info">
                     <a href="${pageContext.request.contextPath}/createUser" style=" text-decoration: none !important;">
                                   <h4>Create User</h4></a>

                    </div>
                  </div>
                </div>
                </sec:authorize>
 <sec:authorize access="hasAuthority('ADMIN')">
   <div class="col-md-6 col-lg-3">
          <div class="widget-small danger coloured-icon"><i class="icon fa fa-file-excel-o fa-3x"></i>
            <div class="info">
             <a href="${pageContext.request.contextPath}/generateReport" style=" text-decoration: none !important;">
                           <h4>Generate Reports</h4></a>

            </div>
          </div>
        </div>
 </sec:authorize>


      </div>



      </div>
    </main>