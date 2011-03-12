function changePassword(id) {
    var element = document.getElementById("changePassword");
    var elementButton = document.getElementById("changePWButton");
    if (element.innerHTML == "") {
        element.innerHTML = "<button onclick='changePassword(null)'>Cancel</button><br />" +
                "<div class='login'><label for='pass1'>New Password:</label><input class='field' type='password' id='pass1'><br />" +
                "<label for='pass2'>Confirm Password:</label><input class='field' type='password' id='pass2'><br /></div>";
        elementButton.innerHTML = "Submit Password";
    } else {
        if (id != null) {
            doPasswordChange(id);
        } else {
            element.innerHTML = "";
            elementButton.innerHTML = "Change Password";
        }
    }
}

function doPasswordChange(id) {
    String.prototype.trim = function () {
        return this.replace(/^\s*/, "").replace(/\s*$/, "");
    }
    var element = document.getElementById("changePassword");
    var elementButton = document.getElementById("changePWButton");

    var pass1 = document.getElementById("pass1").value;
    var pass2 = document.getElementById("pass2").value;
    if (pass1 != pass2) {
        alert("Passwords do not match!");
        return;
    }
    if (pass1 == null || pass1 == "") {
        alert("Passwords must have a value!");
        return;
    }

    var request = new XMLHttpRequest();
    var url = "library/changePW.jsp";
    var parameters = "password=" + pass1 + "&id=" + id;
    request.open("POST", url, true);

    //Send the proper header information along with the request
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("Content-length", parameters.length);
    request.setRequestHeader("Connection", "close");

    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                document.body.style.cursor = "default";
                if (request.responseText.trim() != "success") {
                    alert("Unable to change password.");
                } else {
                    elementButton.innerHTML = "Change Password";
                    element.innerHTML = "";
                }
            } else {
                alert("Unable to change password.");
                document.body.style.cursor = "default";
            }
        } else {
            document.body.style.cursor = "wait";
        }
    };
    request.send(parameters);

}



function calculateTime() {
    var start = document.getElementById("startTime").value;
    var end = document.getElementById("endTime").value;
    var lunch = document.getElementById("lunchTime").value;
    var startArray = start.split(":");
    var endArray = end.split(":");
    var lunchArray = lunch.split(":");
    if (startArray.length != 2 || endArray.length != 2 || lunchArray.length != 2) {
        alert("Input is incorrect!");
        return;
    }
    var lunchHours = Math.round((parseFloat(lunchArray[0] == "" ? "0" : lunchArray[0]) + parseFloat(lunchArray[1] == "" ? "0" : lunchArray[1]) / 60) * 100) / 100;

    var startHours = Math.round((parseFloat(startArray[0] == "" ? "0" : startArray[0]) + parseFloat(startArray[1] == "" ? "0" : startArray[1]) / 60) * 100) / 100;
    if (document.getElementById("startPM").checked) {
        startHours += 12;
    }
    var endHours = Math.round((parseFloat(endArray[0] == "" ? "0" : endArray[0]) + parseFloat(endArray[1] == "" ? "0" : endArray[1]) / 60) * 100) / 100;
    if (document.getElementById("endPM").checked) {
        endHours += 12;
    }

    document.getElementById("hours").value = Math.round((endHours - startHours - lunchHours) * 100) / 100;
    document.getElementById("startTime").value = "";
    document.getElementById("endTime").value = "";
    document.getElementById("lunchTime").value = "";
}

function doTimePeriodGeneration() {
    var date = document.getElementById("date").value;
    var request = new XMLHttpRequest();
    var url = "library/getEmployeesForTimePeriod.php";
    var parameters = "date=" + date;
    request.open("POST", url, true);

    //Send the proper header information along with the request
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("Content-length", parameters.length);
    request.setRequestHeader("Connection", "close");

    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                document.body.style.cursor = "default";
                document.getElementById("employees").innerHTML = request.responseText;
            } else {
                alert("Unable to change password.");
                document.body.style.cursor = "default";
            }
        } else {
            document.body.style.cursor = "wait";
        }
    };
    request.send(parameters);
}

function getCalendarForEmployee() {
    var url = "library/ajaxGetTimePeriodCalendar.jsp";
    var parameters = "empID=" + document.getElementById("employeeID").value;
    var date = document.getElementById("fullDate").value;
    if (date != "") {
        parameters += "&date=" + date;
    }
    var request = new XMLHttpRequest();
    request.open("POST", url, true);

    //Send the proper header information along with the request
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("Content-length", parameters.length);
    request.setRequestHeader("Connection", "close");

    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                document.body.style.cursor = "default";
                document.getElementById("calendar").innerHTML = request.responseText;
            } else {
                alert("Unable to change password.");
                document.body.style.cursor = "default";
            }
        } else {
            document.body.style.cursor = "wait";
        }
    };
    request.send(parameters);
}

function approveTime(id, all) {
    if (!all) {
        document.location = "library/saveApproval.php?id=" + id + "&manager=false"
    } else {
        document.location = "library/saveApproval.php?empid=" + id + "&manager=false"
    }

}
