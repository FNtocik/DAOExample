var oldRow = null;
var paymentElement = document.getElementById("paymentId");
var languageElement = document.getElementById("languageId");
var summaryInput = document.getElementById("summary");
var isPayedCheckbox = document.getElementById("isPayed");
var table = document.getElementById("allPayments");
table.addEventListener('click', function (event) {
    if (oldRow != null)
        oldRow.style.background = "white";
    var row = event.target.parentNode;
    row.style.background = "blue";
    paymentElement.value = row.childNodes[0].textContent;
    get();
    oldRow = row;
});

getAll();

function add() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var cost = summaryInput.value;
    var isPayed = isPayedCheckbox.checked;
    var params = "cost=" + cost + "&isPayed=" + isPayed.toString();
    request.open("POST", "/secure/addPayment", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function edit() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var paymentId = paymentElement.value;
    var cost = summaryInput.value;
    var isPayed = isPayedCheckbox.checked;
    var params = "paymentId=" + paymentId + "&cost=" + cost + "&isPayed="
        + isPayed.toString();
    request.open("POST", "/secure/editPayment", true);
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
    var params = "paymentId=" + paymentElement.value;
    request.open("POST", "/secure/deletePayment", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        getAll();
    };
    request.send(params);
}

function get() {
    var request = new XMLHttpRequest();
    var params = "paymentId=" + paymentElement.value;
    request.open("POST", "/secure/getPayment", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        var reader = JSON.parse(this.responseText);
        paymentElement.value = reader["id"];
        summaryInput.value = reader["cost"];
        isPayedCheckbox.checked = reader["payed"];

    };
    request.send(params);
}

function getAll() {
    var requestToSubs = new XMLHttpRequest();
    requestToSubs.open("POST", "/secure/getAllPayment", true);
    requestToSubs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    requestToSubs.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        if (this.responseText.length == 0)
            return;
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var payments = JSON.parse(this.responseText);
        table.childNodes = null;
        languageElement.value = payments[0]["languageId"];
        for (var i = 0; i < payments.length; i++) {
            var currentPayment = JSON.parse(payments[i]);
            var tr = document.createElement("tr");
            var tdId = document.createElement("td");
            var textId = document.createTextNode(currentPayment["id"]);
            var tdCost = document.createElement("td");
            var textCost = document.createTextNode(currentPayment["cost"]);
            var tdPayed = document.createElement("td");
            var textPayed = document.createTextNode(currentPayment["payed"].toString());
            tdId.appendChild(textId);
            tdCost.appendChild(textCost);
            tdPayed.appendChild(textPayed);
            tr.appendChild(tdId);
            tr.appendChild(tdCost);
            tr.appendChild(tdPayed);
            table.appendChild(tr);
        }
    };
    requestToSubs.send();
}