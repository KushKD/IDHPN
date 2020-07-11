function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

var formURL = getContextPath();


function getFormData() {

    var loginForm = {};

    loginForm.username = document.getElementById("username").value;
    loginForm.password = document.getElementById("password").value;
    //loginForm.captcha=grecaptcha.getResponse();

    alert(loginForm);
    return loginForm;

}

function submit1() {
    var data_ = JSON.stringify(getFormData());
    alert(formURL);
    $.ajax({
        type: "GET",
        url: formURL,
        contentType: 'application/json',
        dataType: "json",
        data: data_,
        success: function(data) {
            alert(data.message);
            console.log(data.message)

        },
        error: function(data) {
            alert(data);
            console.log(data)
        }

    });
}

//ajaxcall

function getroles() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/getRoles",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#roles'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].role_id + " >" + data.RESPONSE[i].role_name + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}


function getdistricts() {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/getDistricts",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#districts'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please District---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });


}

function loadBarriers(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/getBarriers",
        data: {
            "id": id
        },
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#barriers'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Select Barrier ---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].barrierId + " >" + data.RESPONSE[i].barrierName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });


}

function getData(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/getVehicleOwnerData",
        data: {
            "id": id
        },
        success: function(data) {
            var html = "";
            html += "<div class='row' >";

            html += "<div class='col-md-4 form-group' >";
            html += " <label class='control-label' style='font-weight: bold;'> Name:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleOwnerName + "</label> </div>";

            html += "<div class='col-md-5 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Mobile Number:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleOwnerMobileNumber + "</label> </div>";

            html += "<div class='col-md-3 form-group'  >";
            html += " <img class='img-responsive img-thumbnail' style='width:100px; height:100px;' src='" + window.location.protocol + "//" + window.location.hostname + "/downloadfile/" + data.RESPONSE.vehicleOwnerImageName + "'></img> </div>";
            html += "</div>";
            html += "<div class='row' >";
            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Pass Valid From:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.isValidFrom + "</label> </div>";


            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Pass Valid Upto:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.isValidUpto + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> ID Card Number  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.idCardNumber + "</label> </div>";


            html += "</div>";

            html += "<div class='row' >";
            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Aadhaar Number:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleOwnerAadhaarNumber + "</label> </div>";


            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> ID Created on:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.createdDate + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'>Driving Licence Number</label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleOwnerDrivingLicence + "</label> </div>";


            html += "</div>";

            html += "<div class='row' >";
            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Chassis Number:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleOwnerChassisNumber + "</label> </div>";


            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Engine Number:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleOwnerEngineNumber + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'>Vehicle Type: </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleType.vehicleName + "</label> </div>";


            html += "</div>";

            html += "</div>";

            html += "<div class='row' >";
            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> District:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.districtMaster.districtName + "</label> </div>";


            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'> Barrier:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.barriermaster.barrierName + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold;'>User Type: </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleUser.vehicleOwnerTypeName + "</label> </div>";


            html += "</div>";




            $('.modal-body').html(html);

            $('#empModal').modal('show');

        },
        error: function(data) {
            console.log(data)
        }

    });
}