function getContextPath() {
       return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }

    var formURL = getContextPath();


 function getFormData(){

     var loginForm={};

     loginForm.username=document.getElementById("username").value;
     loginForm.password=document.getElementById("password").value;
     //loginForm.captcha=grecaptcha.getResponse();

      alert(loginForm);
      return loginForm;

     }

 function submit1() {
  var data_ = JSON.stringify(getFormData());
                           alert(formURL);
                           $.ajax({
                               type: "GET",
                               url:formURL,
                               contentType: 'application/json',
                               dataType: "json",
                               data: data_,
                               success: function (data) {
                                   alert(data.message);
                                   console.log(data.message)

                               },
                               error: function(data){
                                    alert(data);
                                    console.log(data)
                               }

                           });
                  }

//ajaxcall

                   function getroles() {

                                             $.ajax({
                                                 type: "GET",
                                                 url:formURL+"/ajax/getRoles",
                                                 success: function (data) {
                                                     console.log(data.RESPONSE)
                                                      var selectRole = $('#roles'); // the state select element
                                                             selectRole.find('option').remove();
                                                               selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
                                                             for (i = 0; i < data.RESPONSE.length; i++) {
                                                                 selectRole.append("<option value=" + data.RESPONSE[i].role_id + " >" + data.RESPONSE[i].role_name + "</option>")
                                                             }

                                                 },
                                                 error: function(data){
                                                      console.log(data)
                                                 }

                                             });
                                    }

