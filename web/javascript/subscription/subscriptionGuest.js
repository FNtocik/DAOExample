var table = document.getElementById("tableBody");
var headers = document.getElementById("tableHead");
var totalCount = -1;
var counter = 0;
var numberOfItems = 10;

setHeadersOnClick(headers);
getAll();

function getAll() {
    var requestToSubs = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems + "&sortOrder=" + header;
    requestToSubs.open("POST", "/secure/getAllSubscription", true);
    requestToSubs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
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
        var subscriptions = response["subscriptions"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < subscriptions.length; i++) {
            var subscription = JSON.parse(subscriptions[i]);
            var reader = JSON.parse(subscription["reader"]);
            var publication = JSON.parse(subscription["publication"]);
            var payment = JSON.parse(subscription["payment"]);

            var tr = document.createElement("tr");
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

            tdReader.appendChild(textReader);
            tdPayment.appendChild(textPayment);
            tdPublication.appendChild(textPublication);
            tdStartDate.appendChild(textStartDate);
            tdEndDate.appendChild(textEndDate);

            tr.appendChild(tdReader);
            tr.appendChild(tdPublication);
            tr.appendChild(tdPayment);
            tr.appendChild(tdStartDate);
            tr.appendChild(tdEndDate);
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