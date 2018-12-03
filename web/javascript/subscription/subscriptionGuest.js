var table = document.getElementById("tableBody");
var counter = 0;
var numberOfItems = 10;

getAll();

function getAll() {
    var requestToSubs = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems;
    requestToSubs.open("POST", "/secure/getAllSubscription", true);
    requestToSubs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    requestToSubs.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        if (this.responseText.length == 0) {
            counter--;
            return;
        }
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var subscriptions = JSON.parse(this.responseText);
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
    requestToSubs.send(params);
}

function leftNav() {
    if (counter - 1 >= 0)
        counter--;
    getAll();
}

function rightNav() {
    counter++;
    getAll();
}