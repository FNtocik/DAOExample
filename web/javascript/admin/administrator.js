var adminElement = document.getElementById("administratorId");
var loginInput = document.getElementById("login");
var passwordInput = document.getElementById("password");
var table = document.getElementById("tableBody");
var totalCount = -1;
var counter = 0;
var numberOfItems = 10;


setOnclick(table, adminElement);
getAll();

function add() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var login = loginInput.value;
    var password = passwordInput.value;
    var params = "login=" + login + "&password="
        + password;
    request.open("POST", "/secure/addAdministrator", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function edit() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var administratorId = adminElement.value;
    var login = loginInput.value;
    var password = passwordInput.value;
    var params = "administratorId=" + administratorId + "&login=" + login + "&password=" + password;
    request.open("POST", "/secure/editAdministrator", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function del() {
    var request = new XMLHttpRequest();
    var params = "administratorId=" + adminElement.value;
    request.open("POST", "/secure/deleteAdministrator", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function get() {
    var request = new XMLHttpRequest();
    var params = "administratorId=" + adminElement.value;
    request.open("POST", "/secure/getAdministrator", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var administrator = JSON.parse(this.responseText);
        adminElement.value = administrator["id"];
        loginInput.value = administrator["login"];
        passwordInput.value = administrator["password"];

    };
    request.send(params);
}

function getAll() {
    var request = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems;
    request.open("POST", "/secure/getAllAdministrator", true);
    request.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        if (this.responseText.length === 0) {
            counter--;
            return;
        }
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var response = JSON.parse(this.responseText);
        var administrators = response["administrators"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < administrators.length; i++) {
            var currentAdministrator = JSON.parse(administrators[i]);
            var tr = document.createElement("tr");
            var tdId = document.createElement("td");
            tdId.setAttribute("hidden", "true");
            var textId = document.createTextNode(currentAdministrator["id"]);
            var tdLogin = document.createElement("td");
            var textLogin = document.createTextNode(currentAdministrator["login"]);
            var tdPassword = document.createElement("td");
            var textPassword = document.createTextNode(currentAdministrator["password"]);
            tdId.appendChild(textId);
            tdLogin.appendChild(textLogin);
            tdPassword.appendChild(textPassword);
            tr.appendChild(tdId);
            tr.appendChild(tdLogin);
            tr.appendChild(tdPassword);
            table.appendChild(tr);
        }
    };
    request.send(params);
}

function leftNav() {
    if (counter - 1 >= 0) {
        counter--;
        getAll();
    }
}

function rightNav() {
    if (counter + 1 < totalCount) {
        counter++;
        getAll();
    }
}