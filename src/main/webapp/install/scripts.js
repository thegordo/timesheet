/**
 * User: John Lawrence
 * Date: 1/2/11
 * Time: 8:59 AM
 */

function checkPassword() {
    var pass1 = document.getElementById("adminPassword").value;
    var pass2 = document.getElementById("adminPassword2").value;
    var verification = document.getElementById("passwordVerification");
    if (pass1 == pass2 && pass1.length > 0) {
        verification.innerHTML = "Passwords match";
        verification.style["color"] = "green";
    } else {
        verification.innerHTML = "Passwords don't match";
        verification.style["color"] = "red";
    }

}

function setupDBInputs() {
    if (document.getElementById("mysql").checked == 1) {
        document.getElementById("dbChoice").innerHTML = "Database Name:";
        document.getElementById("DBLocation").value = "paySystem";
    } else {
        document.getElementById("dbChoice").innerHTML = "Database Location:";
        document.getElementById("DBLocation").value = "/~/.PaySystem/paySystem";
    }
}

function changeButtonText() {
    if (document.getElementById("ldapLogin").checked == 1) {
        document.getElementById("submitButton").value = "Next";
    } else {
        document.getElementById("submitButton").value = "Install";
    }
}
