if (window.addEventListener) window.addEventListener('load', init, false);
else if (window.attachEvent) window.attachEvent('onload', init);

function init() {
    for (var i = 0; i < document.forms.length; i++) {
        var form = document.forms[i];
        for (var j = 0; j < form.elements.length; j++) {
            var element = form.elements[j];
            var isDate = element.type === "date";
            var isNumber = element.type === "number";
            var pattern = element.getAttribute("pattern");
            if (pattern || isDate || isNumber) {
                element.onchange = validateOnChange;
            }
        }
    }
}

function validateOnChange() {
    var valid = false;
    switch (this.type) {
        case "text":
        case "password":
            valid = isValidText(this);
            break;
        case "date":
            valid = isValidDate(this);
            break;
        case "number":
            valid = isValidNumber(this);
            break;
    }
    if (valid)
        this.className = "valid";
    else
        this.className = "invalid";
}

function validateBeforePost() {
    var valid = true;
    for (var i = 0; i < document.forms.length; i++) {
        var form = document.forms[i];
        for (var j = 0; j < form.elements.length; j++) {
            var element = form.elements[j];
            if (element.onchange === validateOnChange) {
                element.onchange();
                if (element.className === "invalid") {
                    valid = false;
                }
            }
        }
    }
    return valid;
}

function isValidDate(date) {
    var value = date.value;
    var parts = value.split("-");
    var day = parseInt(parts[2], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[0], 10);
    var currentYear = (new Date()).getFullYear();
    if (year < currentYear || month === 0 || month > 12)
        return false;
    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (year % 400 === 0 || (year % 100 !== 0 && year % 4 === 0))
        monthLength[1] = 29;
    return day > 0 && day <= monthLength[month - 1];
}

function isValidText(text) {
    var patterns = text.getAttribute("pattern").split("||");
    var value = text.value;
    for (var i = 0; i < patterns.length; i++) {
        if (value.match(patterns[i]))
            return true;
    }
    return false;
}

function isValidNumber(number) {
    var value = number.value;
    var min = number.getAttribute("min");
    if (!/^\d+$/.test(value))
        return false;
    if (!value)
        return false;
    return value >= min;

}