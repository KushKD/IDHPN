<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>HP Police Transport ID</title>
      <link rel='stylesheet'   href='${pageContext.request.contextPath}/resources/css/style.css' type='text/css' media='all' />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" >
      <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css" > -->
      <!-- Add icon library -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

      <style> .row{margin:0px} </style>
   </head>

   <body >
         <!--Top Bar Starts-->
         <section class="container-fluid top_bar"  >
            <div class="row">
               <div class="col-xl-7 top_bar_col1 ">&nbsp;</div>
               <div class="col-xl-5 top_bar_col2" >
                  <div class="flex_style">
                     <ul class="accessibility">
                        <li > <a  href="#">A<sub>-</sub></a></li>
                        <li> <a  href="#">A</a></li>
                        <li > <a   href="#">A<sub>+</sub></a></li>
                     </ul>
                     <div  class="login" ><a class="text_decorate" href="${pageContext.request.contextPath}/login">Login</a>  </div>
                  </div>
               </div>
            </div>
         </section>
         <!-- Top Bar Ends-->
         <!-- Navigation and Header-->
         <div class="header_logo_megamenu back_pg" >
            <div class="row ">

               <div class="col-xl-1 col-lg-1 col-md-1 col-sm-1 col-1" style="margin-left: 50px;"> <img src="${pageContext.request.contextPath}/resources/images/hp_police_logo.png"  class="logo_hp"   alt="Himachal Pradesh Police"></div>
               <div class="col-xl-4 col-lg-4 col-md-10 col-sm-9 col-9 padding_">
                  <div>
                     <p class="text-left text_logo_one">HIMACHAL PRADESH POLICE</p>
                  </div>
                  <div>
                     <p class="text_logo_two">GOVERNMENT OF HIMACHAL PRADESH </p>
                  </div>
               </div>
               <div class="col-xl-6 col-lg-6 col-md-12">
                  <nav class="navbar_" style="margin-top: 30px;">
                     <ul >
                        <li><a href="#"> <img src="${pageContext.request.contextPath}/resources/images/vector/house.svg" class="icon_mega_menu"><span class="parent-menga-menu">Home</span></a></li>
                        <li>
                           <a href="#"> <img src="${pageContext.request.contextPath}/resources/images/vector/information.svg" class="icon_mega_menu"><span class="parent-menga-menu">Information</span></a>
                           <div class="mega-menu">
                              <!-- <div class="header">
                                 <h2>Mega Menu</h2>
                                 </div>  -->
                              <div class="row">
                                 <!-- First Row-->
                                 <div  class="col-lg-4" style="float:left">
                                    <div><span class="mega-menu-header">Information </span></div>

                                 </div>
                                 <!-- Third Row Ends-->
                              </div>
                           </div>
                        </li>
                        <li>
                           <a href="#"><img src="${pageContext.request.contextPath}/resources/images/vector/schemes.svg" class="icon_mega_menu"><span class="parent-menga-menu">Schemes</span></a>
                           <div class="mega-menu-single">
                              <!-- <div class="header">
                                 <h2>Mega Menu</h2>
                                 </div>  -->
                              <div class="row">
                                 <!-- First Row-->
                                 <div class="col-lg-12">
                                    <div><span class="mega-menu-header">Schemes </span></div>

                                 </div>
                                 <!-- First Row Ends-->
                              </div>
                           </div>
                        </li>
                        <li>
                           <a href="#"><img src="${pageContext.request.contextPath}/resources/images/vector/act_and_rules.svg" class="icon_mega_menu"><span class="parent-menga-menu">Acts & Rules</span></a>
                           <div class="mega-menu">
                              <!-- <div class="header">
                                 <h2>Mega Menu</h2>
                                 </div>  -->
                              <div class="row">
                                 <!-- First Row-->
                                 <div class="col-lg-4">
                                    <div><span class="mega-menu-header">Information </span></div>

                                 </div>
                                 <!-- First Row Ends-->
                                 <!-- Extra Row Starts-->

                           </div>
                        </li>
                        <li>
                           <a href="#"><img src="${pageContext.request.contextPath}/resources/images/vector/landbank.svg" class="icon_mega_menu"><span class="parent-menga-menu">Land Bank</span></a>
                           <div class="mega-menu-double">
                              <!-- <div class="header">
                                 <h2>Mega Menu</h2>
                                 </div>  -->
                              <div class="row">
                                 <!-- First Row-->
                                 <div class="col-lg-6">
                                    <div><span class="mega-menu-header">Government Land Bank </span></div>

                                 </div>
                                 <!-- First Row Ends-->
                                 <!-- Extra Row Starts-->
                                 <div class="col-lg-6">
                                    <div><span class="mega-menu-header">Private Land Bank</span></div>

                                 </div>
                                 <!--Extra Row Ends-->
                              </div>
                           </div>
                        </li>
                     </ul>
                  </nav>
               </div>
            </div>
         </div>
         <!-- Section More Options-->
         <section class="secondary_menu_options" style="width: auto; height:10px"  >
         </section>
         <!-- Section More Options-->
         </body>