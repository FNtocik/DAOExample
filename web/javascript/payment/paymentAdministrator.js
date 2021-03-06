var paymentElement = document.getElementById("paymentId");
var summaryInput = document.getElementById("summary");
var isPayedCheckbox = document.getElementById("isPayed");
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
    var cost = summaryInput.value;
    var isPayed = isPayedCheckbox.checked;
    var params = "cost=" + cost + "&isPayed=" + isPayed.toString();
    request.open("POST", "/secure/addPayment", true);
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
    var paymentId = paymentElement.value;
    var cost = summaryInput.value;
    var isPayed = isPayedCheckbox.checked;
    var params = "paymentId=" + paymentId + "&cost=" + cost + "&isPayed="
        + isPayed.toString();
    request.open("POST", "/secure/editPayment", true);
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
    var params = "paymentId=" + paymentElement.value;
    request.open("POST", "/secure/deletePayment", true);
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
    var params = "paymentIndex=" + index + "&counter=" + counter * numberOfItems + "&number=" + numberOfItems
        + "&sortOrder=" + header;
    request.open("POST", "/secure/getPayment", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var reader = JSON.parse(this.responseText);
        paymentElement.value = reader["id"];
        summaryInput.value = reader["cost"];
        isPayedCheckbox.checked = reader["payed"];

    };
    request.send(params);
}

function getAll() {
    var request = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems + "&sortOrder=" + header;
    request.open("POST", "/secure/getAllPayment", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        if (this.responseText.length === 0) {
            return;
        }
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var response = JSON.parse(this.responseText);
        var payments = response["payments"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < payments.length; i++) {
            var currentPayment = JSON.parse(payments[i]);
            var tr = document.createElement("tr");
            var tdCost = document.createElement("td");
            var textCost = document.createTextNode(currentPayment["cost"]);
            var tdPayed = document.createElement("td");
            var textPayed = document.createTextNode(currentPayment["payed"].toString());
            tdCost.appendChild(textCost);
            tdPayed.appendChild(textPayed);
            tr.appendChild(tdCost);
            tr.appendChild(tdPayed);
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