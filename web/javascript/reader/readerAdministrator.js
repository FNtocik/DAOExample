var readerElement = document.getElementById("readerId");
var languageElement = document.getElementById("languageId");
var loginInput = document.getElementById("login");
var passwordInput = document.getElementById("password");
var table = document.getElementById("tableBody");
var totalCount = -1;
var counter = 0;
var numberOfItems = 10;


setOnclick(table);
getAll();

function add() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var login = loginInput.value;
    var password = passwordInput.value;
    var languageId = languageElement.value;
    var params = "login=" + login + "&password="
        + password + "&languageId=" + languageId;
    request.open("POST", "/secure/addReader", true);
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
    var readerId = readerElement.value;
    var login = loginInput.value;
    var password = passwordInput.value;
    var languageId = languageElement.value;
    var params = "readerId=" + readerId + "&login=" + login + "&password="
        + password + "&languageId=" + languageId;
    request.open("POST", "/secure/editReader", true);
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
    var params = "readerId=" + readerElement.value;
    request.open("POST", "/secure/deleteReader", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function get(index) {
    var request = new XMLHttpRequest();
    var params = "readerIndex=" + index + "&counter=" + counter * numberOfItems + "&number=" + numberOfItems;
    request.open("POST", "/secure/getReader", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var reader = JSON.parse(this.responseText);
        readerElement.value = reader["id"];
        loginInput.value = reader["login"];
        passwordInput.value = reader["password"];

    };
    request.send(params);
}

function getAll() {
    var requestToSubs = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems;
    requestToSubs.open("POST", "/secure/getAllReader", true);
    requestToSubs.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    requestToSubs.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        if (this.responseText.length === 0) {
            return;
        }
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var response = JSON.parse(this.responseText);
        var readers = response["readers"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < readers.length; i++) {
            var currentReader = JSON.parse(readers[i]);
            var tr = document.createElement("tr");
            var tdLogin = document.createElement("td");
            var textLogin = document.createTextNode(currentReader["login"]);
            var tdPassword = document.createElement("td");
            var textPassword = document.createTextNode(currentReader["password"]);
            tdLogin.appendChild(textLogin);
            tdPassword.appendChild(textPassword);
            tr.appendChild(tdLogin);
            tr.appendChild(tdPassword);
            table.appendChild(tr);
        }
    };
    requestToSubs.send(params);
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