function getContextPath() {
       return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }

    var formURL = getContextPath()+"/verifylogin";


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