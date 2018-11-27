function setLocaleStrings(page) {
    var request = new XMLHttpRequest();
    request.open("POST", "locale", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function (ev) {
        if (this.readyState != XMLHttpRequest.DONE)
            return;
        var curTranslate = request.responseXML.getElementsByTagName(page)[0].childNodes;
        for (var i = 0; i < curTranslate.length; i++) {
            var currentElement = document.getElementById(curTranslate[i].nodeName);
            var currentAttributes = curTranslate[i].childNodes;
            for (var j = 0; j < currentAttributes.length; j++) {
                if (currentAttributes[j].nodeName !== "innerText") {
                    currentElement.setAttribute(currentAttributes[j].nodeName, currentAttributes[j].textContent);
                } else {
                    currentElement.innerText = currentAttributes[j].textContent;
                }
            }
        }
    };
    request.send();
}