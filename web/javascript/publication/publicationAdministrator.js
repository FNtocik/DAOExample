var publicationElement = document.getElementById("publicationId");
var languageSelect = document.getElementById("languageId");
var nameInput = document.getElementById("name");
var authorInput = document.getElementById("author");
var costInput = document.getElementById("cost");
var table = document.getElementById("tableBody");
var totalCount = -1;
var counter = 0;
var numberOfItems = 10;


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
        if (this.readyState !== XMLHttpRequest.DONE)
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
        if (this.readyState !== XMLHttpRequest.DONE)
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
        if (this.readyState !== XMLHttpRequest.DONE)
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
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var publication = JSON.parse(this.responseText);
        var language = JSON.parse(publication["language"]);
        publicationElement.value = publication["id"];
        nameInput.value = publication["name"];
        authorInput.value = publication["author"];
        costInput.value = publication["cost"];
        searchValueInSelect(languageSelect.options, language["id"]);
    };
    request.send(params);
}

function getAll() {
    var request = new XMLHttpRequest();
    var params = "counter=" + counter * numberOfItems + "&number=" + numberOfItems;
    request.open("POST", "/secure/getAllPublication", true);
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
        var publications = response["publications"];
        var size = JSON.parse(response["size"]);
        totalCount = Math.ceil(size / numberOfItems);
        for (var i = 0; i < publications.length; i++) {
            var currentPublication = JSON.parse(publications[i]);
            var currentLanguage = JSON.parse(currentPublication["language"]);
            var tr = document.createElement("tr");
            var tdId = document.createElement("td");
            tdId.setAttribute("hidden", "true");
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
    request.send(params);
}

function fillLanguageSelect() {
    var request = new XMLHttpRequest();
    request.open("POST", "/secure/getAllLanguage", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState !== XMLHttpRequest.DONE)
            return;
        var response = JSON.parse(this.responseText);
        var languages = response["languages"];
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