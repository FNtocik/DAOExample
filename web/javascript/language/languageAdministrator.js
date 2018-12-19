var languageElement = document.getElementById("languageId");
var signatureInput = document.getElementById("signatureInput");
var table = document.getElementById("tableBody");
var headers = document.getElementById("tableHead");
var totalCount = -1;
var counter = 0;
var numberOfItems = 10;


setOnclick(table);
setHeadersOnClick(headers);
getAll();

function add() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var signature = signatureInput.value;
    var languageId = languageElement.value;
    var params = "signature=" + signature + "&languageId=" + languageId;
    request.open("POST", "/secure/addLanguage", true);
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
    var languageId = languageElement.value;
    var signature = signatureInput.value;
    var params = "languageId=" + languageId + "&signature=" + signature;
    request.open("POST", "/secure/editLanguage", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function del() {
    var request = new XMLHttpRequest();
    var params = "languageId=" + languageElement.value;
    request.open("POST", "/secure/deleteLanguage", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function get(index) {
    var request = new XMLHttpRequest();
    var params = "languageIndex=" + index + "&counter=" + counter * numberOfItems + "&number=" + numberOfItems
        + "&sortOrder=" + header;
    request.open("POST", "/secure/getLanguage", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        var language = JSON.parse(this.responseText);
        languageElement.value = language["id"];
        signatureInput.value = language["signature"];

    };
    request.send(params);
}

function getAll() {
    var request = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems + "&sortOrder=" + header;
    request.open("POST", "/secure/getAllLanguage", true);
    request.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        if (this.responseText.length == 0) {
            counter--;
            return;
        }
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var response = JSON.parse(this.responseText);
        var languages = response["languages"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < languages.length; i++) {
            var currentLanguage = JSON.parse(languages[i]);
            var tr = document.createElement("tr");
            var tdSignature = document.createElement("td");
            var textSignature = document.createTextNode(currentLanguage["signature"]);
            tdSignature.appendChild(textSignature);
            tr.appendChild(tdSignature);
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