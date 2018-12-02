var publicationElement = document.getElementById("publicationId");
var languageSelect = document.getElementById("languageId");
var nameInput = document.getElementById("name");
var authorInput = document.getElementById("author");
var costInput = document.getElementById("cost");
var table = document.getElementById("tableBody");


setOnclick(table, publicationElement);
getAll();
fillLanguageSelect();

function add() {
    if (!validateBeforePost()) return;
    var request = new XMLHttpRequest();
    var name = nameInput.value;
    var author = authorInput.value;
    var cost = costInput.value;
    var languageId = languageSelect[languageSelect.selectedIndex].value;
    var params = "name=" + name + "&author="
        + author + "&cost=" + cost + "&languageId=" + languageId;
    request.open("POST", "/secure/addPublication", true);
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
    var publicationId = publicationElement.value;
    var name = nameInput.value;
    var author = authorInput.value;
    var cost = costInput.value;
    var languageId = languageSelect[languageSelect.selectedIndex].value;
    var params = "publicationId=" + publicationId + "&name=" + name +
        "&author=" + author + "&cost=" + cost + "&languageId=" + languageId;
    request.open("POST", "/secure/editPublication", true);
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
    var params = "publicationId=" + publicationElement.value;
    request.open("POST", "/secure/deletePublication", true);
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
    var params = "publicationId=" + publicationElement.value;
    request.open("POST", "/secure/getPublication", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        var publication = JSON.parse(this.responseText);
        var language = JSON.parse(publication["language"])
        publicationElement.value = publication["id"];
        nameInput.value = publication["name"];
        authorInput.value = publication["author"];
        costInput.value = publication["cost"];
        searchValueInSelect(languageSelect.options, language["id"]);
    };
    request.send(params);
}

function getAll() {
    var requestToSubs = new XMLHttpRequest();
    requestToSubs.open("POST", "/secure/getAllPublication", true);
    requestToSubs.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    requestToSubs.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        if (this.responseText.length == 0)
            return;
        var publications = JSON.parse(this.responseText);
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        for (var i = 0; i < publications.length; i++) {
            var currentPublication = JSON.parse(publications[i]);
            var currentLanguage = JSON.parse(currentPublication["language"])
            var tr = document.createElement("tr");
            var tdId = document.createElement("td");
            var textId = document.createTextNode(currentPublication["id"]);
            var tdName = document.createElement("td");
            var textName = document.createTextNode(currentPublication["name"]);
            var tdAuthor = document.createElement("td");
            var textAuthor = document.createTextNode(currentPublication["author"]);
            var tdCost = document.createElement("td");
            var textCost = document.createTextNode(currentPublication["cost"]);
            var tdLanguage = document.createElement("td");
            var textLanguage = document.createTextNode(currentLanguage["signature"]);
            tdId.appendChild(textId);
            tdName.appendChild(textName);
            tdAuthor.appendChild(textAuthor);
            tdCost.appendChild(textCost);
            tdLanguage.appendChild(textLanguage);
            tr.appendChild(tdId);
            tr.appendChild(tdName);
            tr.appendChild(tdAuthor);
            tr.appendChild(tdCost);
            tr.appendChild(tdLanguage);
            table.appendChild(tr);
        }
    };
    requestToSubs.send();
}

function fillLanguageSelect() {
    var request = new XMLHttpRequest();
    request.open("POST", "/secure/getAllLanguage", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        var languages = JSON.parse(this.responseText);
        for (var i = 0; i < languages.length; i++) {
            var currentLanguage = JSON.parse(languages[i]);
            languageSelect[languageSelect.length] = new Option(currentLanguage["signature"], currentLanguage["id"]);
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