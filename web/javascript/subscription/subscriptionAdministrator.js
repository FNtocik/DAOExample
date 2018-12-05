var subscriptionElement = document.getElementById("subscriptionId");
var readerSelect = document.getElementById("readerSelect");
var publicationSelect = document.getElementById("publicationSelect");
var paymentSelect = document.getElementById("paymentSelect");
var dateStartInput = document.getElementById("startDateInput");
var dateEndInput = document.getElementById("endDateInput");
var table = document.getElementById("tableBody");
var totalCount = -1;
var counter = 0;
var numberOfItems = 10;


setOnclick(table, subscriptionElement);
getAll();
fillReaderSelect();
fillPaymentSelect();
fillPublicationSelect();

function add() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var readerId = readerSelect.options[readerSelect.selectedIndex].value;
    var publicationId =
        publicationSelect.options[publicationSelect.selectedIndex].value;
    var paymentId =
        paymentSelect.options[paymentSelect.selectedIndex].value;
    var startDate = dateStartInput.value;
    var endDate = dateEndInput.value;
    var params = "readerId=" + readerId + "&publicationId=" + publicationId
        + "&paymentId=" + paymentId + "&endDate=" + endDate + "&startDate=" + startDate;
    request.open("POST", "secure/addSubscription", true);
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
    var subscriptionId = subscriptionElement.value;
    var readerId = readerSelect.options[readerSelect.selectedIndex].value;
    var publicationId =
        publicationSelect.options[publicationSelect.selectedIndex].value;
    var paymentId =
        paymentSelect.options[paymentSelect.selectedIndex].value;
    var startDate = dateStartInput.value;
    var endDate = dateEndInput.value;
    var params = "subscriptionId=" + subscriptionId + "&readerId=" +
        readerId + "&publicationId=" + publicationId + "&paymentId=" +
        paymentId + "&endDate=" + endDate +
        "&startDate=" + startDate;
    request.open("POST", "secure/editSubscription", true);
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
    var params = "subscriptionId=" + subscriptionElement.value;
    request.open("POST", "secure/deleteSubscription", true);
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
    var params = "subscriptionId=" + subscriptionElement.value;
    request.open("POST", "secure/getSubscription", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var subscription = JSON.parse(this.responseText);
        var reader = JSON.parse(subscription["reader"]);
        var publication = JSON.parse(subscription["publication"]);
        var payment = JSON.parse(subscription["payment"]);
        subscriptionElement.value = subscription["id"];
        searchValueInSelect(readerSelect.options, reader["id"]);
        searchValueInSelect(paymentSelect.options, payment["id"]);
        searchValueInSelect(publicationSelect.options, publication["id"]);
        dateStartInput.value = subscription["startDate"];
        dateEndInput.value = subscription["endDate"];

    };
    request.send(params);
}

function getAll() {
    var request = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems;
    request.open("POST", "/secure/getAllSubscription", true);
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
        var subscriptions = response["subscriptions"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < subscriptions.length; i++) {
            var subscription = JSON.parse(subscriptions[i]);
            var reader = JSON.parse(subscription["reader"]);
            var publication = JSON.parse(subscription["publication"]);
            var payment = JSON.parse(subscription["payment"]);

            var tr = document.createElement("tr");
            var tdId = document.createElement("td");
            var textId = document.createTextNode(subscription["id"]);
            var tdReader = document.createElement("td");
            var textReader = document.createTextNode(reader["login"]);
            var tdPayment = document.createElement("td");
            var textPayment = document.createTextNode(payment["cost"]);
            var tdPublication = document.createElement("td");
            var publicationString = publication["name"] + " " + publication["author"];
            var textPublication = document.createTextNode(publicationString);
            var tdStartDate = document.createElement("td");
            var textStartDate = document.createTextNode(subscription["startDate"]);
            var tdEndDate = document.createElement("td");
            var textEndDate = document.createTextNode(subscription["endDate"]);

            tdId.appendChild(textId);
            tdReader.appendChild(textReader);
            tdPayment.appendChild(textPayment);
            tdPublication.appendChild(textPublication);
            tdStartDate.appendChild(textStartDate);
            tdEndDate.appendChild(textEndDate);

            tr.appendChild(tdId);
            tr.appendChild(tdReader);
            tr.appendChild(tdPublication);
            tr.appendChild(tdPayment);
            tr.appendChild(tdStartDate);
            tr.appendChild(tdEndDate);
            table.appendChild(tr);
        }
    };
    request.send(params);
}

function fillReaderSelect() {
    var request = new XMLHttpRequest();
    request.open("POST", "/secure/getAllReader", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var response = JSON.parse(this.responseText);
        var readers = response["readers"];
        for (var i = 0; i < readers.length; i++) {
            var reader = JSON.parse(readers[i]);
            readerSelect[readerSelect.length] = new
            Option(reader["login"], reader["id"]);
        }
    };
    request.send();
}

function fillPaymentSelect() {
    var request = new XMLHttpRequest();
    request.open("POST", "/secure/getAllPayment", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var response = JSON.parse(this.responseText);
        var payments = response["payments"];
        for (var i = 0; i < payments.length; i++) {
            var payment = JSON.parse(payments[i]);
            paymentSelect[paymentSelect.length] = new
            Option(payment["cost"], payment["id"]);
        }
    };
    request.send();
}

function fillPublicationSelect() {
    var request = new XMLHttpRequest();
    request.open("POST", "/secure/getAllPublication", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var response = JSON.parse(this.responseText);
        var publications = response["publications"];
        for (var i = 0; i < publications.length; i++) {
            var publication = JSON.parse(publications[i]);
            publicationSelect[publicationSelect.length] = new
            Option(publication["name"] + " " + publication["author"], publication["id"]);
        }
    };
    request.send();
}

function searchValueInSelect(options, valueToSelect) {
    for (var i = 0; i < options.length; i++) {
        if (+options[i].value === valueToSelect) {
            options[i].selected = true;
            return;
        }
    }
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